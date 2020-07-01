DESCRIPTION = "nextpnr-xilinx Example for arty-a35"

require nextpnr-xilinx.inc

inherit fpga
inherit allarch
inherit python3native

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d, family = 'artix7')}"

XRAY_DATABASE_DIR = "${STAGING_DIR_NATIVE}${datadir_native}/xray/database"
CHIPDB_DIR = "${STAGING_DATADIR}/chipdb"

B = "${WORKDIR}/build"
ATTOSOC = "${S}/xilinx/examples/arty-a35"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
    # copied from attosoc.sh in xilinx/examples/arty-a35/attosoc.sh

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

    echo "fasm2frames"
    fasm2frames --db-root "${XRAY_DATABASE_DIR}/artix7" \
        --part xc7a35tcsg324-1 \
        ${B}/attosoc.fasm > ${B}/attosoc.frames
    echo "xc7frames2bit"
    xc7frames2bit \
        --part_file "${XRAY_DATABASE_DIR}/artix7/xc7a35tcsg324-1/part.yaml" \
        --part_name xc7a35tcsg324-1 \
        --frm_file ${B}/attosoc.frames \
        --output_file ${B}/attosoc.bit
}

