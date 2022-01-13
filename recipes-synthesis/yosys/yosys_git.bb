DESCRIPTION = "Yosys, a framework for Verilog RTL synthesis"
HOMEPAGE = "http://www.clifford.at/yosys/"
LICENSE = "ISC"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=d1b1d1933fedc05c99106d80dd51dd02"

SRC_URI = "git://github.com/YosysHQ/yosys;protocol=https;branch=master"
SRCREV = "59a71503448401d2476cf0872808e0a99c3a4d81"

S = "${WORKDIR}/git"

PV = "0.12+git${SRCPV}"

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

RDEPENDS:${PN} += "berkeley-abc bash"
RDEPENDS:${PN}-smtbmc += "python3-core python3-resource python3-threading"

FILES:${PN}-smtbmc += " \
		${bindir}/yosys-smtbmc \
		${datadir}/yosys/python3/* \
		"

# OE modifies target tcl to populate includes into subdirectory but never sets that up in .pc
CXXFLAGS:append = " -I=${includedir}/tcl8.6"

do_configure:append () {
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

# the yosys-config script is used to build plugins/etc.
SYSROOT_DIRS += "${bindir}"

BBCLASSEXTEND = "native nativesdk"
