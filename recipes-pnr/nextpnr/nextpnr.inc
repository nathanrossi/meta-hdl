DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "206761b41589f218e7c3f1ebc360183f3d8c1c79"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit cmake python3native

DEPENDS += "python3 boost libeigen"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

do_configure_prepend() {
    # HACK: work around python35 missing check
    sed -i 's#COMPONENTS python36#COMPONENTS python35#' ${S}/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"

