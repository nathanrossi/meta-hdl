SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/SymbiFlow/fasm"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=3894bff8746d28aca6650d596b65b37a"

SRC_URI = "git://github.com/SymbiFlow/fasm;protocol=https"
SRCREV = "54530625f90b4e5fa7d65091507cacb7461743ea"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
