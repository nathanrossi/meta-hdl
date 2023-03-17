DESCRIPTION = "Z3 is a theorem prover from Microsoft Research."
HOMEPAGE = "https://github.com/Z3Prover/z3/wiki"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5f03ad1486a2e4ce71200ce0f9721557"
SRC_URI = "git://github.com/Z3Prover/z3;protocol=https;branch=master"
SRCREV = "a0f3727e90c4446ba2c1fa7e4392637587ad9632"

S = "${WORKDIR}/git"

PV = "4.12.1+git${SRCPV}"

inherit cmake python3native

BBCLASSEXTEND = "native nativesdk"
