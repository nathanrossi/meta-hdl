SUMMARY = "Small footprint and configurable Ethernet core"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70ab32fd17078e7007b69f144468f821"

SRC_URI = "git://github.com/enjoy-digital/liteeth;protocol=https"
SRCREV = "8ecc3ca6d9476dd1a8b32b2b6bf3584cd6dcfc02"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
