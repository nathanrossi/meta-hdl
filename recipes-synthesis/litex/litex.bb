SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e2abad236488e3dd7fc3c9f12deb2a7c"

SRC_URI = "git://github.com/enjoy-digital/litex;protocol=https;branch=master"
SRCREV = "153f9e9f6c083dd861c015086cdcc9fedc1b009f"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "python3-pyserial"
RDEPENDS:${PN} += "python3-requests"
RDEPENDS:${PN} += "migen"
RDEPENDS:${PN} += "litescope"

do_configure:prepend() {
    # missing in upstream
    echo "graft litex/soc/cores/cpu/vexriscv_smp" >> ${S}/MANIFEST.in

    # remove use of "-nodefaultlibs", binutils-ld does not accept this arg
    sed -i 's/-nodefaultlibs//g' ${S}/litex/soc/software/common.mak

    # HACK: fix handling of meson cpu_family for riscv32
    sed -i "s/\\(cpu_family\\s*=\\s*\\)'.*'/\\1'\$(subst riscv,riscv32,\$(CPUFAMILY))'/g" ${S}/litex/soc/software/libc/Makefile
}

do_install:append() {
    # some scripts in litex exist but are unused
    installpath=${D}${PYTHON_SITEPACKAGES_DIR}/litex
}

BBCLASSEXTEND = "native nativesdk"
