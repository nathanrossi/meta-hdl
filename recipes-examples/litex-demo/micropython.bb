SUMMARY = "MicroPython - a lean and efficient Python implementation for microcontrollers and constrained systems."
HOMEPAGE = "https://micropython.org/"
LICENSE = "MIT"
SECTION = "bsp/firmware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a8a14efdd86622bc2a34296228779da7"

DEPENDS += "litex-firmware-platform"

inherit deploy

SRC_URI = "git://github.com/fupy/micropython.git;protocol=https"
SRCREV = "${AUTOREV}"

# check this is ontop of?
PV = "1.9.4+fupy+${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

SHARED_SRC = "${RECIPE_SYSROOT}${includedir}/litex"

EXTRA_OEMAKE += "BUILDINC_DIRECTORY=${SHARED_SRC}"
EXTRA_OEMAKE += "LIBBASE_DIRECTORY=${SHARED_SRC}/libbase"

EXTRA_OEMAKE += "BUILD_VERBOSE=1"
EXTRA_OEMAKE += "CROSS_COMPILE=${TARGET_PREFIX}"
EXTRA_OEMAKE += "CPU=${TARGET_ARCH}"

# override the makefile's overriding of CC, etc (which trashes the --sysroot arg)
EXTRA_OEMAKE += "CC="${CC}""
EXTRA_OEMAKE += "CXX="${CXX}""
EXTRA_OEMAKE += "AS="${AS}""
EXTRA_OEMAKE += "LD="${LD}""
EXTRA_OEMAKE += "AR="${AR}""

# TODO: makefile is broken adds literal "" to args, compiler thinks that is a file
EXTRA_OEMAKE += "CRT0FLAGS="

# disable the build-id
TARGET_LDFLAGS .= " -Wl,--build-id=none"

do_compile () {
    oe_runmake BUILD=${B} -C ${S}/ports/fupy
}

do_install () {
    install -d ${D}/boot
    install -m 0755 ${B}/firmware.bin ${D}/boot
    install -m 0755 ${B}/firmware.elf ${D}/boot
}

SYSROOT_DIRS += "/boot"
FILES_${PN} += "/boot"

do_deploy() {
    install -Dm 0644 ${B}/firmware.bin ${DEPLOYDIR}/micropython-${MACHINE}.bin
    install -Dm 0644 ${B}/firmware.elf ${DEPLOYDIR}/micropython-${MACHINE}.elf

    # symlink firmware image to 'boot.bin', for qemu/netloading
    ln -sf micropython-${MACHINE}.bin ${DEPLOYDIR}/boot.bin
}
addtask deploy before do_build after do_install

COMPATIBLE_MACHINE = "litex-platform"
PACKAGE_ARCH = "${MACHINE_ARCH}"

