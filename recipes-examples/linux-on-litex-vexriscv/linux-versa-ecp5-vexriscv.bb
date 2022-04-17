SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

# disable kernel-base depending on image, other mechanisms are used to ship the kernel
RDEPENDS:${KERNEL_PACKAGE_NAME}-base = ""

DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "^versa-ecp5$"

S = "${WORKDIR}/git"

BRANCH = "master"
SRCREV = "f443e374ae131c168a065ea1748feac6b2e76613"
PV = "5.17+${SRCPV}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=${BRANCH}"

kernel_do_configure() {
    # generate allnoconfig/setup build
    oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} allnoconfig

    echo "" > ${B}/.config

    # Architecture
    echo "CONFIG_ARCH_RV32I=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_C=n" >> ${B}/.config
    echo "CONFIG_SIFIVE_PLIC=y" >> ${B}/.config
    echo "CONFIG_FPU=y" >> ${B}/.config
    echo "CONFIG_SMP=n" >> ${B}/.config

    # soc configs
    echo "CONFIG_LITEX=y" >> ${B}/.config
    echo "CONFIG_LITEX_SOC_CONTROLLER=y" >> ${B}/.config

    # liteuart console
    echo "CONFIG_SERIAL_LITEUART=y" >> ${B}/.config
    echo "CONFIG_SERIAL_LITEUART_CONSOLE=y" >> ${B}/.config

    # liteeth network
    echo "CONFIG_NET=y" >> ${B}/.config
    echo "CONFIG_NET_CORE=y" >> ${B}/.config
    echo "CONFIG_INET=y" >> ${B}/.config
    echo "CONFIG_NETDEVICES=y" >> ${B}/.config
    echo "CONFIG_NET_VENDOR_LITEX=y" >> ${B}/.config
    echo "CONFIG_LITEX_LITEETH=y" >> ${B}/.config

    # kernel features
    echo "CONFIG_BINFMT_ELF=y" >> ${B}/.config
    echo "CONFIG_BINFMT_SCRIPT=y" >> ${B}/.config
    echo "CONFIG_BINFMT_MISC=y" >> ${B}/.config
    echo "CONFIG_SYSVIPC=y" >> ${B}/.config
    echo "CONFIG_POSIX_MQUEUE=y" >> ${B}/.config
    echo "CONFIG_INOTIFY_USER=y" >> ${B}/.config
    echo "CONFIG_NO_HZ_IDLE=y" >> ${B}/.config
    echo "CONFIG_HIGH_RES_TIMERS=y" >> ${B}/.config

    echo "CONFIG_IKCONFIG=y" >> ${B}/.config
    echo "CONFIG_IKCONFIG_PROC=y" >> ${B}/.config
    echo "CONFIG_PRINTK_TIME=y" >> ${B}/.config
    echo "CONFIG_SECTION_MISMATCH_WARN_ONLY=y" >> ${B}/.config

    echo "CONFIG_BLK_DEV_INITRD=y" >> ${B}/.config
    echo "CONFIG_INITRAMFS_SOURCE=" >> ${B}/.config

    echo "CONFIG_TMPFS=y" >> ${B}/.config
    echo "CONFIG_DEVTMPFS=y" >> ${B}/.config
    echo "CONFIG_DEVTMPFS_MOUNT=y" >> ${B}/.config
    echo "CONFIG_UNIX=y" >> ${B}/.config
    echo "CONFIG_PACKET=y" >> ${B}/.config

    # use merge_config to generate a allnoconfig merge with desired configs
    ${S}/scripts/kconfig/merge_config.sh -n ${B}/.config
}

