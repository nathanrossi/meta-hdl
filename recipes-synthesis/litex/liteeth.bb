SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d5fe6a63c47e2580771cc56883d2ed64"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https;branch=master"
SRCREV = "97dccdb294fa2081536f26f4ef6235c004007432"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
