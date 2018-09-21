TOOLCHAIN_OUTPUTNAME ?= "fpga-toolkit-${SDK_ARCH}"
SDK_TITLE = "FPGA toolkit"

require recipes-core/meta/buildtools-tarball.bb

DESCRIPTION = "Target for building a portable toolkit/SDK for FPGA tools"
SUMMARY = "Portable toolkit/SDK for FPGA tools"
LICENSE = "MIT"

TOOLCHAIN_HOST_TASK = " \
    nativesdk-python3-core \
    nativesdk-python3-modules \
    nativesdk-python3-misc \
    nativesdk-icarus-verilog \
    nativesdk-icestorm \
    nativesdk-libtrellis \
    nativesdk-yosys \
    nativesdk-arachne-pnr \
    nativesdk-nextpnr-ice40 \
    nativesdk-nextpnr-ecp5 \
    nativesdk-migen \
    "

