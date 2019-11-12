DESCRIPTION = "PicoSoC - A simple example SoC using PicoRV32"
HOMEPAGE = "https://github.com/cliffordwolf/picorv32/tree/master/picosoc"
LICENSE = "ISC"
SECTION = "bsp"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d"

SRC_URI = "git://github.com/cliffordwolf/picorv32;protocol=https"
SRCREV = "46aa89c13f51c2963019be5e9b6af40d64dceb62"

inherit deploy

COMPATIBLE_MACHINE = "^picosoc$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/picosoc"

DEPENDS = "yosys-native icestorm-native icarus-verilog-native"

PROJ_NAME ?= ""
SIM_TARGET ?= ""

TARGET_CC_ARCH_append = " -nostdlib"

# 'gcc -E' is not the same as running just the preprocessor (it processes .lds files differently)
CPP = "${HOST_PREFIX}cpp ${TOOLCHAIN_OPTIONS} ${HOST_CC_ARCH}"

# export vvp and iverilog variables with local paths
export VVP = "vvp -M${STAGING_DIR_NATIVE}${libdir_native}/ivl"
export IVERILOG = "iverilog -B${STAGING_DIR_NATIVE}${libdir_native}/ivl"

do_compile () {
    sed -i 's#riscv32-[a-z-]\+elf-gcc#$(CC)#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-cpp#$(CPP)#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-objcopy#$(OBJCOPY)#' ${S}/Makefile
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

