DESCRIPTION = "FOSS architecture definitions of FPGA hardware useful for doing PnR device generation."
HOMEPAGE = "https://github.com/SymbiFlow/symbiflow-arch-defs"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3894bff8746d28aca6650d596b65b37a"

SRC_URI = "git://github.com/SymbiFlow/symbiflow-arch-defs;protocol=https"
SRCREV = "56452559f507a9adc3c3dec2d950e25c3f81910f"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += "yosys-native"
DEPENDS += "verilog-to-routing-native"
#DEPENDS += "symbiflow-arch-defs"

do_compile() {
    yosys -p "synth_xilinx -vpr -flatten -abc9 -nosrl -noclkbuf -nodsp -iopad -run:check -arch xc7 -top top; write_json ${B}/output.json" \
        -l output.yosys.log \
        ${S}/xc/xc7/tests/counter/counter_arty.v
    yosys -p "read_json ${B}/output.json; write_blif -attr -cname -param -true VCC VCC -false GND GND -undef VCC VCC ${B}/output.eblif" \
        -l output-eblif.yosys.log
    # ${S}/xc/xc7/tests/common/arty_swbut.pcf

    xc7a50t
    part=xc7a35tcsg324-1

    #vpr \
        #.xml
        #${B}/output.eblif
        #--device ${DEVICE_FULL}
        #--read_rr_graph ${OUT_RRBIN_REAL_LOCATION}
        #${VPR_BASE_ARGS_LIST}
        #${VPR_ARCH_ARGS_LIST}
        #${VPR_EXTRA_ARGS_LIST}
        #${SDC_ARG}

    #genfasm \
        #${DEVICE_MERGED_FILE_LOCATION}
        #${OUT_EBLIF}
        #--device ${DEVICE_FULL}
        #--read_rr_graph ${OUT_RRBIN_REAL_LOCATION}
        #${VPR_BASE_ARGS_LIST}
        #${VPR_ARCH_ARGS_LIST}
        #${VPR_EXTRA_ARGS_LIST}

    fasm2frames.py \
        --db-root "${XRAY_DATABASE_DIR}/artix7" \
        --part $part \
        ${B}/output.fasm > ${B}/output.frames
    xc7frames2bit \
        --part_file "${XRAY_DATABASE_DIR}/artix7/$part/part.yaml" \
        --part_name $part \
        --frm_file ${B}/output.frames \
        --output_file ${B}/output.bit
}
