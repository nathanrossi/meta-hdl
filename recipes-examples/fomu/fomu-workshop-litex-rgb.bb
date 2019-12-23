SUMMARY = "Bootloader for Fomu"
HOMEPAGE = "https://github.com/im-tomu/foboot"
SECTION = "devel/hdl"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/im-tomu/fomu-workshop;protocol=https"
SRCREV = "74dbc8ec371d9872899755fcb0da50cb9ca4bcd9"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${S}/litex"

inherit deploy
inherit litexnative
inherit fpga

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "dfu-util-native"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
    # HACK: the lxbuildenv script will always exit with code 1
    python3 workshop_rgb.py --board pvt || true

    # check it built
    [ -e ${B}/build/gateware/top.bin ] || exit 1
    [ -e ${B}/build/gateware/top.dfu ] || exit 1
}

do_deploy () {
    install -Dm 0644 ${B}/build/gateware/top.bin ${DEPLOYDIR}/top.bin
    install -Dm 0644 ${B}/build/gateware/top.dfu ${DEPLOYDIR}/top.dfu
}
addtask deploy before do_build after do_install

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "fomu"

