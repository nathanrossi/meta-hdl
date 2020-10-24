SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f073e725b3957def9888511f3cccf349"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https"
SRCREV = "bae2e6dd782026d9db3e4e00ef535d1b5858322d"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-cython-native"

RDEPENDS_${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
