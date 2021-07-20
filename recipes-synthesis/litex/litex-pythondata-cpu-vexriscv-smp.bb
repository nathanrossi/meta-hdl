SUMMARY = "LiteX - VexRiscV CPU SMP"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv-smp"
SECTION = "devel/hdl"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv-smp;protocol=https"
SRCREV = "42569fbe536c35f8f4ba753ce7215a311f6cb142"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
