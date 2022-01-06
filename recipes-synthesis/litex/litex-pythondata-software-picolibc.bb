SUMMARY = "LiteX - picolibc"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-picolibc"
SECTION = "devel/hdl"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

SRC_URI = "gitsm://github.com/litex-hub/pythondata-software-picolibc;protocol=https;branch=master"
SRCREV = "44b87f9de0330c2ae40ec3d5ac6883630da15b18"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
