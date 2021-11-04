DESCRIPTION = "Btor2tools is a generic parser and tool package for the BTOR2 format."
HOMEPAGE = "https://github.com/boolector/btor2tools"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8be17480d0587bf9ac39c209f6cb8c76"
SRC_URI = "git://github.com/Boolector/btor2tools;protocol=https;branch=master"
SRCREV = "7772bb546e98c829f3f34e184ec756e188cd845a"

S = "${WORKDIR}/git"

BTOR2TOOLS_VERSION = "1.0.0"
PV = "${BTOR2TOOLS_VERSION}+git${SRCPV}"

inherit cmake

# Fix QA Issue: No GNU_HASH in the ELF binary
TARGET_CC_ARCH += "${LDFLAGS}"

# Currently there is no cmake install target available:
# ninja: error: unknown target 'install'
do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/bin/* ${D}${bindir}

	install -d ${D}${includedir}
	install -d ${D}${includedir}/btor2parser
	install -m 0644 ${S}/src/btor2parser/btor2parser.h ${D}/${includedir}/btor2parser

	install -d ${D}${libdir}
	# Fix "-dev package contains non-symlink .so" dev-elf error
	install -m 0755 ${B}/lib/libbtor2parser.so ${D}${libdir}/libbtor2parser.so.${BTOR2TOOLS_VERSION}
	ln -sfr ${D}${libdir}/libbtor2parser.so.${BTOR2TOOLS_VERSION} ${D}${libdir}/libbtor2parser.so
}

BBCLASSEXTEND = "native nativesdk"
