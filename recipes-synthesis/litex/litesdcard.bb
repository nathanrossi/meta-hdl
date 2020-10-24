SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=327975928420d679cbf3fb2ee14e33e6"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https"
SRCREV = "0cb5ab5bab0bba9c3f3c900837bd39e745465aa2"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
