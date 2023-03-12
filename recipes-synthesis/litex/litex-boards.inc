SUMMARY = "LiteX boards files"
HOMEPAGE = "https://github.com/litex-hub/litex-boards"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"

SRC_URI = "git://github.com/litex-hub/litex-boards;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "litex"
RDEPENDS:${PN} += "litedram"
RDEPENDS:${PN} += "liteeth"
RDEPENDS:${PN} += "litesata"
RDEPENDS:${PN} += "litepcie"
RDEPENDS:${PN} += "litehyperbus"

BBCLASSEXTEND = "native nativesdk"
