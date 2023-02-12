SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26002bebf4fb75e22401233046399ea1"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https;branch=master"
SRCREV = "e18313834eff08295f643ff9790d8f5a5372f203"
PV = "2022.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
