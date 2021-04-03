SUMMARY = "Small footprint and configurable SATA core"
HOMEPAGE = "https://github.com/enjoy-digital/litesata"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6a21e9a29f3977840b72dab37a4e0bb"

SRC_URI = "git://github.com/enjoy-digital/litesata;protocol=https"
SRCREV = "4320f54218bdc765e247230e2162d418dff57751"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
