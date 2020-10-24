SUMMARY = "Small footprint and configurable Inter-Chip communication cores"
HOMEPAGE = "http://www.enjoy-digital.fr/cores.html"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6244c5230de1fe6eb6cdd3c66ec64888"

SRC_URI = "git://github.com/enjoy-digital/liteiclink;protocol=https"
SRCREV = "1793e81640a747b198206e7eafbed43d8bcd4c85"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
