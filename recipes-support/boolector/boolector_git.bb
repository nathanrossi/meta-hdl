DESCRIPTION = "Boolector is a Satisfiability Modulo Theories (SMT) solver for \
the theories of fixed-size bit-vectors, arrays and uninterpreted functions."
HOMEPAGE = "https://boolector.github.io/"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=7c2823721d5a44205b97299506846bea"
SRC_URI = "git://github.com/Boolector/boolector;protocol=https;branch=master"
SRCREV = "13a8a06d561041cafcaf5458e404c1ec354b2841"

S = "${WORKDIR}/git"

BOOLECTOR_VERSION = "3.1.0"
PV = "${BOOLECTOR_VERSION}+git${SRCPV}"

inherit cmake python3native

EXTRA_OECMAKE += "-DBUILD_SHARED_LIBS=ON"

DEPENDS = "\
        btor2tools \
        cryptominisat \
        "

do_configure:prepend() {
    # boolector forces custom install path for btor2parser.h without a subdir
    # (see contrib/setup-btor2tools.sh in boolector)
    ln -sf btor2parser/btor2parser.h ${STAGING_INCDIR}/btor2parser.h
}

do_install:append() {
    # Fix QA Issue: -dev package contains non-symlink .so:
    for lib in $(ls ${D}${libdir}/*.so); do
        if ! [ -L ${lib} ]; then
            mv ${lib} ${lib}.${BOOLECTOR_VERSION};
            ln -sfr ${lib}.${BOOLECTOR_VERSION} ${lib};
        fi
    done
}

BBCLASSEXTEND = "native nativesdk"
