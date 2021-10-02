SUMMARY = "MicroPython - a lean and efficient Python implementation for microcontrollers and constrained systems."
HOMEPAGE = "https://micropython.org/"
LICENSE = "MIT"
SECTION = "bsp/firmware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a8a14efdd86622bc2a34296228779da7"

DEPENDS += "dfu-util-native"

inherit deploy

SRC_URI = "git://github.com/xobs/micropython.git;protocol=https;branch=fomu"
SRCREV = "421dcd20ed71b91cb1f573fdb19d8832b597aa1c"

PV = "1.10+fomu+${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

EXTRA_OEMAKE += "BUILD_VERBOSE=1"
EXTRA_OEMAKE += "CC="${CC}""
EXTRA_OEMAKE += "CXX="${CXX}""
EXTRA_OEMAKE += "OBJCOPY="${OBJCOPY}""

do_compile () {
    oe_runmake BUILD=${B} -C ${S}/ports/fomu

    # manually build dfu with PVT vid/pid
    cp ${B}/firmware.bin ${B}/firmware.dfu
    dfu-suffix --vid 1209 --pid 5bf0 --add ${B}/firmware.dfu
}

do_install () {
    install -d ${D}/boot
    install -m 0755 ${B}/firmware.bin ${D}/boot
    install -m 0755 ${B}/firmware.elf ${D}/boot
}

do_deploy() {
    install -Dm 0644 ${B}/firmware.bin ${DEPLOYDIR}/micropython-${MACHINE}.bin
    install -Dm 0644 ${B}/firmware.elf ${DEPLOYDIR}/micropython-${MACHINE}.elf
    install -Dm 0644 ${B}/firmware.dfu ${DEPLOYDIR}/micropython-${MACHINE}.dfu
}
addtask deploy before do_build after do_install

FILES:${PN} += "/boot"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "fomu"

