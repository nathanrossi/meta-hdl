SUMMARY = "Small footprint and configurable SPI core "
HOMEPAGE = "https://github.com/litex-hub/litespi"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b80bf324f960f268abbe81194cc61789"

SRC_URI = "git://github.com/litex-hub/litespi;protocol=https;branch=master"
SRCREV = "4a8e149af40c15b5dd11fceda4e85b7b6cef907a"
PV = "2021.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

# RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
