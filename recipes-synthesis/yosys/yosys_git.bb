DESCRIPTION = "Yosys, a framework for Verilog RTL synthesis"
HOMEPAGE = "http://www.clifford.at/yosys/"
LICENSE = "ISC"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=d58b3b3d2af4ff73cc744b083d19261d"

SRC_URI = "git://github.com/YosysHQ/yosys;protocol=https"
SRCREV = "3209c0762a560d68ce7aef00942a8b3e440d5a61"

S = "${WORKDIR}/git"

PV = "0.9+git${SRCPV}"

inherit pkgconfig

DEPENDS = " \
		python3-native \
		bison-native \
		flex-native \
		gawk-native \
		tcl-native \
		berkeley-abc \
		readline \
		tcl \
		libffi \
		"

PACKAGE_BEFORE_PN += "${PN}-smtbmc"

RDEPENDS_${PN} += "berkeley-abc bash"
RDEPENDS_${PN}-smtbmc += "python3-core python3-resource python3-threading"

FILES_${PN}-smtbmc += " \
		${bindir}/yosys-smtbmc \
		${datadir}/yosys/python3/* \
		"

# OE modifies target tcl to populate includes into subdirectory but never sets that up in .pc
CXXFLAGS_append = " -I=${includedir}/tcl8.6"

do_configure_append () {
	# config build for GCC
	make config-gcc

	# remove posions
	sed -i 's/-I$(PREFIX)\/include//g' ${S}/Makefile
	sed -i 's/-L$(LIBDIR)//g' ${S}/Makefile
}

# use abc from PATH
export ABCEXTERNAL = "abc"

do_compile() {
	# force make variables for CXX and LD, the makefile forces them instead of defaulting
	oe_runmake PREFIX="${prefix}" PRETTY=0 CXX="${CXX}" LD="${CC}"
}

do_install() {
	# override STRIP to prevent the makefile from stripping during the install
	oe_runmake PREFIX="${prefix}" PRETTY=0 DESTDIR="${D}" STRIP="echo" install
}

BBCLASSEXTEND = "native nativesdk"
