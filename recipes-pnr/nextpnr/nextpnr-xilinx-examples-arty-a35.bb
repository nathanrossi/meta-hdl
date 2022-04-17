DESCRIPTION = "nextpnr-xilinx Example for arty-a35"

require nextpnr-xilinx.inc

inherit fpga
inherit allarch
inherit python3native

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d, family = 'artix7')}"
DEPENDS += "python3-xc-fasm-native"

XRAY_DATABASE_DIR = "${STAGING_DIR_NATIVE}${datadir_native}/xray/database"
CHIPDB_DIR = "${STAGING_DATADIR}/chipdb"

B = "${WORKDIR}/build"
ATTOSOC = "${S}/xilinx/examples/arty-a35"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
    # cd into source for readmem of firmware.hex
    (cd ${ATTOSOC};
        yosys -p "synth_xilinx -flatten -nowidelut -abc9 -arch xc7 -top top; write_json ${B}/attosoc.json" \
        ${ATTOSOC}/../attosoc/attosoc.v ${ATTOSOC}/attosoc_top.v)
    nextpnr-xilinx \
        --chipdb ${CHIPDB_DIR}/xc7a35t.bin \
        --xdc ${ATTOSOC}/arty.xdc \
        --json ${B}/attosoc.json \
        --write ${B}/attosoc_routed.json \
        --fasm ${B}/attosoc.fasm

    echo "xcfasm"
    xcfasm \
        --db-root "${XRAY_DATABASE_DIR}/artix7" \
        --part xc7a35tcsg324-1 \
        --part_file "${XRAY_DATABASE_DIR}/artix7/xc7a35tcsg324-1/part.yaml" \
        --fn_in ${B}/attosoc.fasm \
        --frm_out ${B}/attosoc.frames \
        --bit_out ${B}/attosoc.bit
}

