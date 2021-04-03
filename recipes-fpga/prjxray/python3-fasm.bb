SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f073e725b3957def9888511f3cccf349"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https"
SRCREV = "af39a4fb7adc367502ed91ac7f0b8f1c17f37ee8"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-cython-native"

RDEPENDS_${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
