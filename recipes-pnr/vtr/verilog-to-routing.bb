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
SRCREV = "8980e46218542888fac879961b13aa7b0fba8432"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS += "bison-native flex-native"
DEPENDS += "readline ncurses"
# needed by vpr (for GUI)
DEPENDS += "fontconfig libice libsm libx11 libxcb libxext libxft"
# for libseasygl
DEPENDS += "cairo"

# set the ARCHFLAGS for internal abc to avoid execution of arch_flags during build
export ARCHFLAGS = "-DABC_USE_STDINT_H=1"

do_install_append () {
    # fix up code archives being in bindir
    install -d ${D}${libdir}
    mv ${D}${bindir}/*.a ${D}${libdir}/
}

# export capnp dirs to sysroot
SYSROOT_DIRS_append = " ${prefix}/capnp"

# nativesdk support not functional due to X deps
BBCLASSEXTEND = "native"
