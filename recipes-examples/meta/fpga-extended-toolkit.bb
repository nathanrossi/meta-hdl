require fpga-toolkit.bb

TOOLCHAIN_OUTPUTNAME = "fpga-extended-toolkit-${SDK_ARCH}"
SDK_TITLE = "FPGA extended toolkit"

TOOLCHAIN_HOST_TASK += " \
    nativesdk-renode \
    "

