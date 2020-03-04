SUMMARY = "The Verilog to Routing (VTR) project provides open-source CAD tools for FPGA architecture and CAD research."
HOMEPAGE = "https://verilogtorouting.org/"
LICENSE = "MIT"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=633281c5b6ab9bbb4b7dcbef1aa323db"

# upstream
#SRC_URI = "git://github.com/verilog-to-routing/vtr-verilog-to-routing;protocol=https"
#SRCREV = "6234d127aa8d781f9a773b2bcbf7f64afa3227f5"

# symbiflow version
SRC_URI = "git://github.com/SymbiFlow/vtr-verilog-to-routing;protocol=https;branch=master+wip"
SRCREV = "4214c377606e98fc8c13e3f6224bfe7f16da3640"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS += "bison-native flex-native"
DEPENDS += "readline ncurses"
# needed by vpr (for GUI)
DEPENDS += "fontconfig libice libsm libx11 libxcb libxext libxft"
# for libseasygl
DEPENDS += "cairo"

do_install_append () {
    # fix up code archives being in bindir
    install -d ${D}${libdir}
    mv ${D}${bindir}/*.a ${D}${libdir}/
}

# nativesdk support not functional due to X deps
BBCLASSEXTEND = "native"
