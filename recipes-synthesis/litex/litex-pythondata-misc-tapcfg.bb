SUMMARY = "LiteX - tapcfg"
HOMEPAGE = "https://github.com/litex-hub/pythondata-misc-tapcfg"
SECTION = "devel/hdl"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fabba2a3bfeb22a6483d44e9ae824d3f"

SRC_URI = "git://github.com/litex-hub/pythondata-misc-tapcfg;protocol=https;branch=master"
SRCREV = "fbcb02422940bbb97c492f0c970959d262b33632"
PV = "2022.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
