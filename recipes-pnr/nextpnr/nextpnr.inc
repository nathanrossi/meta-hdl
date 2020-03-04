DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "a957e9039642c1f5fe4270895d7a23452fab281b"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit cmake python3native

DEPENDS += "python3 boost libeigen"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

BBCLASSEXTEND = "native nativesdk"

