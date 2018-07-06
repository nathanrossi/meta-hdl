DESCRIPTION = "Project IceStorm aims at reverse engineering, documenting and tools for Lattice iCE40 FPGAs"
HOMEPAGE = "http://www.clifford.at/icestorm/"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://README;beginline=8;endline=18;md5=8104f3444ed967701dafaeab14d572b9"

SRC_URI = "git://github.com/cliffordwolf/icestorm;protocol=https"
SRCREV = "16a9962fb16d50683a7b69835194703c48bc1759"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit pkgconfig

# diffutils (provided by hosttools)
DEPENDS = "gawk-native python3-native"

PACKAGECONFIG = "icebox icetime iceprog"
PACKAGECONFIG[iceprog] = ",,libftdi"
PACKAGECONFIG[icetime] = ",,,python3"
PACKAGECONFIG[icebox] = ",,,python3"

MAKE_SUBDIRS = "icepack icemulti icepll icebram ${PACKAGECONFIG}"

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
