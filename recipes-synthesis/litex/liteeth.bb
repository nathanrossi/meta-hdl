SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d5fe6a63c47e2580771cc56883d2ed64"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https;branch=master"
SRCREV = "bc9162d578bec79003c5ce9bbf44cb14c9a25a0d"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
