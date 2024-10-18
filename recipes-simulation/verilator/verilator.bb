DESCRIPTION = "Verilator open-source SystemVerilog simulator and lint system"
HOMEPAGE = "https://www.veripool.org/wiki/verilator"
LICENSE = "LGPL-3.0-only"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3000208d539ec061b899bce1d9ce9404"

DEPENDS += "flex-native bison-native flex"
DEPENDS += "python3-native"
DEPENDS += "perl-native"
DEPENDS += "help2man-native"

SRC_URI = "git://github.com/verilator/verilator;protocol=https;branch=stable"
SRCREV = "1d79f5c59b0dbe7901dd563a69310d1c9ef60504"

PV = "5.028+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools autotools-brokensep

do_configure() {
    # override the default autoreconf behaviour as it breaks verilator's
    # autotools setup
    autoconf
    oe_runconf
}

do_compile:append:class-target() {
    # Remove buildpaths/hosttools from verilated.mk
    sed -i "s#AR = .*#AR = ar#g" ${B}/include/verilated.mk
    sed -i "s#CXX = .*#CXX = g++ ${HOST_CC_ARCH}#g" ${B}/include/verilated.mk
    sed -i "s#LINK = .*#LINK = g++ ${HOST_CC_ARCH}#g" ${B}/include/verilated.mk
    sed -i "s#PERL = .*#PERL = perl#g" ${B}/include/verilated.mk
    sed -i "s#PYTHON3 = .*#PYTHON3 = python3#g" ${B}/include/verilated.mk
}

do_compile:append:class-nativesdk() {
    # Remove buildpaths/hosttools from verilated.mk
    sed -i "s#AR = .*#AR = ar#g" ${B}/include/verilated.mk
    sed -i "s#CXX = .*#CXX = g++ ${HOST_CC_ARCH}#g" ${B}/include/verilated.mk
    sed -i "s#LINK = .*#LINK = g++ ${HOST_CC_ARCH}#g" ${B}/include/verilated.mk
    sed -i "s#PERL = .*#PERL = perl#g" ${B}/include/verilated.mk
    sed -i "s#PYTHON3 = .*#PYTHON3 = python3#g" ${B}/include/verilated.mk
}

SYSROOT_DIRS_NATIVE:append = " ${datadir}/verilator"

BBCLASSEXTEND = "native nativesdk"
