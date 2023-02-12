SUMMARY = "Small footprint and configurable HyperBus core"
HOMEPAGE = "https://github.com/litex-hub/litehyperbus"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e40877df301ec6de075d51f4b7571d62"

SRC_URI = "git://github.com/litex-hub/litehyperbus;protocol=https;branch=master"
SRCREV = "76454e4c0c304a34b9915722de750887c3abbe89"
PV = "2022.04+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
