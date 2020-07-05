DESCRIPTION = "Verilator open-source SystemVerilog simulator and lint system"
HOMEPAGE = "https://www.veripool.org/wiki/verilator"
LICENSE = "GPLv3"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"

DEPENDS += "flex-native bison-native flex"
DEPENDS += "python3-native"
DEPENDS += "perl-native"

SRC_URI = "git://github.com/verilator/verilator;protocol=https"
SRCREV = "8291ef6537702d69d9d87467aae1233c3bbcd27c"

PV = "4.036+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools autotools-brokensep

do_configure() {
    # prevent the configure script from searching bad paths by default
    sed -i '/CFLAGS+=/d' ${S}/configure.ac
    sed -i '/CPPFLAGS+=/d' ${S}/configure.ac
    sed -i '/CXXFLAGS+=/d' ${S}/configure.ac
    sed -i '/LDFLAGS+=/d' ${S}/configure.ac

    # override the default autoreconf behaviour as it breaks verilator's
    # autotools setup
    autoconf
    oe_runconf
}

do_install_append() {
    # create symlinks from VERILATOR_ROOT to usr/bin
    for i in verilator verilator_bin verilator_bin_dbg verilator_coverage verilator_coverage_bin_dbg verilator_gantt verilator_profcfunc; do
        ln -s \
            ${@os.path.relpath(d.expand("${bindir}"), d.expand("${datadir}/verilator/bin"))}/$i \
            ${D}${datadir}/verilator/bin/$i
    done
}

SYSROOT_DIRS_NATIVE_append = " ${datadir}/verilator"

BBCLASSEXTEND = "native nativesdk"
