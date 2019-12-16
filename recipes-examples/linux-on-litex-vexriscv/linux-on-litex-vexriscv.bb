SUMMARY = "Experiments with Linux on LiteX-VexRiscv"
HOMEPAGE = "https://github.com/enjoy-digital/linux-on-litex-vexriscv"
SECTION = "devel/hdl"

# there is no license on the repo
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/linux-on-litex-vexriscv;protocol=https"
SRCREV = "baeca54764dc02df32989190fd89c512386f55da"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit deploy
inherit litexnative
inherit fpga

DEPENDS += "dtc-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "migen-native"
DEPENDS += "litex-native"

# device modules
DEPENDS += "litex-boards-native"
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litevideo-native"

# do not depend on libc or compiler libs, only the compiler is needed
DEPENDS_remove = "virtual/${TARGET_PREFIX}compilerlibs virtual/libc"

do_configure() {
    # rewrite paths in images.json for use from deploy dir
    cp ${S}/images.json ${B}/images-${MACHINE}.json
    sed -i 's/buildroot\///' ${B}/images-${MACHINE}.json
    sed -i 's/emulator\///' ${B}/images-${MACHINE}.json
}

do_compile() {
    ${S}/make.py --board versa_ecp5 --build

    # build emulator
    make -C ${S}/emulator BUILD_DIR=${S}/build/versa_ecp5/
}

do_install[noexec] = "1"

do_deploy () {
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/top.bit ${DEPLOYDIR}/top.bit
    install -Dm 0644 ${B}/build/versa_ecp5/gateware/top.svf ${DEPLOYDIR}/top.svf
    install -Dm 0644 ${B}/emulator/emulator.bin ${DEPLOYDIR}/emulator.bin
    install -Dm 0655 ${B}/buildroot/rv32.dtb ${DEPLOYDIR}/rv32.dtb
    install -Dm 0644 ${B}/images-${MACHINE}.json ${DEPLOYDIR}/images.json
    ln -sf core-image-minimal-${MACHINE}.cpio ${DEPLOYDIR}/rootfs.cpio
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
do_sim[depends] += "core-image-minimal:do_image_cpio"
do_sim[nostamp] = "1"
python do_sim() {
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/Image"), d.expand("${S}/buildroot/Image"), force = True)
    oe.path.symlink(d.expand("${DEPLOY_DIR_IMAGE}/core-image-minimal-${MACHINE}.cpio"), d.expand("${S}/buildroot/rootfs.cpio"), force = True)

    oe_terminal(d.expand("${S}/sim.py"), "linux-on-litex-vexriscv verilator simulation", d)
}
addtask sim after do_configure

do_load[dirs] += "${B}"
do_load[depends] += "openocd-native:do_populate_sysroot"
do_load[nostamp] = "1"
do_load() {
    ${S}/make.py --board versa_ecp5 --load
}
addtask load after do_compile

