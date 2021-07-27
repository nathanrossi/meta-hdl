require fomu-workshop.inc

SUMMARY = "Fomu Workshop verilog blink"
B = "${S}/hdl/verilog/blink-expanded"

inherit deploy
inherit litexnative
inherit fpga

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "dfu-util-native"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
    oe_runmake FOMU_REV=pvt

    # manually build dfu with PVT vid/pid
    cp ${B}/blink.bit ${B}/blink.dfu
    dfu-suffix --vid 1209 --pid 5bf0 --add ${B}/blink.dfu
}

do_deploy () {
    install -Dm 0644 ${B}/blink.dfu ${DEPLOYDIR}/fomu-workshop-verilog-blink.dfu
}
addtask deploy before do_build after do_install

