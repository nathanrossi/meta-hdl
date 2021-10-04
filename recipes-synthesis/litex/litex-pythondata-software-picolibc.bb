SUMMARY = "LiteX - picolibc"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-picolibc"
SECTION = "devel/hdl"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=0"

SRC_URI = "gitsm://github.com/litex-hub/pythondata-software-picolibc;protocol=https"
SRCREV = "e27c8a7ef8a8e75b6474823aae338efb1a2ca1a9"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
