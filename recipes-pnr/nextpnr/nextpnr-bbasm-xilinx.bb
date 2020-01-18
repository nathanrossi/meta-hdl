require nextpnr-xilinx.inc

OECMAKE_TARGET_COMPILE = "bbasm"

EXTRA_OECMAKE += "-DARCH=generic"

do_install() {
    install -Dm 744 ${B}/bbasm ${D}${bindir}/bbasm-xilinx
}

