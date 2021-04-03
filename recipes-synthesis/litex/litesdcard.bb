SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=327975928420d679cbf3fb2ee14e33e6"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https"
SRCREV = "fc91e0ec4653b5d8a5a67eec22b7122d1ea3c807"
PV = "2020.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
