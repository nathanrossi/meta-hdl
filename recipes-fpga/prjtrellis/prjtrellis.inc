DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https"
SRCREV = "dff1cbcb1bd30de7e96f8a059f2e19be1bb2e44d"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

