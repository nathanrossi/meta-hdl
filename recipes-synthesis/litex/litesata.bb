SUMMARY = "Small footprint and configurable SATA core"
HOMEPAGE = "https://github.com/enjoy-digital/litesata"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7472087497557b85f53fe7e0a1e26db6"

SRC_URI = "git://github.com/enjoy-digital/litesata;protocol=https;branch=master"
SRCREV = "6441cd0d9545387f32687a962258d4a56ba93309"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
