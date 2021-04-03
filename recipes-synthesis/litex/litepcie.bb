SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3e08966f83e2f6fe04775cf6da6038d"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https"
SRCREV = "c4780c3140effa8d24d57294e651a800daabafe7"
PV = "2020.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
