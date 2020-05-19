SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3e08966f83e2f6fe04775cf6da6038d"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https"
SRCREV = "b0e8383d6179ee81a708204d4db9263d6fadc6c8"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
