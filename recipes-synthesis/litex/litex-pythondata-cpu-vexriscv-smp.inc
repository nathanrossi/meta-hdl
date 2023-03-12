SUMMARY = "LiteX - VexRiscV CPU SMP"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv-smp"
SECTION = "devel/hdl"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv_smp;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit setuptools3

VEXRISCV_SMP_DATA = "${PYTHON_SITEPACKAGES_DIR}/pythondata_cpu_vexriscv_smp/verilog"

BBCLASSEXTEND = "native nativesdk"
