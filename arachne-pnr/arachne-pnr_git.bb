DESCRIPTION = "Arachne Place and Route tool for iCE40 FPGAs"
HOMEPAGE = "https://github.com/cseed/arachne-pnr"
LICENSE = "MIT"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bb65c4bee625d497d43f50e6dacb4e2"

SRC_URI = "git://github.com/cseed/arachne-pnr;protocol=https"
SRCREV = "7e135edb31feacde85ec5b7e5c03fc9157080977"

PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

# needs coreutils for 'sum'
# icestorm is needed for chipdb
# depends on itself for processing chipdb content
DEPENDS = "coreutils-native icestorm-native arachne-pnr-native"

# export the path for icestorm chipdb
export ICEBOX = "${STAGING_DIR_NATIVE}${datadir_native}/icebox"

# set executable for use when processing chipdb
ARACHNEPNR_class-native = "${B}/bin/arachne-pnr"
ARACHNEPNR = "arachne-pnr"

do_compile () {
	oe_runmake bin/arachne-pnr

	# manually build the chipdb.bin files
	mkdir -p ${B}/share/arachne-pnr
	for i in 384 1k 8k; do
		${ARACHNEPNR} -d 384 -c ${ICEBOX}/chipdb-$i.txt --write-binary-chipdb ${B}/share/arachne-pnr/chipdb-$i.bin
	done
}

do_install () {
	oe_runmake PREFIX="${prefix}" DESTDIR="${D}" install
}

# populate the chipdb into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/arachne-pnr"

BBCLASSEXTEND = "native nativesdk"
