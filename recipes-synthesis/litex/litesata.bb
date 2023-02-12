SUMMARY = "Small footprint and configurable SATA core"
HOMEPAGE = "https://github.com/enjoy-digital/litesata"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06961f9174ef2f11bff41b45f906eb5f"

SRC_URI = "git://github.com/enjoy-digital/litesata;protocol=https;branch=master"
SRCREV = "e222360cfcf9e26d6039eb04785fa50b1256a40e"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
