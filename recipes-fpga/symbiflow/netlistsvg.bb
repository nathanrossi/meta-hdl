SUMMARY = "draws an SVG schematic from a JSON netlist"
HOMEPAGE = "https://github.com/nturley/netlistsvg"
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://LICENSE;md5=47d473badfb26282ffa50e1e4d3262ed"

SRC_URI = "git://github.com/nturley/netlistsvg;protocol=https;branch=master"
SRCREV = "d3518f1b5c996a876b72a1a984bb39d3572c47dc"

PV = "1.0.2"

inherit npm

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native"
