DESCRIPTION = "VHDL 2008/93/87 simulator"
HOMEPAGE = "http://ghdl.free.fr/"
SECTION = "devel/vhdl"
# ieee2008 libraries are Apache 2.0
LICENSE = "GPL-2.0 & Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=60d644347832d2dd9534761f6919e2a6"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/ghdl/ghdl;protocol=https;branch=master"
SRCREV = "89fc56f98e81a9fa390e4e80ddefff72e68237e7"
PV = "1.0.0+git${SRCPV}"

DEPENDS = "zlib"

CONFIGUREOPTS = "--prefix=${prefix} --srcdir=${S} --enable-libghdl --enable-synth --default-pic"

B = "${WORKDIR}/build"

CFLAGS:append = " -fno-strict-aliasing -fPIC"

GNATBIND = "gnatbind"
GNATLINK = "gnatlink --GCC=\"${CC} ${CFLAGS}\""
GNATMAKE = "gnatmake ${CFLAGS} ${LDFLAGS} --GCC=${CC} --GNATBIND=\"${GNATBIND}\" --GNATLINK=\"gnatlink-wrapped\""

EXTRA_OEMAKE += 'CC="${CC} ${CFLAGS}"'
EXTRA_OEMAKE += 'GNATMAKE="gnatmake-wrapped"'

do_configure() {
    # Create wrapper scripts for gnatmake/gnatlink in order to pass various
    # toolchain options. This is required because gnatmake does not correctly
    # pass --GNATLINK options (e.g. --GCC) to gnatlink.
    echo "#!/bin/sh\n${HOSTTOOLS_DIR}/${GNATMAKE} \$*" > ${STAGING_BINDIR_NATIVE}/gnatmake-wrapped
    chmod +x ${STAGING_BINDIR_NATIVE}/gnatmake-wrapped
    echo "#!/bin/sh\n${HOSTTOOLS_DIR}/${GNATLINK} \$*" > ${STAGING_BINDIR_NATIVE}/gnatlink-wrapped
    chmod +x ${STAGING_BINDIR_NATIVE}/gnatlink-wrapped

    ${S}/configure ${CONFIGUREOPTS}
}

do_configure:append:class-native() {
    # remove the native prefix path from being embedded
    sed -i 's#${RECIPE_SYSROOT_NATIVE}##g' ${B}/default_paths.ads
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

python() {
    if not bb.data.inherits_class("native", d):
        raise bb.parse.SkipRecipe("Cross-compilation support for Ada missing")
}

BBCLASSEXTEND = "native nativesdk"
