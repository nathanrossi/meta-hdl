SUMMARY = "draws an SVG schematic from a JSON netlist"
HOMEPAGE = "https://github.com/nturley/netlistsvg"
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=47d473badfb26282ffa50e1e4d3262ed"

SRC_URI = "git://github.com/nturley/netlistsvg;protocol=https"
SRCREV = "86981cef0815cc8c59900f008cc713140c164830"

# npm uses 0.0.1 as default version
PV = "0.0.1"

inherit npm

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"
