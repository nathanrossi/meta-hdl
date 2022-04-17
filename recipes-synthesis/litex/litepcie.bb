SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=26002bebf4fb75e22401233046399ea1"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https;branch=master"
SRCREV = "2f8c1a29c5c8bdc352fb60e2b72981c91543ae06"
PV = "2021.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
