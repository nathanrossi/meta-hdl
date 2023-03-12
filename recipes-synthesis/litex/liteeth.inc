SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
