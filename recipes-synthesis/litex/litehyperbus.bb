SUMMARY = "Small footprint and configurable HyperBus core"
HOMEPAGE = "https://github.com/litex-hub/litehyperbus"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e40877df301ec6de075d51f4b7571d62"

SRC_URI = "git://github.com/litex-hub/litehyperbus;protocol=https;branch=master"
SRCREV = "1cde460f24e06e30be7ac1cd361bb2d263528253"
PV = "2021.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
