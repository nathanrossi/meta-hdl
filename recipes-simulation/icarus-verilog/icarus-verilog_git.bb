DESCRIPTION = "Icarus Verilog is a Verilog simulaton and synthesis tool"
HOMEPAGE = "http://iverilog.icarus.com/"
LICENSE = "GPL-2.0-or-later"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/steveicarus/iverilog.git;protocol=https;branch=master"
SRCREV = "d8c3c51ab1190ed3fb26540d7de6177f83e0e75b"

S = "${WORKDIR}/git"

PV = "12.0+git${SRCPV}"

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

do_compile:append() {
    sed -i \
        -e 's#-fcanon-prefix-map ##g' \
        -e 's#-fdebug-prefix-map=[^[:space:]]* ##g' \
        -e 's#-fmacro-prefix-map=[^[:space:]]* ##g' \
        ${B}/iverilog-vpi

    # Only replace target/nativesdk STAGING_DIR_TARGET, -native is rewritten by
    # staging.bbclass
    if [ -n "${STAGING_DIR_TARGET}" ]; then
        sed -i \
            -e 's#--sysroot=${STAGING_DIR_TARGET} ##g' \
            -e 's#${STAGING_DIR_TARGET}##g' \
            ${B}/iverilog-vpi
    fi
}

BBCLASSEXTEND = "native nativesdk"
