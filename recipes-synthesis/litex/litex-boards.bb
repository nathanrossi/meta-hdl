SUMMARY = "LiteX boards files"
HOMEPAGE = "https://github.com/litex-hub/litex-boards"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/litex-boards;protocol=https;branch=master"
SRCREV = "9e18d9bc34bafb18839512a55dc66ba62e0d4824"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "litex"
RDEPENDS:${PN} += "litedram"
RDEPENDS:${PN} += "liteeth"
RDEPENDS:${PN} += "litesata"
RDEPENDS:${PN} += "litepcie"
RDEPENDS:${PN} += "litehyperbus"

BBCLASSEXTEND = "native nativesdk"
