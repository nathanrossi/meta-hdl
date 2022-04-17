DESCRIPTION = "Yices 2 is a solver for Satisfiability Modulo Theories (SMT) problems."
HOMEPAGE = "https://yices.csl.sri.com/"
LICENSE = "GPL-3.0-or-later"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "git://github.com/SRI-CSL/yices2;protocol=https;branch=master"
SRCREV = "09f162107cc9140172e6c890d8ccc633126c3720"

S = "${WORKDIR}/git"

PV = "2.6.4+git${SRCPV}"

inherit autotools autotools-brokensep

DEPENDS = " \
	gperf-native \
	gmp \
	"

EXTRA_OEMAKE += "YICES_MAKE_INCLUDE=configs/make.include.${TARGET_SYS} STRIP=echo"

# prevent use of ldconfig, as running ldconfig on cross builds is not ideal
EXTRA_OEMAKE += "LDCONFIG=echo"

do_install:append() {
    # create symlink that matches library soname (this would be done by the ldconfig call)
    ln -sf libyices.so.2.6.2 ${D}${libdir}/libyices.so.2.6
}

BBCLASSEXTEND = "native nativesdk"
