SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e2abad236488e3dd7fc3c9f12deb2a7c"

SRC_URI = "git://github.com/enjoy-digital/litex;protocol=https;branch=master"
SRCREV = "3dbe349dd95d60d5a45e872b44823fb1209d4263"
PV = "2021.12+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "python3-pyserial"
RDEPENDS:${PN} += "python3-requests"
RDEPENDS:${PN} += "migen"
RDEPENDS:${PN} += "litescope"

do_configure:prepend() {
    # remove use of "-nodefaultlibs", binutils-ld does not accept this arg
    sed -i 's/-nodefaultlibs//g' ${S}/litex/soc/software/common.mak

    # HACK: fix handling of meson cpu_family for riscv32
    sed -i "s/\\(cpu_family\\s*=\\s*\\)'.*'/\\1'\$(subst riscv,riscv32,\$(CPUFAMILY))'/g" ${S}/litex/soc/software/libc/Makefile

    # HACK: handle gcc 11+ with zicsr separation
    sed -i 's/\(-march=[^ ]*\)_?/\1_zicsr_zifencei/g' ${S}/litex/soc/cores/cpu/vexriscv_smp/core.py
}

BBCLASSEXTEND = "native nativesdk"
