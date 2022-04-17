SUMMARY = "LiteX - VexRiscV CPU"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv"
SECTION = "devel/hdl"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38aa75cf4c4c87f018227d5ec9638d75"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv;protocol=https;branch=master"
SRCREV = "cdfb2306087a680df65153760fd41002e316ad30"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
