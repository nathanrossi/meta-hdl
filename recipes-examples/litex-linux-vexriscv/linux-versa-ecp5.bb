SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

# disable kernel-base depending on image, other mechanisms are used to ship the kernel
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE_versa-ecp5 = ".*"

S = "${WORKDIR}/git"

BRANCH = "master"
SRCREV = "${AUTOREV}"
PV = "5.1-pre"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=${BRANCH}"

SRC_URI += "file://linux_add_liteeth_driver.patch"

kernel_do_configure() {
    echo "CONFIG_SECTION_MISMATCH_WARN_ONLY=y" > ${B}/.config
    echo "CONFIG_PRINTK_TIME=y" >> ${B}/.config
    echo "CONFIG_DEVTMPFS=y" >> ${B}/.config

    # Architecture
    echo "CONFIG_ARCH_DEFCONFIG=\"arch/riscv/configs/defconfig\"" >> ${B}/.config
    echo "CONFIG_ARCH_RV32I=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_M=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_A=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_C=n" >> ${B}/.config
    echo "CONFIG_SIFIVE_PLIC=y" >> ${B}/.config
    echo "CONFIG_FPU=n" >> ${B}/.config
    echo "CONFIG_SMP=n" >> ${B}/.config

    echo "CONFIG_HVC_RISCV_SBI=y" >> ${B}/.config

    echo "CONFIG_BLK_DEV_INITRD=y" >> ${B}/.config
    echo "CONFIG_RD_GZIP=y" >> ${B}/.config
    echo "CONFIG_RD_BZIP2=y" >> ${B}/.config
    echo "CONFIG_RD_LZMA=y" >> ${B}/.config
    echo "CONFIG_RD_XZ=y" >> ${B}/.config
    echo "CONFIG_RD_LZO=y" >> ${B}/.config
    echo "CONFIG_RD_LZ4=y" >> ${B}/.config

    echo "CONFIG_NET=y" >> ${B}/.config
    echo "CONFIG_INET=y" >> ${B}/.config
    echo "CONFIG_NETDEVICES=y" >> ${B}/.config
    # liteeth (requires patch)
    echo "CONFIG_NET_VENDOR_LITEX=y" >> ${B}/.config
    echo "CONFIG_LITEX_LITEETH=y" >> ${B}/.config

    oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig
}

