SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9f3746ccc6348e52617a7a527ae22ff8"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https;branch=master"
SRCREV = "15955d88ad2390dfa46f4846f7fc22bd642738db"
PV = "2022.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
