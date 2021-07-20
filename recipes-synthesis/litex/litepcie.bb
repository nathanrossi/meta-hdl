SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3e08966f83e2f6fe04775cf6da6038d"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https"
SRCREV = "68334fd93d078c77521a8000cb41311ab360a256"
PV = "2020.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
