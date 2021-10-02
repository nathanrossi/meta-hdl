SECTION = "kernel"
DESCRIPTION = "Mainline Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

# disable kernel-base depending on image, other mechanisms are used to ship the kernel
RDEPENDS:${KERNEL_PACKAGE_NAME}-base = ""

DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:versa-ecp5 = ".*"

S = "${WORKDIR}/git"

BRANCH = "master"
SRCREV = "0ad2c0e5fc7bd5c5a60f88be1174271410254e32"
PV = "5.6+${SRCPV}"
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=https;branch=${BRANCH}"

SRC_URI += " \
        file://0001-linux-add-liteeth-driver.patch \
        file://0002-litex_liteeth-make-it-work-in-polling-mode.patch \
        file://0003-litex_liteeth-fix-printk-p-ptrval-result.patch \
        file://0004-litex_liteeth-add-missed-netif_stop_queue.patch \
        file://0005-drivers-tty-serial-add-LiteUART-driver.patch \
        file://0006-net-ethernet-liteeth-Fix-up-mac_addr-validity-checki.patch \
        "

kernel_do_configure() {
    echo "" > ${B}/.config

    # Architecture
    echo "CONFIG_ARCH_RV32I=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_M=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_A=y" >> ${B}/.config
    echo "CONFIG_RISCV_ISA_C=n" >> ${B}/.config
    echo "CONFIG_SIFIVE_PLIC=y" >> ${B}/.config
    echo "CONFIG_FPU=n" >> ${B}/.config
    echo "CONFIG_SMP=n" >> ${B}/.config

    echo "CONFIG_NET=y" >> ${B}/.config
    echo "CONFIG_INET=y" >> ${B}/.config
    echo "CONFIG_NETDEVICES=y" >> ${B}/.config
    # liteeth (requires patch)
    echo "CONFIG_NET_VENDOR_LITEX=y" >> ${B}/.config
    echo "CONFIG_LITEX_LITEETH=y" >> ${B}/.config

    echo "CONFIG_SERIAL_EARLYCON_RISCV_SBI=y" >> ${B}/.config
    # liteuart (requires patch)
    echo "CONFIG_SERIAL_LITEUART=y" >> ${B}/.config
    echo "CONFIG_SERIAL_LITEUART_CONSOLE=y" >> ${B}/.config

    # kernel features
    echo "CONFIG_PRINTK_TIME=y" >> ${B}/.config
    echo "CONFIG_SECTION_MISMATCH_WARN_ONLY=y" >> ${B}/.config

    echo "CONFIG_BLK_DEV_INITRD=y" >> ${B}/.config
    echo "CONFIG_INITRAMFS_SOURCE=" >> ${B}/.config

    echo "CONFIG_TMPFS=y" >> ${B}/.config
    echo "CONFIG_DEVTMPFS=y" >> ${B}/.config
    echo "CONFIG_DEVTMPFS_MOUNT=y" >> ${B}/.config
    echo "CONFIG_UNIX=y" >> ${B}/.config
    echo "CONFIG_PACKET=y" >> ${B}/.config

    # update the .config with the modifications, default no config
    yes "" | oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} oldconfig
}

