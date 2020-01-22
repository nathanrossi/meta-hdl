require fomu-workshop.inc

SUMMARY = "Fomu Workshop litex rgb"
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

