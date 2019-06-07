SUMMARY = "An open source USB bootloader for FPGAs"
HOMEPAGE = "https://github.com/tinyfpga/TinyFPGA-Bootloader"
LICENSE = "GPLv3"

SRC_URI = "git://github.com/tinyfpga/TinyFPGA-Bootloader;protocol=https"
SRCREV = "6e312535cba4fc0216a59b1b9af651ba45fcc1d3"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit setuptools3

S = "${WORKDIR}/git/programmer"

# used for versioning
DEPENDS += "${PYTHON_PN}-setuptools-scm-native"

RDEPENDS_${PN} += " \
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
