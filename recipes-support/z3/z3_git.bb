DESCRIPTION = "Z3 is a theorem prover from Microsoft Research."
HOMEPAGE = "https://github.com/Z3Prover/z3/wiki"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5f03ad1486a2e4ce71200ce0f9721557"
SRC_URI = "git://github.com/Z3Prover/z3;protocol=https"
SRCREV = "c0e74f946b28a83f94becab56a1565c49cb37141"

S = "${WORKDIR}/git"

PV = "4.8.10+git${SRCPV}"

inherit cmake python3native

BBCLASSEXTEND = "native nativesdk"
