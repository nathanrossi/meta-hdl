SUMMARY = "LiteX - VexRiscV CPU"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv"
SECTION = "devel/hdl"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38aa75cf4c4c87f018227d5ec9638d75"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv;protocol=https;branch=master"
SRCREV = "e75700dff2ab9662f3e26dd89ab59a5f6da65687"
PV = "2022.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
