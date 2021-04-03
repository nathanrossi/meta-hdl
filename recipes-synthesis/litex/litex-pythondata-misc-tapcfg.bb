SUMMARY = "LiteX - tapcfg"
HOMEPAGE = "https://github.com/litex-hub/pythondata-misc-tapcfg"
SECTION = "devel/hdl"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

SRC_URI = "git://github.com/litex-hub/pythondata-misc-tapcfg;protocol=https"
SRCREV = "0e6809132b7a42d26fc148b2b5e54ede8d6021ab"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
