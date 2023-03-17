DESCRIPTION = "Verilator open-source SystemVerilog simulator and lint system"
HOMEPAGE = "https://www.veripool.org/wiki/verilator"
LICENSE = "LGPL-3.0-only"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"

DEPENDS += "flex-native bison-native flex"
DEPENDS += "python3-native"
DEPENDS += "perl-native"
DEPENDS += "help2man-native"

SRC_URI = "git://github.com/verilator/verilator;protocol=https;branch=master"
SRCREV = "85fd88ace2447fde2986f2861f8f2e66295235d7"

PV = "5.008+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools autotools-brokensep

do_configure() {
    # override the default autoreconf behaviour as it breaks verilator's
    # autotools setup
    autoconf
    oe_runconf
}

do_install:append() {
    # create symlinks from VERILATOR_ROOT to usr/bin
    for i in verilator verilator_bin verilator_bin_dbg verilator_coverage verilator_coverage_bin_dbg verilator_gantt verilator_profcfunc; do
        ln -s \
            ${@os.path.relpath(d.expand("${bindir}"), d.expand("${datadir}/verilator/bin"))}/$i \
            ${D}${datadir}/verilator/bin/$i
    done
}

SYSROOT_DIRS_NATIVE:append = " ${datadir}/verilator"

BBCLASSEXTEND = "native nativesdk"
