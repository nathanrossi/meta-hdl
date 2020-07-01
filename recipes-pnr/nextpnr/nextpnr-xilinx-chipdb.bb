require nextpnr-xilinx.inc

inherit allarch
inherit python3native

DEPENDS += "nextpnr-xilinx-native"

do_compile() {
    mkdir -p ${B}
    echo "Generating xc7a35t.bba"
    python3 ${S}/xilinx/python/bbaexport.py --device xc7a35tcsg324-1 --bba ${B}/xc7a35t.bba
    echo "Generating xc7a35t.bin"
    bbasm-xilinx --le ${B}/xc7a35t.bba ${B}/xc7a35t.bin
}

do_install() {
    install -d ${D}${datadir}/chipdb
    install ${B}/xc7a35t.bin ${D}${datadir}/chipdb/
}

FILES_${PN} += "${datadir}/chipdb"

