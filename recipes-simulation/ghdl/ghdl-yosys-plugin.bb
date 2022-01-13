DESCRIPTION = "VHDL synthesis (based on ghdl)"
HOMEPAGE = "http://ghdl.free.fr/"
SECTION = "devel/vhdl"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/ghdl/ghdl-yosys-plugin;protocol=https;branch=master"
SRCREV = "09a32cd13c3335e3231d6ccd32c033826c6a3798"
PV = "0+git${SRCPV}"

DEPENDS += "yosys ghdl"

do_configure[noexec] = "1"

CFLAGS:append = " -I${STAGING_DATADIR}/yosys/include "

EXTRA_OEMAKE += 'LIBGHDL_LIB="${STAGING_LIBDIR}/libghdl-2_0_0_dev.so"'
EXTRA_OEMAKE += 'LIBGHDL_INC="${STAGING_INCDIR}"'
EXTRA_OEMAKE += 'YOSYS_CONFIG="${STAGING_BINDIR}/yosys-config"'
# disable rpath
EXTRA_OEMAKE += 'ALL_LDFLAGS="\$(LIBGHDL_LIB) \$(LDFLAGS)"'

do_compile() {
    oe_runmake
}

do_install() {
    # install the shared library into the yosys plugin directory
    install -d ${D}${datadir}/yosys/plugins
    install -m 0755 ${B}/ghdl.so ${D}${datadir}/yosys/plugins/
}

FILES:${PN} += "${datadir}/yosys/plugins/*.so"

do_check[depends] += "z3-native:do_populate_sysroot"
do_check[depends] += "yices2-native:do_populate_sysroot"
do_check[depends] += "sby-native:do_populate_sysroot"
do_check() {
    export GHDL_PREFIX="${STAGING_DIR_NATIVE}/usr/lib/ghdl/"
    ${S}/testsuite/testsuite.sh
}
addtask check after do_install

BBCLASSEXTEND = "native nativesdk"
