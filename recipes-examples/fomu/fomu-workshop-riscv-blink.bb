require fomu-workshop.inc

SUMMARY = "Fomu Workshop riscv-blink"
B = "${S}/riscv-blink"

inherit deploy

DEPENDS += "dfu-util-native"

do_configure[noexec] = "1"
do_install[noexec] = "1"

EXTRA_OEMAKE += "CC="${CC}""
EXTRA_OEMAKE += "CXX="${CXX}""
EXTRA_OEMAKE += "OBJCOPY="${OBJCOPY}""

do_compile() {
    # map all .rodata sections into ".rodata"
    sed -i 's/\*(\.srodata)/*(.srodata .srodata.*)/' ${S}/riscv-blink/ld/linker.ld

    oe_runmake riscv-blink.bin

    # manually build dfu with PVT vid/pid
    cp ${B}/riscv-blink.bin ${B}/riscv-blink.dfu
    dfu-suffix --vid 1209 --pid 5bf0 --add ${B}/riscv-blink.dfu
}

do_deploy () {
    install -Dm 0644 ${B}/riscv-blink.dfu ${DEPLOYDIR}/fomu-workshop-riscv-blink.dfu
}
addtask deploy before do_build after do_install

