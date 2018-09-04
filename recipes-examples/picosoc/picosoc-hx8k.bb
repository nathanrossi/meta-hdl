require picosoc.inc

MAKE_TARGETS = "hx8kdemo.bin hx8kdemo_fw.bin"

do_deploy () {
    install -Dm 0644 ${B}/hx8kdemo.bin ${DEPLOYDIR}/picosoc-hx8k.bin
    install -Dm 0644 ${B}/hx8kdemo_fw.bin ${DEPLOYDIR}/firmware-hx8k.bin
}

