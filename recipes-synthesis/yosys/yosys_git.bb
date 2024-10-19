DESCRIPTION = "Yosys, a framework for Verilog RTL synthesis"
HOMEPAGE = "http://www.clifford.at/yosys/"
LICENSE = "ISC"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://COPYING;md5=d1b1d1933fedc05c99106d80dd51dd02"

SRC_URI = "gitsm://github.com/YosysHQ/yosys;protocol=https;branch=main"
SRCREV = "799497ebbaa6f548a9711244bf6de8147c2aa398"

S = "${WORKDIR}/git"

PV = "0.46+git${SRCPV}"

inherit pkgconfig

DEPENDS = " \
    python3-native \
    bison-native \
    flex-native \
    gawk-native \
    tcl-native \
    readline \
    tcl \
    libffi \
    "

PACKAGE_BEFORE_PN += "${PN}-smtbmc"

RDEPENDS:${PN} += "bash"
RDEPENDS:${PN}-smtbmc += "python3-core python3-resource python3-threading"

FILES:${PN}-smtbmc += " \
    ${bindir}/yosys-smtbmc \
    ${datadir}/yosys/python3/* \
    "

do_configure:append () {
    # config build for GCC
    make config-gcc

    # Prevent host paths from leaking into the build/output binaries, replacing
    # the use of "-version" from g++.
    sed -i '/YOSYS_VER_STR.*shell /{s#..notdir.*shell.*#${HOST_PREFIX}-g++ ${HOST_CC_ARCH})#g;n;d}' ${S}/Makefile
}

do_compile() {
    # force make variables for CXX and LD, the makefile forces them instead of defaulting
    oe_runmake PREFIX="${prefix}" PRETTY=0 CXX="${CXX}" LINKFLAGS="${LDFLAGS}"

    # Prevent host paths from leaking into yosys-config
    sed -i \
        -e 's#-fcanon-prefix-map ##g' \
        -e 's#-fdebug-prefix-map=[^[:space:]]* ##g' \
        -e 's#-fmacro-prefix-map=[^[:space:]]* ##g' \
        ${B}/yosys-config

    # Only replace target/nativesdk STAGING_DIR_TARGET, -native is rewritten by
    # staging.bbclass
    if [ -n "${STAGING_DIR_TARGET}" ]; then
        sed -i \
            -e 's#--sysroot=${STAGING_DIR_TARGET} ##g' \
            -e 's#${STAGING_DIR_TARGET}##g' ${B}/yosys-config
    fi
}

do_install() {
    # override STRIP to prevent the makefile from stripping during the install
    oe_runmake PREFIX="${prefix}" PRETTY=0 DESTDIR="${D}" STRIP="echo" install
}

BBCLASSEXTEND = "native nativesdk"
