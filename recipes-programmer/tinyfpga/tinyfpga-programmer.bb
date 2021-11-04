SUMMARY = "An open source USB bootloader for FPGAs"
HOMEPAGE = "https://github.com/tinyfpga/TinyFPGA-Bootloader"
LICENSE = "GPLv3"

SRC_URI = "git://github.com/tinyfpga/TinyFPGA-Bootloader;protocol=https;branch=master"
SRCREV = "97f6353540bf7c0d27f5612f202b48f41da75299"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit setuptools3

S = "${WORKDIR}/git/programmer"

# used for versioning
DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

RDEPENDS:${PN} += " \
        ${PYTHON_PN}-pyserial \
        ${PYTHON_PN}-jsonmerge \
        ${PYTHON_PN}-intelhex \
        ${PYTHON_PN}-tqdm \
        ${PYTHON_PN}-six \
        ${PYTHON_PN}-pyusb \
        ${PYTHON_PN}-packaging \
        ${PYTHON_PN}-setuptools \
        "

BBCLASSEXTEND = "native nativesdk"
