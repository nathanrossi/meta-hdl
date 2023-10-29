require litex.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce55a94e98cbec2110586bb5264bd9a"

SRCREV = "a2c2c211c56c5d7a6a57e5248e3216c37446bdd3"
PV = "2023.08+git${SRCPV}"

do_configure:prepend() {
    # remove use of "-nodefaultlibs", binutils-ld does not accept this arg
    sed -i 's/-nodefaultlibs//g' ${S}/litex/soc/software/common.mak

    # HACK: fix handling of meson cpu_family for riscv32
    sed -i "s/\\(cpu_family\\s*=\\s*\\)'.*'/\\1'\$(subst riscv,riscv32,\$(CPUFAMILY))'/g" ${S}/litex/soc/software/libc/Makefile

    # HACK: handle gcc 11+ with zicsr separation
    sed -i 's/\(-march={[^ ]*}\)/\1_zicsr_zifencei/g' ${S}/litex/soc/cores/cpu/vexriscv_smp/core.py
}
