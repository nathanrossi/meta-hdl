DESCRIPTION = "Project IceStorm aims at reverse engineering, documenting and tools for Lattice iCE40 FPGAs"
HOMEPAGE = "http://www.clifford.at/icestorm/"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=18;md5=8104f3444ed967701dafaeab14d572b9"

SRC_URI = "git://github.com/cliffordwolf/icestorm;protocol=https"
SRCREV = "710470f9d28891698aa015eb8c101d1cf75fc332"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit pkgconfig

# diffutils (provided by hosttools)
DEPENDS = "gawk-native python3-native libftdi"

MAKE_SUBDIRS = "icebox icepack iceprog icemulti icepll icetime icebram"

do_configure () {
	# remove posioned directories
	sed -i 's/-I\S*//g' ${S}/config.mk
	sed -i 's/-L\S*//g' ${S}/iceprog/Makefile
}

do_compile () {
	for i in ${MAKE_SUBDIRS}; do
		oe_runmake -C ${B}/$i PREFIX="${prefix}"
	done
}

do_install () {
	for i in ${MAKE_SUBDIRS}; do
		oe_runmake -C ${B}/$i PREFIX="${prefix}" DESTDIR="${D}" install
	done
}

# populate the chipdb into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/icebox"

# package the icebox chipdb files
FILES_${PN} += "${datadir}/icebox"

BBCLASSEXTEND = "native nativesdk"
