DESCRIPTION = "Yices 2 is a solver for Satisfiability Modulo Theories (SMT) problems."
HOMEPAGE = "https://yices.csl.sri.com/"
LICENSE = "GPL-3.0-or-later"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "git://github.com/SRI-CSL/yices2;protocol=https;branch=master"
SRCREV = "234bd18c7b827c53317c3a52497ec5016bb27ec6"

S = "${WORKDIR}/git"

PV = "2.6.5+git${SRCPV}"

inherit autotools autotools-brokensep

DEPENDS = " \
	gperf-native \
	gmp \
	"

EXTRA_OEMAKE += "YICES_MAKE_INCLUDE=configs/make.include.${TARGET_SYS} STRIP=echo"

# prevent use of ldconfig, as running ldconfig on cross builds is not ideal
EXTRA_OEMAKE += "LDCONFIG=echo"

BBCLASSEXTEND = "native nativesdk"
