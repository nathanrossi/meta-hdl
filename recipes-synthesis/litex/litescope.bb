SUMMARY = "Small footprint and configurable embedded FPGA logic analyzer"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab969670c1dff560d9f90d0b1846c269"

SRC_URI = "git://github.com/enjoy-digital/litescope;protocol=https"
SRCREV = "08072a78ba1157d106d305bdc93cd9c5d35bab98"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
