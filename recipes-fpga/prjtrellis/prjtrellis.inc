DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/YosysHQ/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https;branch=master"
SRCREV = "2dab0095e1a5691855b0955b329cb4946b6a13b8"
PV = "1.4+git${SRCPV}"

S = "${WORKDIR}/git"

