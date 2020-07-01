DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d0edad23e5bde54888d63370c9775534"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "c0901fb972e34d2d16b3ac0074a18542ba1c448d"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake python3native

DEPENDS += "python3 boost libeigen"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

BBCLASSEXTEND = "native nativesdk"

python () {
    bpn = d.getVar("BPN")
    if not bb.data.inherits_class("native", d) and "bbasm" not in bpn:
        # cross bbasm specific setup
        d.appendVar("DEPENDS", " nextpnr-bbasm-native")
        d.appendVar("EXTRA_OECMAKE", " -DBBA_IMPORT=${RECIPE_SYSROOT_NATIVE}${includedir_native}/nextpnr/bba-export.cmake")
}

