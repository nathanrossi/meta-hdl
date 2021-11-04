SUMMARY = "Small footprint and configurable Inter-Chip communication cores"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6244c5230de1fe6eb6cdd3c66ec64888"

SRC_URI = "git://github.com/enjoy-digital/liteiclink;protocol=https;branch=master"
SRCREV = "3d8ecdbcf9f0260292221ff63b0ad3f5e409a955"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
