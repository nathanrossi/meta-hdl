SUMMARY = "LiteX - picolibc"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-picolibc"
SECTION = "devel/hdl"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f225922a2c12dfa5218fb70c49db3ea6"

SRC_URI = "gitsm://github.com/litex-hub/pythondata-software-picolibc;protocol=https;branch=master"
SRCREV = "ae9c1ca4af911c85f941c8a8540ddae4520ae2cd"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
