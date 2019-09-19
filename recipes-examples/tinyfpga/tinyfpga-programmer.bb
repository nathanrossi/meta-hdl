SUMMARY = "An open source USB bootloader for FPGAs"
HOMEPAGE = "https://github.com/tinyfpga/TinyFPGA-Bootloader"
LICENSE = "GPLv3"

SRC_URI = "git://github.com/tinyfpga/TinyFPGA-Bootloader;protocol=https"
SRCREV = "22e7a49c1c0e2914a9124bf9861aa6d7a23b96ac"

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
