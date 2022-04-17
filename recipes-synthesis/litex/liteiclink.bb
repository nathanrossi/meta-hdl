SUMMARY = "Small footprint and configurable Inter-Chip communication cores"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75a811b254c8b0477ed1864f5703e846"

SRC_URI = "git://github.com/enjoy-digital/liteiclink;protocol=https;branch=master"
SRCREV = "f3eadbd1165a8055c7c2c98b72c450ca1bdcc09f"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
