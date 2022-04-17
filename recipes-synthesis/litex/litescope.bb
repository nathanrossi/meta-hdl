SUMMARY = "Small footprint and configurable embedded FPGA logic analyzer"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c779474112984b3fbcfaba3dc8f3f3b4"

SRC_URI = "git://github.com/enjoy-digital/litescope;protocol=https;branch=master"
SRCREV = "d1c480d0e8c1e6188cccab4a0621c714376d591c"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
