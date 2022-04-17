SUMMARY = "Library to convert FASM files to bistream"
HOMEPAGE = "https://github.com/chipsalliance/f4pga-xc-fasm"
LICENSE = "Apache-2.0"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/chipsalliance/f4pga-xc-fasm;protocol=https;branch=master"
SRCREV = "25dc605c9c0896204f0c3425b52a332034cf5e5c"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "python3-intervaltree"
RDEPENDS:${PN} += "python3-simplejson"
RDEPENDS:${PN} += "python3-textx"
RDEPENDS:${PN} += "prjxray"
RDEPENDS:${PN} += "python3-fasm"

BBCLASSEXTEND = "native nativesdk"
