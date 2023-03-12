SUMMARY = "LiteX - VexRiscV CPU"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv"
SECTION = "devel/hdl"
LICENSE = "MIT"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
