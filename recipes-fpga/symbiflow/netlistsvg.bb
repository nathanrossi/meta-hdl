SUMMARY = "draws an SVG schematic from a JSON netlist"
HOMEPAGE = "https://github.com/nturley/netlistsvg"
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=47d473badfb26282ffa50e1e4d3262ed"

SRC_URI = "git://github.com/nturley/netlistsvg;protocol=https"
SRCREV = "9a44cea281fe0fa01094535f57179d0358af992f"

# npm uses 0.0.1 as default version
PV = "0.0.1"

inherit npm

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"
