DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d8fc0d7b7d3514b72095e01b9dca7876"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "1a5bb7b8abdf3eab38223b108c1a77c9a6381e79"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

