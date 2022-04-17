DESCRIPTION = "SymbiYosys (sby) -- Front-end for Yosys-based formal verification flows"
HOMEPAGE = "https://github.com/YosysHQ/sby"
LICENSE = "ISC"
SECTION = "devel/verification"

LIC_FILES_CHKSUM = "file://COPYING;md5=e6376e491ece4189d8d21042729747ce"

SRC_URI = "git://github.com/YosysHQ/sby;protocol=https;branch=master"
SRCREV = "1e1aea0b1ecd523c1e4a3c6566fb078f0dbfdfeb"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

RDEPENDS:${PN} += "python3-core"

do_compile[noexec] = "1"

do_install() {
    oe_runmake PREFIX="${prefix}" DESTDIR="${D}" install
}

FILES:${PN} += "${datadir}/yosys/python3/*"

BBCLASSEXTEND = "native nativesdk"
