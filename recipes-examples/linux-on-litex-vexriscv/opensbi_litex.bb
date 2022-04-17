require recipes-bsp/opensbi/opensbi_1.0.bb

SRC_URI = "git://github.com/litex-hub/opensbi.git;protocol=https;branch=0.8-linux-on-litex-vexriscv"
SRCREV = "a9ce3ada56574fcdb5befae0395f2f3d33134f81"

PV = "0.8+litex+git${SRCPV}"

SYSROOT_DIRS += "/share/opensbi"

do_configure:append() {
    sed -i 's/PLATFORM_RISCV_ISA = rv32.*/PLATFORM_RISCV_ISA = rv32ima_zicsr_zifencei/g' ${S}/platform/litex/vexriscv/config.mk
}

COMPATIBLE_MACHINE = "versa-ecp5"
PACKAGE_ARCH = "${MACHINE_ARCH}"
