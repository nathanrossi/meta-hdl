DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d8fc0d7b7d3514b72095e01b9dca7876"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https;branch=master"
SRCREV = "d6f54fd9df879643f795b3d96c25e6a84cf48a77"
PV = "0.6+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

