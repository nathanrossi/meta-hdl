require nextpnr-xilinx.inc

inherit allarch
inherit python3native

DEPENDS += "nextpnr-xilinx-native"

do_compile() {
    # HACK: the scripts don't have newer changes to rename the generic
    # "xc7a35t" device in prjxray, create a symlink so these tools can find
    # file the correct files.
    ln -sf xc7a35tcsg324-1 ${S}/xilinx/external/prjxray-db/artix7/xc7a35t
    ln -sf xc7a50tfgg484-1 ${S}/xilinx/external/prjxray-db/artix7/xc7a50t

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

FILES:${PN} += "${datadir}/chipdb"

