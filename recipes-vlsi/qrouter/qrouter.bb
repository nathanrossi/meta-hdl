SUMMARY = "Qrouter is a tool to generate metal layers and vias to physically connect together a netlist in a VLSI fabrication technology"
HOMEPAGE = "http://opencircuitdesign.com/qrouter/"
LICENSE = "GPL-2.0"
SECTION = "devel/vlsi"

LIC_FILES_CHKSUM = "file://COPYRIGHT;beginline=2;endline=16;md5=0b4522f6409bdb47b8c21417a88918ee"

SRC_URI = "git://github.com/RTimothyEdwards/qrouter;protocol=https"
SRCREV = "cf11d1139ca7800e7844e2fd15fefa424c72a8b3"

S = "${WORKDIR}/git"

PV = "1.4.49+git${SRCPV}"

inherit autotools autotools-brokensep
inherit features_check

DEPENDS += "tcl tk"
# this recipe and tk only supports x11
REQUIRED_DISTRO_FEATURES = "x11"

BBCLASSEXTEND = "native nativesdk"
