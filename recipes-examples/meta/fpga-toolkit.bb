require recipes-core/meta/buildtools-tarball.bb

# Force inclusion of buildinfo into the output SDK
inherit image-buildinfo

TOOLCHAIN_OUTPUTNAME = "fpga-toolkit-${SDK_ARCH}"
SDK_TITLE = "FPGA toolkit"

DESCRIPTION = "Target for building a portable toolkit/SDK for FPGA tools"
SUMMARY = "Portable toolkit/SDK for FPGA tools"
LICENSE = "MIT"

TOOLCHAIN_TARGET_TASK = ""
TOOLCHAIN_HOST_TASK = " \
    nativesdk-sdk-provides-dummy \
    nativesdk-python3-core \
    nativesdk-python3-modules \
    nativesdk-python3-misc \
    nativesdk-icarus-verilog \
    nativesdk-verilator \
    nativesdk-icestorm \
    nativesdk-prjtrellis \
    nativesdk-prjtrellis-openocd \
    nativesdk-yosys \
    nativesdk-yices2 \
    nativesdk-z3 \
    nativesdk-boolector \
    nativesdk-arachne-pnr \
    nativesdk-nextpnr-ice40 \
    nativesdk-nextpnr-ecp5 \
    nativesdk-migen \
    nativesdk-litex \
    nativesdk-litex-boards \
    nativesdk-litedram \
    nativesdk-liteeth \
    nativesdk-litevideo \
    nativesdk-tinyfpga-programmer \
    nativesdk-openocd \
    nativesdk-xc3sprog \
    nativesdk-dfu-util \
    "

