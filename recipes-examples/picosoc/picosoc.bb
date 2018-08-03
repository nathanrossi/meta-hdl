DESCRIPTION = "PicoSoC - A simple example SoC using PicoRV32"
HOMEPAGE = "https://github.com/cliffordwolf/picorv32/tree/master/picosoc"
LICENSE = "ISC"
SECTION = "bsp"

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d"

SRC_URI = "git://github.com/cliffordwolf/picorv32;protocol=https"
SRCREV = "9b6ea045f9b539b0f708d71962716e5dde865181"

inherit deploy

COMPATIBLE_MACHINE = "picosoc-hx8k"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git/picosoc"

#INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "yosys-native arachne-pnr-native icestorm-native"

export ICEBOX = "${STAGING_DIR_NATIVE}${datadir_native}/icebox"

do_compile () {
    sed -i 's#\(\s*icetime\) -d#\1 -C $(ICEBOX)/chipdb-8k.txt -d#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-gcc#$(CC)#' ${S}/Makefile
    sed -i 's#riscv32-[a-z-]\+elf-objcopy#$(OBJCOPY)#' ${S}/Makefile

    oe_runmake -f ${S}/Makefile hx8kdemo.bin
    oe_runmake -f ${S}/Makefile firmware.bin
}

do_deploy () {
    install -Dm 0644 ${B}/hx8kdemo.bin ${DEPLOYDIR}/picosoc-hx8k.bin
    install -Dm 0644 ${B}/firmware.bin ${DEPLOYDIR}/firmware.bin
}
addtask deploy before do_build after do_install

