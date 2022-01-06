SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f073e725b3957def9888511f3cccf349"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https;branch=master"
SRCREV = "41d22f2edaebae63a7ede6e16ecf373a9ce1cedb"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-cython-native"

RDEPENDS:${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
