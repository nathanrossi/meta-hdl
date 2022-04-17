SUMMARY = "LiteX - VexRiscV CPU SMP"
HOMEPAGE = "https://github.com/litex-hub/pythondata-cpu-vexriscv-smp"
SECTION = "devel/hdl"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/litex-hub/pythondata-cpu-vexriscv_smp;protocol=https;branch=master"
SRCREV = "60c32b141906ea4b8ba6ec19c66a321a1477e55c"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

VEXRISCV_SMP_DATA = "${PYTHON_SITEPACKAGES_DIR}/pythondata_cpu_vexriscv_smp/verilog"

do_install:append() {
    # HACK: copy the desired pre-built verilog for the vexriscv cluster
    # litex - c06bd2c77d023aab739f056d31fecef0a53de283 adds "ITs4DTs4" for dtlb/itlb sizes
    for i in Ood_Wm Ldw128_Ood Ldw256_Ood Ldw32_Ood; do
        sed "s/VexRiscvLitexSmpCluster_Cc1_Iw32Is4096Iy1_Dw32Ds4096Dy1_$i/VexRiscvLitexSmpCluster_Cc1_Iw32Is4096Iy1_Dw32Ds4096Dy1_ITs4DTs4_$i/g" \
            ${D}${VEXRISCV_SMP_DATA}/VexRiscvLitexSmpCluster_Cc1_Iw32Is4096Iy1_Dw32Ds4096Dy1_$i.v \
            > ${D}${VEXRISCV_SMP_DATA}/VexRiscvLitexSmpCluster_Cc1_Iw32Is4096Iy1_Dw32Ds4096Dy1_ITs4DTs4_$i.v
    done
}

BBCLASSEXTEND = "native nativesdk"
