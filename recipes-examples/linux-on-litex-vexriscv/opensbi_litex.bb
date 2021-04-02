require recipes-bsp/opensbi/opensbi_0.9.bb

SRC_URI = "git://github.com/litex-hub/opensbi.git;protocol=https;branch=0.8-linux-on-litex-vexriscv"
SRCREV = "a9ce3ada56574fcdb5befae0395f2f3d33134f81"

PV = "0.8+litex+git${SRCPV}"

SYSROOT_DIRS += "/share/opensbi"

COMPATIBLE_MACHINE = "versa-ecp5"
