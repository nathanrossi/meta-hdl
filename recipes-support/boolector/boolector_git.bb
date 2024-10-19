DESCRIPTION = "Boolector is a Satisfiability Modulo Theories (SMT) solver for \
the theories of fixed-size bit-vectors, arrays and uninterpreted functions."
HOMEPAGE = "https://boolector.github.io/"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=7c2823721d5a44205b97299506846bea"
SRC_URI = "git://github.com/Boolector/boolector;protocol=https;branch=master"
SRCREV = "43dae91c1070e5e2633e036ebd75ffb13fe261e1"

S = "${WORKDIR}/git"

BOOLECTOR_VERSION = "3.2.4"
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

    # Prevent buildpaths from leaking into header defines.
    # Some of these defines are only used by the test code.
    sed -i \
        -e 's#\(define BTOR_CXXFLAGS,*\).*#\1 "${CXXFLAGS}"#g' \
        -e 's#\(define BTOR_CFLAGS,*\).*#\1 "${CFLAGS}"#g' \
        -e '/BTOR_SRC_DIR/d' \
        -e '/BTOR_BIN_DIR/d' \
        -e '/BTOR_TEST_DIR/d' \
        -e '/BTOR_LOG_DIR/d' \
        -e '/BTOR_OUT_DIR/d' \
        -e '/BTOR_CONTRIB_DIR/d' \
        ${S}/src/btorconfig.h.in
    sed -i \
        -e 's#-fcanon-prefix-map ##g' \
        -e 's#-fdebug-prefix-map=[^[:space:]]* ##g' \
        -e 's#-fmacro-prefix-map=[^[:space:]]* ##g' \
        ${S}/src/btorconfig.h.in

    # Only replace target/nativesdk STAGING_DIR_TARGET, -native is rewritten by
    # staging.bbclass
    if [ -n "${STAGING_DIR_TARGET}" ]; then
        sed -i \
            -e 's#--sysroot=${STAGING_DIR_TARGET} ##g' \
            -e 's#${STAGING_DIR_TARGET}##g' \
            ${S}/src/btorconfig.h.in
    fi
}

do_install:append() {
    # Prevent buildpaths from leaking into cmake includes, only replace
    # target/nativesdk STAGING_DIR_TARGET, -native is rewritten by
    # staging.bbclass
    if [ -n "${STAGING_DIR_TARGET}" ]; then
        sed -i 's#${STAGING_DIR_TARGET}##g' \
            ${D}${libdir}/cmake/Boolector/BoolectorTargets.cmake
    fi

    # Fix QA Issue: -dev package contains non-symlink .so:
    for lib in $(ls ${D}${libdir}/*.so); do
        if ! [ -L ${lib} ]; then
            mv ${lib} ${lib}.${BOOLECTOR_VERSION};
            ln -sfr ${lib}.${BOOLECTOR_VERSION} ${lib};
        fi
    done
}

BBCLASSEXTEND = "native nativesdk"
