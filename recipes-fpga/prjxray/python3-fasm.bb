SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f073e725b3957def9888511f3cccf349"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https"
SRCREV = "4857dde757edd88688c2faf808774d85bdbe3900"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
