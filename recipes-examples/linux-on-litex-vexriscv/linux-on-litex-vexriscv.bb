SUMMARY = "Experiments with Linux on LiteX-VexRiscv"
HOMEPAGE = "https://github.com/enjoy-digital/linux-on-litex-vexriscv"
SECTION = "devel/hdl"

# there is no license on the repo
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/linux-on-litex-vexriscv;protocol=https"
SRCREV = "2758b5df5d2f96eb3eaae4ae9f26832dbb7bf64c"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit deploy
inherit litexnative
inherit fpga

DEPENDS += "dtc-native"
DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "migen-native"
DEPENDS += "litex-native"

# device modules
DEPENDS += "litex-pythondata-cpu-vexriscv-native"
DEPENDS += "litex-pythondata-cpu-vexriscv-smp-native"
DEPENDS += "litex-pythondata-software-compiler-rt-native"
DEPENDS += "litex-pythondata-misc-tapcfg-native"
DEPENDS += "litex-boards-native"
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litevideo-native"
DEPENDS += "litescope-native"
DEPENDS += "litesdcard-native"

# do not depend on libc or compiler libs, only the compiler is needed
DEPENDS_remove = "virtual/${TARGET_PREFIX}compilerlibs virtual/libc"

# prevent the population of the build-id section into the output
CC += "-Wl,--build-id=none"

do_configure() {
    # use images from images/
    sed -i 's/buildroot\//images\//' ${S}/sim.py
    sed -i 's/opensbi\//images\//' ${S}/sim.py
}

do_compile() {
    ${S}/make.py --board versa_ecp5 --build
}

do_install[noexec] = "1"

do_deploy () {
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/top.bit ${DEPLOYDIR}/top.bit
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/top.svf ${DEPLOYDIR}/top.svf
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

do_sim[dirs] += "${B}"
do_sim[depends] += "verilator-native:do_populate_sysroot"
do_sim[depends] += "libevent-native:do_populate_sysroot"
do_sim[depends] += "json-c-native:do_populate_sysroot"
do_sim[depends] += "virtual/kernel:do_deploy"
do_sim[depends] += "core-image-minimal:do_image_complete"
do_sim[depends] += "opensbi:do_populate_sysroot"
do_sim[nostamp] = "1"
python do_sim() {
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/Image"), d.expand("${S}/images/Image"), force = True)
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/core-image-minimal-${MACHINE}.cpio"), d.expand("${S}/images/rootfs.cpio"), force = True)
    oe.path.symlink(d.expand("${RECIPE_SYSROOT}/share/opensbi/ilp32/${RISCV_SBI_PLAT}/firmware/fw_jump.bin"), d.expand("${S}/images/opensbi.bin"), force = True)

    # create a child data for the terminal instance
    t = d.createCopy()
    # force CC to be BUILD_CC when running sim
    t.appendVar("OE_TERMINAL_EXPORTS", " CC")
    t.setVar("CC", "${BUILD_CC}")

    oe_terminal(d.expand("sh -c \"${S}/sim.py || read r\""), "linux-on-litex-vexriscv verilator simulation", t)
}
addtask sim after do_configure

do_load[dirs] += "${B}"
do_load[depends] += "openocd-native:do_populate_sysroot"
do_load[nostamp] = "1"
do_load() {
    ${S}/make.py --board versa_ecp5 --load
}
addtask load after do_compile

