DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/YosysHQ/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https;branch=master"
SRCREV = "6280c4d1e73b50935b61b24dbaaf2a6a355be001"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

