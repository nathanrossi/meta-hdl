require picosoc.inc

DEPENDS += "arachne-pnr-native"

PROJ_NAME = "hx8kdemo"
SIM_TARGET = "hx8ksim"

do_deploy () {
    install -Dm 0644 ${B}/${PROJ_NAME}.bin ${DEPLOYDIR}/picosoc-hx8k.bin
    install -Dm 0644 ${B}/${PROJ_NAME}_fw.bin ${DEPLOYDIR}/firmware-hx8k.bin
}

