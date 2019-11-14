SUMMARY = "MicroPython - a lean and efficient Python implementation for microcontrollers and constrained systems."
HOMEPAGE = "https://micropython.org/"
LICENSE = "MIT"
SECTION = "bsp/firmware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=8a3983a7b144ef8a632c8549f34f87b1"

inherit deploy

SRC_URI = "git://github.com/im-tomu/micropython.git;protocol=https;branch=fomu"
SRCREV = "8962f238dcb51aee26db28980fe96c282151a7dd"

PV = "1.4.1+fomu+${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

EXTRA_OEMAKE += "BUILD_VERBOSE=1"
EXTRA_OEMAKE += "CC="${CC}""
EXTRA_OEMAKE += "CXX="${CXX}""
EXTRA_OEMAKE += "OBJCOPY="${OBJCOPY}""

do_compile () {
    oe_runmake BUILD=${B} -C ${S}/ports/fomu
}

do_install () {
    install -d ${D}/boot
    install -m 0755 ${B}/firmware.bin ${D}/boot
    install -m 0755 ${B}/firmware.elf ${D}/boot
}

do_deploy() {
    install -Dm 0644 ${B}/firmware.bin ${DEPLOYDIR}/micropython-${MACHINE}.bin
    install -Dm 0644 ${B}/firmware.elf ${DEPLOYDIR}/micropython-${MACHINE}.elf

    # symlink firmware image to 'boot.bin', for qemu/netloading
    ln -sf micropython-${MACHINE}.bin ${DEPLOYDIR}/boot.bin
}
addtask deploy before do_build after do_install

FILES_${PN} += "/boot"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "fomu"

