SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=327975928420d679cbf3fb2ee14e33e6"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https"
SRCREV = "b55de0edcee67556c53e0b7f650fff0a895ffeef"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
