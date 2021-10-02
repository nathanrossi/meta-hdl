SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70ab32fd17078e7007b69f144468f821"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https"
SRCREV = "947ed037202a9982ed1c2e2b7f78cf8a31a23e87"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
