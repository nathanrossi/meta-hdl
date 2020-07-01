require nextpnr.inc
require nextpnr-xilinx.inc

EXTRA_OECMAKE += "-DARCH=xilinx"

do_install_append() {
    install -Dm 744 ${B}/bbasm ${D}${bindir}/bbasm-xilinx
}
