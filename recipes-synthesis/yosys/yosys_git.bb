DESCRIPTION = "Yosys, a framework for Verilog RTL synthesis"
HOMEPAGE = "http://www.clifford.at/yosys/"
LICENSE = "ISC"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://README.md;beginline=2;endline=16;md5=8248ba3ca46fd8ff645653ed33b79217"

SRC_URI = "git://github.com/YosysHQ/yosys;protocol=https"
SRCREV = "b894187cf66dfa346eddeccf42c38c0635db9524"

S = "${WORKDIR}/git"

PV = "0.8+git${SRCPV}"

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

RDEPENDS_${PN} += "berkeley-abc bash"

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
