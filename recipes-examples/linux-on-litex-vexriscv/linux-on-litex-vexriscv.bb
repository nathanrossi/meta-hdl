SUMMARY = "Experiments with Linux on LiteX-VexRiscv"
HOMEPAGE = "https://github.com/litex-hub/linux-on-litex-vexriscv"
SECTION = "devel/hdl"

# there is no license on the repo
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dbd6d84e1c93eda0bd79ca770c20a31"

SRC_URI = "git://github.com/litex-hub/linux-on-litex-vexriscv;protocol=https;branch=master"
SRCREV = "708b0cbf315ff2990a00beba314f236af3b9431a"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit deploy
inherit python3native
inherit litexnative
inherit fpga

# inhibit default deps, to exclude libc
INHIBIT_DEFAULT_DEPS = "1"
DEPENDS += "virtual/${HOST_PREFIX}gcc"

DEPENDS += "dtc-native"
DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "migen-native"
DEPENDS += "litex-native"
DEPENDS += "meson-native ninja-native"

# device modules
DEPENDS += "litex-pythondata-cpu-vexriscv-native"
DEPENDS += "litex-pythondata-cpu-vexriscv-smp-native"
DEPENDS += "litex-pythondata-software-compiler-rt-native"
DEPENDS += "litex-pythondata-software-picolibc-native"
DEPENDS += "litex-pythondata-misc-tapcfg-native"
DEPENDS += "litex-boards-native"
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litevideo-native"
DEPENDS += "litesdcard-native"
DEPENDS += "litespi-native"

# disable any security flags set by security_flags.inc (e.g. poky distro)
SECURITY_CFLAGS = "${SECURITY_NOPIE_CFLAGS}"
SECURITY_LDFLAGS = ""

# prevent the population of the build-id section into the output
CC += "-Wl,--build-id=none"

do_configure() {
    # use images from images/
    sed -i 's/buildroot\//images\//' ${S}/sim.py
    sed -i 's/opensbi\//images\//' ${S}/sim.py
}

do_compile() {
    mkdir -p ${S}/build
    ln -sf lattice_versa_ecp5 ${S}/build/versa_ecp5

    ${S}/make.py --board versa_ecp5 --build
}

do_install[noexec] = "1"

do_deploy () {
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/versa_ecp5.bit ${DEPLOYDIR}/versa_ecp5.bit
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/versa_ecp5.svf ${DEPLOYDIR}/versa_ecp5.svf
    install -Dm 0655 ${B}/images/rv32.dtb ${DEPLOYDIR}/rv32.dtb

    # rewrite paths in boot.json for use from deploy dir
    cp ${S}/images/boot.json ${B}/boot-${MACHINE}.json
    sed -i 's/opensbi.bin/fw_jump.bin/' ${B}/boot-${MACHINE}.json
    sed -i "s/rootfs.cpio/core-image-minimal-${MACHINE}.cpio/" ${B}/boot-${MACHINE}.json
    install -Dm 0644 ${B}/boot-${MACHINE}.json ${DEPLOYDIR}/boot.json
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "versa-ecp5"

# include terminal to run the simulator on the user terminal
inherit terminal

export VERILATOR_ROOT = "${STAGING_DIR_NATIVE}/usr/share/verilator"

do_sim_setup[dirs] += "${B}"
do_sim_setup[depends] += "verilator-native:do_populate_sysroot"
do_sim_setup[depends] += "libevent-native:do_populate_sysroot"
do_sim_setup[depends] += "json-c-native:do_populate_sysroot"
do_sim_setup[depends] += "virtual/kernel:do_deploy"
do_sim_setup[depends] += "core-image-minimal:do_image_complete"
do_sim_setup[depends] += "opensbi:do_populate_sysroot"
python do_sim_setup() {
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/Image"), d.expand("${S}/images/Image"), force = True)
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/core-image-minimal-${MACHINE}.cpio"), d.expand("${S}/images/rootfs.cpio"), force = True)
    oe.path.symlink(d.expand("${RECIPE_SYSROOT}/share/opensbi/ilp32/${RISCV_SBI_PLAT}/firmware/fw_jump.bin"), d.expand("${S}/images/opensbi.bin"), force = True)
}
addtask sim_setup after do_configure

do_sim[dirs] += "${B}"
do_sim[nostamp] = "1"
python do_sim() {
    # create a child data for the terminal instance
    t = d.createCopy()
    # force CC to be BUILD_CC when running sim
    t.appendVar("OE_TERMINAL_EXPORTS", " CC")
    t.setVar("CC", "${BUILD_CC}")

    oe_terminal(d.expand("sh -c \"${S}/sim.py || read r\""), "linux-on-litex-vexriscv verilator simulation", t)
}
addtask sim after do_sim_setup

inherit python3-dir

do_sim_check_boot[dirs] += "${B}"
do_sim_check_boot[nostamp] = "1"
do_sim_check_boot[depends] += "python3-pexpect-native:do_populate_sysroot"
python do_sim_check_boot() {
    import os
    import sys
    import signal
    sys.path.append(d.expand("${RECIPE_SYSROOT_NATIVE}/${PYTHON_SITEPACKAGES_DIR}"))
    import pexpect

    # force CC to be BUILD_CC when running sim
    env = os.environ.copy()
    env["CC"] = d.getVar("BUILD_CC")

    s = pexpect.spawn(d.expand("${S}/sim.py"), env = env)
    try:
        while True:
            if s.expect("(.*?)\r\n", timeout = 60) == 0:
                line = s.match.group(1).decode()
                # repr to prevent raw terminal codes
                sys.stdout.write("{}\n".format(repr(line)[1:-1]))
                sys.stdout.flush()
                if "linux version" in line.casefold():
                    break
    except (pexpect.exceptions.TIMEOUT, pexpect.exceptions.EOF):
        bb.fatal("Failed to boot to kernel")
    finally:
        s.kill(signal.SIGINT)
        s.wait()
}
addtask sim_check_boot after do_sim_setup

do_load[dirs] += "${B}"
do_load[depends] += "openocd-native:do_populate_sysroot"
do_load[nostamp] = "1"
do_load() {
    ${S}/make.py --board versa_ecp5 --load
}
addtask load after do_compile

