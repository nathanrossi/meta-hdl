DESCRIPTION = "PicoSoC - A simple example SoC using PicoRV32"
HOMEPAGE = "https://github.com/cliffordwolf/picorv32/tree/master/picosoc"
LICENSE = "ISC"
SECTION = "bsp"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d"

SRC_URI = "git://github.com/cliffordwolf/picorv32;protocol=https"
SRCREV = "e0baf2e0bd49fdddef2e3440c1f6364478655154"

inherit deploy

COMPATIBLE_MACHINE = "^picosoc$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/picosoc"

DEPENDS = "yosys-native arachne-pnr-native icestorm-native icarus-verilog-native"

PROJ_NAME ?= ""
SIM_TARGET ?= ""

do_compile () {
    sed -i 's#\(\s*icetime\) -d#\1 -C ${STAGING_DIR_NATIVE}${datadir_native}/icebox/chipdb-8k.txt -d#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-gcc#$(CC)#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-objcopy#$(OBJCOPY)#' ${S}/Makefile
    sed -i 's#\(^\s\+vvp\)#\1 -M${STAGING_DIR_NATIVE}${libdir_native}/ivl#' ${S}/Makefile
    sed -i 's#\(^\s\+iverilog\)#\1 -B${STAGING_DIR_NATIVE}${libdir_native}/ivl#' ${S}/Makefile

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

