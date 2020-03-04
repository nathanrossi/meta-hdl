require nextpnr.inc
require nextpnr-xilinx.inc
require nextpnr-bbasm-native.inc

EXTRA_OECMAKE += "-DARCH=xilinx"

do_compile_prepend() {
    mkdir -p ${B}/xilinx
    echo "Generating xc7a35t.bba"
    python3 ${S}/xilinx/python/bbaexport.py --device xc7a35tcsg324-1 --bba ${B}/xilinx/xc7a35t.bba
    echo "Generating xc7a35t.bin"
    bbasm-xilinx --le ${B}/xilinx/xc7a35t.bba ${B}/xilinx/xc7a35t.bin
}

do_install_append() {
    install -d ${D}${datadir}/chipdb
    install ${B}/xilinx/xc7a35t.bin ${D}${datadir}/chipdb/
}

# populate the database into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/chipdb"

FILES_${PN} += "${datadir}/chipdb"

