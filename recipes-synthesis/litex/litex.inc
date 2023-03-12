SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"

SRC_URI = "git://github.com/enjoy-digital/litex;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "python3-pyserial"
RDEPENDS:${PN} += "python3-requests"
RDEPENDS:${PN} += "migen"
RDEPENDS:${PN} += "litescope"

BBCLASSEXTEND = "native nativesdk"
