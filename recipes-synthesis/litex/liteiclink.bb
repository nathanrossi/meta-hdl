SUMMARY = "Small footprint and configurable Inter-Chip communication cores"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75a811b254c8b0477ed1864f5703e846"

SRC_URI = "git://github.com/enjoy-digital/liteiclink;protocol=https;branch=master"
SRCREV = "fb7ddcfe9f7d6d5e53a2281e9aa29ead4f3c2477"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
