DESCRIPTION = "Berkeley ABC: System for Sequential Logic Synthesis and Formal Verification"
HOMEPAGE = "http://www.eecs.berkeley.edu/~alanmi/abc/"
# License is MIT, but differs from standard license text
LICENSE = "MIT"
SECTION = "devel/hardware"

LIC_FILES_CHKSUM = "file://copyright.txt;md5=bddcabf9df6d33e12310a00780d3a87c"

SRC_URI = "git://github.com/berkeley-abc/abc;protocol=https"
SRCREV = "97c826a6e63eb0020920819595f4da8f2cc674ba"

S = "${WORKDIR}/git"

PV = "1.01+git${SRCPV}"

DEPENDS = "readline"

# set the ARCHFLAGS to avoid execution of arch_flags during build
export ARCHFLAGS = "${ABC_ARCHFLAGS}"
ABC_ARCHFLAGS_x86-64 = "-DLIN64 -DSIZEOF_VOID_P=8 -DSIZEOF_LONG=8 -DSIZEOF_INT=4"
ABC_ARCHFLAGS = "-DLIN -DSIZEOF_VOID_P=4 -DSIZEOF_LONG=4 -DSIZEOF_INT=4 -fpermissive -w"

inherit cmake

do_install() {
	install -Dm 755 ${B}/abc ${D}${bindir}/abc
}

BBCLASSEXTEND = "native nativesdk"
