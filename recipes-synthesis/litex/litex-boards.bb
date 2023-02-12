SUMMARY = "LiteX boards files"
HOMEPAGE = "https://github.com/litex-hub/litex-boards"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=44c881049d568ad42c4c81ed51fa6515"

SRC_URI = "git://github.com/litex-hub/litex-boards;protocol=https;branch=master"
SRCREV = "d8d757eec4decd50d18101c8ceadb603b777e920"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "litex"
RDEPENDS:${PN} += "litedram"
RDEPENDS:${PN} += "liteeth"
RDEPENDS:${PN} += "litesata"
RDEPENDS:${PN} += "litepcie"
RDEPENDS:${PN} += "litehyperbus"

BBCLASSEXTEND = "native nativesdk"
