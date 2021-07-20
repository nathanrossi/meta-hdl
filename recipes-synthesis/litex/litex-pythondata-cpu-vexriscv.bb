SUMMARY = "LiteX - VexRiscV CPU"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv"
SECTION = "devel/hdl"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv;protocol=https"
SRCREV = "a17f86c94c11dabb4be5e3f63a90e0b65fc8dec5"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
