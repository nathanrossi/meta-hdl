DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "abfe31d5d22a0ed1cc6ef32cf73fc1826b090b1c"

SRC_URI += " \
        file://0001-CMakeLists.txt-Rework-Boost-python-version-checking.patch \
        "

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit cmake python3native

DEPENDS += "python3 boost libeigen"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

BBCLASSEXTEND = "native nativesdk"

