SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=3894bff8746d28aca6650d596b65b37a"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https"
SRCREV = "052f629217119b699ea83e03c412bc6fafb2313a"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
