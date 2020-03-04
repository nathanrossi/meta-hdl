HOMEPAGE = "https://github.com/daveshah1/nextpnr-xilinx"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "gitsm://github.com/daveshah1/nextpnr-xilinx;protocol=https;branch=xilinx-upstream"
SRCREV = "c2527afd729ecb67192c78b713cf242d9f1a5320"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

