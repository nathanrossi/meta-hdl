DESCRIPTION = "Yices 2 is a solver for Satisfiability Modulo Theories (SMT) problems."
HOMEPAGE = "https://yices.csl.sri.com/"
LICENSE = "GPLv3"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"
SRC_URI = "git://github.com/SRI-CSL/yices2;protocol=https"
SRCREV = "fd040de11d492f43213e890bc77490dacb8546b8"

S = "${WORKDIR}/git"

PV = "2.6.1+git${SRCPV}"

inherit autotools autotools-brokensep

DEPENDS = " \
	gperf-native \
	gmp \
	"

EXTRA_OEMAKE += "YICES_MAKE_INCLUDE=configs/make.include.${TARGET_SYS} STRIP=echo"

BBCLASSEXTEND = "native nativesdk"
