SUMMARY = "LiteX boards files"
HOMEPAGE = "https://github.com/litex-hub/litex-boards"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-2-Clause;md5=cb641bc04cda31daea161b1bc15da69f"

SRC_URI = "git://github.com/litex-hub/litex-boards;protocol=https"
SRCREV = "03accabc257ca06dd608db06bde8d0100c1e68de"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "litex"
RDEPENDS_${PN} += "litedram"
RDEPENDS_${PN} += "liteeth"
RDEPENDS_${PN} += "litesata"
RDEPENDS_${PN} += "litepcie"
RDEPENDS_${PN} += "litehyperbus"

BBCLASSEXTEND = "native nativesdk"
