DESCRIPTION = "PicoSoC - A simple example SoC using PicoRV32"
HOMEPAGE = "https://github.com/cliffordwolf/picorv32/tree/master/picosoc"
LICENSE = "ISC"
SECTION = "bsp"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d"

SRC_URI = "git://github.com/cliffordwolf/picorv32;protocol=https"
SRCREV = "028aa757dfdf1c4ed667cea5dec3c30d22bcd36e"

inherit deploy

COMPATIBLE_MACHINE = "^picosoc$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/picosoc"

DEPENDS = "yosys-native arachne-pnr-native icestorm-native"

MAKE_TARGETS ?= "all"

do_compile () {
    sed -i 's#\(\s*icetime\) -d#\1 -C ${STAGING_DIR_NATIVE}${datadir_native}/icebox/chipdb-8k.txt -d#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-gcc#$(CC)#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-objcopy#$(OBJCOPY)#' ${S}/Makefile

    oe_runmake -f ${S}/Makefile ${MAKE_TARGETS}
}

do_deploy () {
    :
}
addtask deploy before do_build after do_install

