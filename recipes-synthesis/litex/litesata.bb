SUMMARY = "Small footprint and configurable SATA core"
HOMEPAGE = "https://github.com/enjoy-digital/litesata"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10bd765c3601dc2c0b4cbe1541ccf3a3"

SRC_URI = "git://github.com/enjoy-digital/litesata;protocol=https"
SRCREV = "b36d3a33fbbfcffdb77a7a9e05bc8121387858d3"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
