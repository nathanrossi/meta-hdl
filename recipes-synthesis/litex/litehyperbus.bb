SUMMARY = "Small footprint and configurable HyperBus core"
HOMEPAGE = "https://github.com/litex-hub/litehyperbus"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24f42c68cd88bddb4037957029d69817"

SRC_URI = "git://github.com/litex-hub/litehyperbus;protocol=https"
SRCREV = "9f118e83c819a20e1348d295cc9a251cc006f8f8"
PV = "2021.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
