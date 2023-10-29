DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/YosysHQ/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https;branch=master"
SRCREV = "36c615d1740473cc3574464c7f0bed44da20e5b6"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

