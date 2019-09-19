SUMMARY = "draws an SVG schematic from a JSON netlist"
HOMEPAGE = "https://github.com/nturley/netlistsvg"
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=47d473badfb26282ffa50e1e4d3262ed"

SRC_URI = "git://github.com/nturley/netlistsvg;protocol=https"
SRCREV = "7a7aea83b3b4947f8e65c1f6d734fec52781ff76"

# npm uses 0.0.1 as default version
PV = "0.0.1"

inherit npm

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"
