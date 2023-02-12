SUMMARY = "LiteX - picolibc"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-picolibc"
SECTION = "devel/hdl"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f225922a2c12dfa5218fb70c49db3ea6"

SRC_URI = "gitsm://github.com/litex-hub/pythondata-software-picolibc;protocol=https;branch=master"
SRCREV = "a5e11229885a87083ec54034f8d693ba52ea2718"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
