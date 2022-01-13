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

# use the pre-built mcode objects from the native build
DEPENDS += "ghdl-native"

CONFIGUREOPTS = "--prefix=${prefix} --srcdir=${S} --enable-libghdl --enable-synth --default-pic"

B = "${WORKDIR}/build"

CFLAGS:append = " -fno-strict-aliasing -fPIC"

inherit gnat

EXTRA_OEMAKE += 'CC="${CC} ${CFLAGS}"'
EXTRA_OEMAKE += 'GNATMAKE='${GNATMAKE}''

do_configure() {
    ${S}/configure ${CONFIGUREOPTS}
}

do_configure:append:class-native() {
    # remove the native prefix path from being embedded
    sed -i 's#${RECIPE_SYSROOT_NATIVE}##g' ${B}/default_paths.ads
}

do_compile() {
    if ${@"false" if bb.data.inherits_class("native", d) else "true"}; then
        # copy pre-built mcode objects/source from native build
        mkdir -p ${B}/lib/ghdl/
        cp -r ${RECIPE_SYSROOT_NATIVE}${libdir_native}/ghdl/src ${B}/lib/ghdl/
        cp -r ${RECIPE_SYSROOT_NATIVE}${libdir_native}/ghdl/std ${B}/lib/ghdl/
        cp -r ${RECIPE_SYSROOT_NATIVE}${libdir_native}/ghdl/ieee ${B}/lib/ghdl/
        # prevent rebuilds based on target ghdl_mocde
        sed -i '/ANALYZE_DEP=/d' ${S}/libraries/Makefile.inc
        # prevent rebuilding of standard.vhdl files
        sed -i '/--disp-standard/d' ${S}/Makefile.in
    fi

    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

do_install:append:class-nativesdk () {
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    # setup GHDL_PREFIX for library path
    echo "export GHDL_PREFIX=\"\$OECORE_NATIVE_SYSROOT${libdir_nativesdk}/ghdl\"" > ${WORKDIR}/ghdl.sh
    install -m 644 ${WORKDIR}/ghdl.sh ${D}${SDKPATHNATIVE}/environment-setup.d/ghdl.sh
}

FILES:${PN} += "${libdir}/lib*.so"
FILES:${PN}:append:class-nativesdk = " ${SDKPATHNATIVE}/environment-setup.d/ghdl.sh"
FILES:${PN}-dev:remove = "${libdir}/lib*.so"
FILES:${PN}-dev += "${libdir}/libghdl.link"

BBCLASSEXTEND = "native nativesdk"

# mcode backend is only supported on x86
COMPATIBLE_HOST = "(i.86|x86_64).*-linux"
