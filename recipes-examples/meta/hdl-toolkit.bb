require recipes-core/meta/buildtools-tarball.bb

TOOLCHAIN_OUTPUTNAME = "hdl-toolkit-${SDK_ARCH}"
SDK_TITLE = "HDL toolkit"

DESCRIPTION = "Target for building a portable toolkit/SDK for HDL simulation, synthesis and verification tools"
SUMMARY = "Portable toolkit/SDK for HDL tools"
LICENSE = "MIT"

TOOLCHAIN_TARGET_TASK = ""
TOOLCHAIN_HOST_TASK = " \
    nativesdk-python3-core \
    nativesdk-python3-modules \
    nativesdk-python3-misc \
    nativesdk-icarus-verilog \
    nativesdk-verilator \
    nativesdk-ghdl \
    nativesdk-ghdl-yosys-plugin \
    nativesdk-yosys \
    nativesdk-yosys-smtbmc \
    nativesdk-sby \
    nativesdk-yices2 \
    nativesdk-z3 \
    nativesdk-boolector \
    "

