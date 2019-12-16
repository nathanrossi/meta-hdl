DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=551f2364fa8248634340a80a748e986f"

SRC_URI = "git://github.com/SymbiFlow/prjtrellis;protocol=https"
SRCREV = "4e0b21597774084fca16762d5f5d88573420f1bf"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI += " \
        file://0001-libtrellis-CMakeLists.txt-Run-git-describe-in-source.patch \
        file://0002-libtrellis-CMakeLists.txt-Rework-Boost-python-versio.patch \
        "

