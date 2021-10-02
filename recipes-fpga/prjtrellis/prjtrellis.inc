DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https"
SRCREV = "03e0070f263fbe31c247de61d259544722786210"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

