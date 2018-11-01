require litex-target.inc

DEPENDS += "python3-pyserial-native"
DEPENDS += "migen-native"
DEPENDS += "litex-native"
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litex-buildenv"

do_configure[noexec] = "1"
do_install[noexec] = "1"

inherit python3native
inherit deploy

DEPENDS += "litex-target"
DEPENDS += "micropython"

# qemu-img
DEPENDS += "qemu-native"

do_compile() {
    export PYTHONPATH="${S}:${PYTHONPATH}"

    python3 -m litex.soc.tools.mkmscimg \
        -f ${STAGING_DIR_HOST}/boot/firmware.bin \
        -o ${B}/firmware.fbi

    python3 ${S}/mkimage.py \
        --override-gateware=${STAGING_DIR_HOST}/boot/top.bin \
        --override-bios=${STAGING_DIR_HOST}/boot/bios.bin \
        --override-firmware=${B}/firmware.fbi \
        --output-file=${B}/image.bin

    #python3 ${S}/mkimage.py \
        #--override-gateware=none \
        #--override-bios=${STAGING_DIR_HOST}/boot/bios.bin \
        #--override-firmware=${B}/firmware.fbi \
        #--output-file=${B}/image-qemu.bin \
        #--force-image-size=true

    python3 ${S}/mkimage.py \
        --output-file=${B}/image-qemu.bin \
        --override-bios=none \
        --override-gateware=none \
        --force-image-size=true \
        --override-firmware=${B}/firmware.fbi

    qemu-img convert -f raw ${B}/image-qemu.bin -O qcow2 -S 16M ${B}/image-qemu.qcow2
}

do_deploy() {
    install -Dm 0644 ${B}/image.bin ${DEPLOYDIR}/${BPN}.bin
    install -Dm 0644 ${B}/image-qemu.qcow2 ${DEPLOYDIR}/${BPN}-qemu.qcow2
}
addtask deploy before do_build after do_install

# generate runqemu conf
inherit qemuboot
IMGDEPLOYDIR = "${WORKDIR}/deploy-${PN}"
do_rootfs[noexec] = "1"
do_rootfs () {
    :
}
addtask rootfs before do_image
do_image () {
    :
}
addtask image before do_deploy

python () {
    d.appendVarFlag('do_image', 'depends',
        ' '.join('{0}:do_populate_sysroot'.format(dep) for dep in (d.getVar('EXTRA_IMAGEDEPENDS') or '').split()))
}

