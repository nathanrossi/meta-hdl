SUMMARY = "Small footprint and configurable SDCard core"
HOMEPAGE = "https://github.com/enjoy-digital/litesdcard"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84fb2139cffab4ac444f26bfd284a2bb"

SRC_URI = "git://github.com/enjoy-digital/litesdcard;protocol=https"
SRCREV = "4edf05e627a8b5f03e632215accec8ab588737e3"
PV = "2021.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
