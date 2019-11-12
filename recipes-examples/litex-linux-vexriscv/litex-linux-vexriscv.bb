SUMMARY = "Experiments with Linux on LiteX-VexRiscv"
HOMEPAGE = "https://github.com/enjoy-digital/linux-on-litex-vexriscv"
SECTION = "devel/hdl"

# there is no license on the repo
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/linux-on-litex-vexriscv;protocol=https"
SRCREV = "${AUTOREV}"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += "dtc-native"
DEPENDS += "migen-native"
DEPENDS += "litex-native"
DEPENDS += "yosys-native"
DEPENDS += "nextpnr-ecp5-native"
DEPENDS += "prjtrellis-native prjtrellis-db-native"

# device modules
DEPENDS += "litex-boards-native"
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litevideo-native"

# do not depend on libc or compiler libs, only the compiler is needed
DEPENDS_remove = "virtual/${TARGET_PREFIX}compilerlibs virtual/libc"

do_configure[noexec] = "1"

inherit deploy
inherit litexnative

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
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "versa-ecp5"

