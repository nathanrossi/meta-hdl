DESCRIPTION = "Icarus Verilog is a Verilog simulaton and synthesis tool"
HOMEPAGE = "http://iverilog.icarus.com/"
LICENSE = "GPL-2.0-or-later"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/steveicarus/iverilog.git;protocol=https;branch=master"
SRCREV = "72b0498af473f06d6cfaafa30659e7ff87d1d5a9"

S = "${WORKDIR}/git"

PV = "11.0+git${SRCPV}"

inherit autotools

EXTRA_OEMAKE:append = ' HOSTCC="${BUILD_CC}" HOSTCFLAGS="${BUILD_CFLAGS}"'

export CXXCPP = "${CPP}"

# issues with install races
PARALLEL_MAKEINST = ""

# don't regen aclocal or autoheader
EXTRA_AUTORECONF += "--exclude=aclocal --exclude=autoheader"

DEPENDS = " \
	flex-native bison-native \
	gperf-native \
	readline ncurses \
	zlib \
	"

# expects host readline during configure
DEPENDS += "readline-native"

FILES:${PN} += "${libdir}/ivl"

do_configure:prepend () {
	# prevent use of CFLAGS with BUILDCC
	sed -i 's/BUILDCC.\s\+..CFLAGS/BUILDCC/' ${S}/Makefile.in
}

BBCLASSEXTEND = "native nativesdk"
