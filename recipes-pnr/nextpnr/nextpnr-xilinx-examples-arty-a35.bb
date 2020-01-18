DESCRIPTION = "nextpnr-xilinx Example for arty-a35"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "git://github.com/daveshah1/nextpnr-xilinx;protocol=https;branch=xilinx"
SRCREV = "f576de4d4cd0639138cce3e785f84d3b0b0eff0d"

PV = "0+git${SRCPV}"

inherit fpga
inherit allarch
inherit python3native

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d, family = 'artix7')}"

XRAY_DATABASE_DIR = "${STAGING_DIR_NATIVE}${datadir_native}/xray/database"
CHIPDB_DIR = "${STAGING_DIR_NATIVE}${datadir_native}/chipdb"

PATH_prepend = "${STAGING_DIR_NATIVE}${bindir_native}/prjxray:"

S = "${WORKDIR}/git"
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

    #source "${XRAY_DIR}/utils/environment.sh"

    echo "fasm2frames"
    fasm2frames.py --db-root "${XRAY_DATABASE_DIR}/artix7" \
        --part xc7a35tcsg324-1 \
        ${B}/attosoc.fasm > ${B}/attosoc.frames
    echo "xc7frames2bit"
    xc7frames2bit \
        --part_file "${XRAY_DATABASE_DIR}/artix7/xc7a35tcsg324-1.yaml" \
        --part_name xc7a35tcsg324-1 \
        --frm_file ${B}/attosoc.frames \
        --output_file ${B}/attosoc.bit
}

