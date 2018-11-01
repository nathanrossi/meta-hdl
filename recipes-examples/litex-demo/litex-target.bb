require litex-target.inc

DEPENDS += "python3-pyserial-native"
DEPENDS += "migen-native"
DEPENDS += "litex-native"

# device modules
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"
DEPENDS += "litex-buildenv"

def fpga_family(d):
    return next((f.strip("fpga-") for f in d.getVar("MACHINEOVERRIDES").split(":") if f.startswith("fpga-")), None)

def fpga_toolchain_args(d):
    fpga = fpga_family(d)
    if fpga in ["artix7"]:
        return "-Ob toolchain_path ${STAGING_DIR_NATIVE}/opt/xilinx"
    return ""

python () {
    # auto select toolchain dependencies
    fpga = fpga_family(d)
    if fpga in ["artix7"]:
        d.appendVar("DEPENDS", " virtual/xilinx-vivado-native")
    elif fpga in ["ice40"]:
        d.appendVar("DEPENDS", " yosys-native icestorm-native nextpnr-ice40-native")
    else:
        raise bb.parse.SkipRecipe("Unknown FPGA target architecture '{0}'".format(fpga))
}

do_configure[noexec] = "1"

inherit python3native
inherit deploy

do_compile() {
    export PYTHONPATH="${S}:${PYTHONPATH}"

    # make.py uses os.getcwd() to get the srcdir
    cd ${S}

    python3 -m make \
        ${@fpga_toolchain_args(d)} \
        --output-dir ${B} \
        --cpu-type ${TARGET_ARCH} \
        ${@"--cpu-variant ${LITEX_CPU_VARIANT}" if d.getVar("LITEX_CPU_VARIANT") else ""} \
        ${@"--target ${LITEX_TARGET}" if d.getVar("LITEX_TARGET") else ""} \
        ${@"--platform ${LITEX_PLATFORM}" if d.getVar("LITEX_PLATFORM") else ""} \
        --no-compile-firmware
}

do_install () {
    install -d ${D}/boot

    # install image binaries into /boot/
    install -m 0644 ${B}/gateware/top.bin ${D}/boot

    install -d ${D}${libdir}/litex
    # copy in any archive libraries
    for i in libbase libcompiler_rt libnet uip; do
        if [ -e ${B}/software/$i ]; then
            for f in $(find ${B}/software/$i -name "*.a"); do
                install -m 0644 -D $f ${D}${libdir}/litex/$i/$(basename $f)
            done
        fi
    done

    # install bios binaries into /boot/
    install -m 0644 ${B}/software/bios/bios.bin ${D}/boot
}

SYSROOT_DIRS += "/boot"
FILES_${PN} += "/boot"
FILES_${PN}-staticdev += "${libdir}/litex/*/*.a"

do_deploy() {
    install -Dm 0644 ${B}/gateware/top.bin ${DEPLOYDIR}/${BPN}.bin
    install -Dm 0644 ${B}/software/bios/bios.bin ${DEPLOYDIR}/bios-${MACHINE}.bin
}
addtask deploy before do_build after do_install

