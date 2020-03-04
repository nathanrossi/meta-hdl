SUMMARY = "Small footprint and configurable embedded FPGA logic analyzer"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2722a7b107e1e687ab0de4d63ac5f8ca"

SRC_URI = "git://github.com/enjoy-digital/litescope;protocol=https"
SRCREV = "b3d1e6938f42045ade1fcb10aa2498722a4ea041"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
