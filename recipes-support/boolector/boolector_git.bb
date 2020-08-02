DESCRIPTION = "Boolector is a Satisfiability Modulo Theories (SMT) solver for \
the theories of fixed-size bit-vectors, arrays and uninterpreted functions."
HOMEPAGE = "https://boolector.github.io/"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=6b4c24fbbe55ff1229acdef3dec0298b"
SRC_URI = "git://github.com/Boolector/boolector;protocol=https"
SRCREV = "1dda9a78661a642f4229f7e4334bb661351d1225"

S = "${WORKDIR}/git"

BOOLECTOR_VERSION = "3.1.0"
PV = "${BOOLECTOR_VERSION}+git${SRCPV}"

inherit cmake python3native

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"

DEPENDS = "\
        btor2tools \
        cryptominisat \
        "

do_install_append() {
	# Fix QA Issue: -dev package contains non-symlink .so:
	for lib in $(ls ${D}${libdir}/*.so); do
		if ! [ -L ${lib} ]; then
			mv ${lib} ${lib}.${BOOLECTOR_VERSION};
			ln -sfr ${lib}.${BOOLECTOR_VERSION} ${lib};
		fi
	done
}

BBCLASSEXTEND = "native nativesdk"
