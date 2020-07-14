DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d0edad23e5bde54888d63370c9775534"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "44007eab6f6a7f20c257bb109bbc8f66a5fde12d"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

