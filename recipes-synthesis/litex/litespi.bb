SUMMARY = "Small footprint and configurable SPI core "
HOMEPAGE = "https://github.com/litex-hub/litespi"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70ab32fd17078e7007b69f144468f821"

SRC_URI = "git://github.com/litex-hub/litespi;protocol=https"
SRCREV = "4cb907881bb75999e4c6bb68e211dd5cfc301de9"
PV = "2021.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

# RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
