DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/daveshah1/nextpnr-xilinx"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "gitsm://github.com/daveshah1/nextpnr-xilinx;protocol=https;branch=xilinx-upstream"
SRCREV = "d40ffba7b34d0bd6ae7d62d1bf849f8ec4d1da78"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

