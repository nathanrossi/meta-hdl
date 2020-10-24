DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d0edad23e5bde54888d63370c9775534"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "5e53a182921dad0e128186a1fe8766062c7cae61"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

