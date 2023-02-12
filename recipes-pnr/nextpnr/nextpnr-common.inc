DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d8fc0d7b7d3514b72095e01b9dca7876"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https;branch=master"
SRCREV = "ba3801e010cf01a702c4829a66e955ab2ddc4423"
PV = "0.5+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

