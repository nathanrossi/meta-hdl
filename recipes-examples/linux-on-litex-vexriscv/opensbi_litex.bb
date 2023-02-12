SUMMARY = "RISC-V Open Source Supervisor Binary Interface (OpenSBI)"
DESCRIPTION = "OpenSBI aims to provide an open-source and extensible implementation of the RISC-V SBI specification for a platform specific firmware (M-mode) and a general purpose OS, hypervisor or bootloader (S-mode or HS-mode). OpenSBI implementation can be easily extended by RISC-V platform or System-on-Chip vendors to fit a particular hadware configuration."
HOMEPAGE = "https://github.com/riscv/opensbi"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=42dd9555eb177f35150cf9aa240b61e5"

inherit autotools-brokensep deploy

SRC_URI = "git://github.com/litex-hub/opensbi.git;protocol=https;branch=0.8-linux-on-litex-vexriscv"
SRCREV = "a9ce3ada56574fcdb5befae0395f2f3d33134f81"
S = "${WORKDIR}/git"

PV = "0.8+litex+git${SRCPV}"

SYSROOT_DIRS += "/share/opensbi"

EXTRA_OEMAKE += "PLATFORM=${RISCV_SBI_PLAT} I=${D} FW_PIC=n CLANG_TARGET= "

do_configure:append() {
    sed -i 's/PLATFORM_RISCV_ISA = rv32.*/PLATFORM_RISCV_ISA = rv32ima_zicsr_zifencei/g' ${S}/platform/litex/vexriscv/config.mk
}

do_install:append() {
    # In the future these might be required as a dependency for other packages.
    # At the moment just delete them to avoid warnings
    rm -r ${D}/include
    rm -r ${D}/lib*
    rm -r ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/payloads
}

do_deploy () {
    install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.* ${DEPLOYDIR}/
    install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.* ${DEPLOYDIR}/
    install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.* ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install

FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.*"

INHIBIT_PACKAGE_STRIP = "1"

SECURITY_CFLAGS = ""

COMPATIBLE_MACHINE = "versa-ecp5"
PACKAGE_ARCH = "${MACHINE_ARCH}"
