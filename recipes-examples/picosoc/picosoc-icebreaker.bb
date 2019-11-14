require picosoc.inc

PROJ_NAME = "icebreaker"
SIM_TARGET = "icebsim"

do_deploy () {
    install -Dm 0644 ${B}/${PROJ_NAME}.bin ${DEPLOYDIR}/picosoc-icebreaker.bin
    install -Dm 0644 ${B}/${PROJ_NAME}_fw.bin ${DEPLOYDIR}/firmware-icebreaker.bin
}

