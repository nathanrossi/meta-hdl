SUMMARY = "Small footprint and configurable HyperBus core"
HOMEPAGE = "https://github.com/litex-hub/litehyperbus"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d20af18e6906a2962e7369cca2d194c0"

SRC_URI = "git://github.com/litex-hub/litehyperbus;protocol=https"
SRCREV = "b7d57e9c63b1f213f63c05bc05bdcfb97c4ff284"
PV = "2020.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
