DESCRIPTION = "VHDL 2008/93/87 simulator"
HOMEPAGE = "http://ghdl.free.fr/"
SECTION = "devel/vhdl"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=60d644347832d2dd9534761f6919e2a6"

PV = "0.37-dev+git${SRCPV}"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/ghdl/ghdl;protocol=https"
SRCREV = "8982646305a71ab14057f84e80ee504c8b7d2c67"

DEPENDS = "zlib"

CONFIGUREOPTS = "--prefix=${prefix} --srcdir=${S} --enable-openieee --enable-libghdl --enable-synth"

B = "${WORKDIR}/build"

CFLAGS += "-fno-strict-aliasing"

GNATMAKE = "gnatmake"
GNATBIND = "gnatbind"
GNATLINK = "gnatlink"

# setup CC, GNATMAKE
EXTRA_OEMAKE = ' \
    CC="${CC} ${CFLAGS}" \
    GNATMAKE="${GNATMAKE} ${LDFLAGS} --GCC=\"${CC} ${CFLAGS}\" --GNATBIND=${GNATBIND} --GNATLINK=${GNATLINK}" \
    '

do_configure() {
    ${S}/configure ${CONFIGUREOPTS}
}

do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D}
}


BBCLASSEXTEND = "native nativesdk"
