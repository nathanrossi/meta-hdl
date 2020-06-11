SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0745e1d0890d8914926d9bbe62ae78d6"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https"
SRCREV = "27572e742b25171c8e67afad3cb1ff0eae7c2d1b"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
