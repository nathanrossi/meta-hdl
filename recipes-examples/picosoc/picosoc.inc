DESCRIPTION = "PicoSoC - A simple example SoC using PicoRV32"
HOMEPAGE = "https://github.com/YosysHQ/picorv32/tree/master/picosoc"
LICENSE = "ISC"
SECTION = "bsp"

LIC_FILES_CHKSUM = "file://../COPYING;md5=9feb72939be4c181179f60e0604ae1a5"

SRC_URI = "git://github.com/YosysHQ/picorv32;protocol=https;branch=master"
SRCREV = "f00a88c36eaab478b64ee27d8162e421049bcc66"

inherit deploy fpga

COMPATIBLE_MACHINE = "^picosoc$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/picosoc"

DEPENDS += "yosys-native"
DEPENDS += "${@fpga_family_depends(d)}"
DEPENDS += "icarus-verilog-native"

PROJ_NAME ?= ""
SIM_TARGET ?= ""

# prevent the population of the build-id section into the output
TARGET_CFLAGS += "-Wl,--build-id=none"

# export vvp and iverilog variables with local paths
export VVP = "vvp -M${STAGING_DIR_NATIVE}${libdir_native}/ivl"
export IVERILOG = "iverilog -B${STAGING_DIR_NATIVE}${libdir_native}/ivl"

# icarus does not support systemverilog default assignment, this is used in the yosys cells for ice40.
IVERILOG += "-DNO_ICE40_DEFAULT_ASSIGNMENTS"

# override the makefiles CROSS and CFLAGS values
EXTRA_OEMAKE += "CROSS=${TARGET_PREFIX} CFLAGS="${TARGET_CC_ARCH} ${TOOCHAIN_OPTIONS} ${TARGET_CFLAGS}""

do_compile () {
    sed -i 's#\(^\s\+\)vvp#\1$(VVP)#' ${S}/Makefile
    sed -i 's#\(^\s\+\)iverilog#\1$(IVERILOG)#' ${S}/Makefile

    oe_runmake -f ${S}/Makefile ${PROJ_NAME}.bin ${PROJ_NAME}_fw.bin ${PROJ_NAME}_fw.hex

    if [ -n "${SIM_TARGET}" ]; then
        # run the simulation target
        cp ${B}/${PROJ_NAME}_fw.hex ${B}/firmware.hex
        oe_runmake -f ${S}/Makefile ${SIM_TARGET}
    fi
}

do_deploy () {
    :
}
addtask deploy before do_build after do_install

