SUMMARY = "Small footprint and configurable SPI core "
HOMEPAGE = "https://github.com/litex-hub/litespi"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=99cb24c95072af16a66df894e8b495b9"

SRC_URI = "git://github.com/litex-hub/litespi;protocol=https;branch=master"
SRCREV = "63c815fe7c0c28b132a2407d836b7d8f80ea7a61"
PV = "2022.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

# RDEPENDS:${PN} += "liteiclink"

BBCLASSEXTEND = "native nativesdk"
