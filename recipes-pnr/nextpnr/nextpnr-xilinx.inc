DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/daveshah1/nextpnr-xilinx"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d8fc0d7b7d3514b72095e01b9dca7876"

SRC_URI = "gitsm://github.com/gatecat/nextpnr-xilinx;protocol=https;branch=xilinx-upstream"
SRCREV = "f79387599b420bfadf3dc218e55e1e77b1edb304"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"
