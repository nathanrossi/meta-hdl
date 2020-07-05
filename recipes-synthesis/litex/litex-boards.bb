SUMMARY = "LiteX boards files"
HOMEPAGE = "https://github.com/litex-hub/litex-boards"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/litex-boards;protocol=https"
SRCREV = "7b1bf9d74ac45aafb9d408c9340252ecc1025722"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
