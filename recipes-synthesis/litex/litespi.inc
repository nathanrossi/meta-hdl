SUMMARY = "Small footprint and configurable SPI core "
HOMEPAGE = "https://github.com/litex-hub/litespi"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"

SRC_URI = "git://github.com/litex-hub/litespi;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
