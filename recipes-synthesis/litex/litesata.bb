SUMMARY = "Small footprint and configurable SATA core"
HOMEPAGE = "https://github.com/enjoy-digital/litesata"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10bd765c3601dc2c0b4cbe1541ccf3a3"

SRC_URI = "git://github.com/enjoy-digital/litesata;protocol=https"
SRCREV = "1e3573b07d382eac50ef764fd839009bf90cb8ce"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
