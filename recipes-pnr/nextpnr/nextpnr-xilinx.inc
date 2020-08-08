DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/daveshah1/nextpnr-xilinx"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "gitsm://github.com/daveshah1/nextpnr-xilinx;protocol=https;branch=xilinx-upstream"
SRCREV = "2425dde6a3cc65d990e0bb06bbe8a40e661b0b45"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

