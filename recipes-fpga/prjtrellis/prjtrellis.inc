DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/YosysHQ/prjtrellis;protocol=https;branch=master"
SRCREV = "2f06397673bbca3da11928d538b8ab7d01c944c6"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

