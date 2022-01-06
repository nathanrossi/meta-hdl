SUMMARY = "Small footprint and configurable HyperBus core"
HOMEPAGE = "https://github.com/litex-hub/litehyperbus"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e40877df301ec6de075d51f4b7571d62"

SRC_URI = "git://github.com/litex-hub/litehyperbus;protocol=https;branch=master"
SRCREV = "a950fa768d255869a36d8c91d7bd4dbd3c47a9b2"
PV = "2021.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
