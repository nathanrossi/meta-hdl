SUMMARY = "Small footprint and configurable Inter-Chip communication cores"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75a811b254c8b0477ed1864f5703e846"

SRC_URI = "git://github.com/enjoy-digital/liteiclink;protocol=https;branch=master"
SRCREV = "7718e96fa404be5718df05df717643e5e1213a1a"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
