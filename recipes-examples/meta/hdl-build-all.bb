SUMMARY = "Build all the meta-hdl recipes for target, native and nativesdk"
LICENSE = "MIT"

TARGETS = " \
    icarus-verilog \
    verilator \
    icestorm \
    prjtrellis \
    prjxray \
    yosys \
    sby \
    yices2 \
    z3 \
    boolector \
    arachne-pnr \
    nextpnr-ice40 \
    nextpnr-ecp5 \
    migen \
    litex \
    ${LITEX_MODULES} \
    tinyfpga-programmer \
    xc3sprog \
    "

LITEX_MODULES = " \
    litedram \
    liteeth \
    liteiclink \
    litepcie \
    litesata \
    litescope \
    litesdcard \
    litevideo \
    \
    litex-boards \
    \
    litex-pythondata-cpu-vexriscv \
    litex-pythondata-cpu-vexriscv-smp \
    litex-pythondata-misc-tapcfg \
    litex-pythondata-software-compiler-rt \
    "

def targets_expand(d):
    deps = []
    for t in d.getVar("TARGETS").split():
        if t is not None:
            deps.append("{0}".format(t))
            deps.append("{0}-native".format(t))
            deps.append("nativesdk-{0}".format(t))
    return " ".join(deps)

DEPENDS += "${@targets_expand(d)}"

DEPENDS += "nextpnr-xilinx-native"

# GHDL is currently native only
DEPENDS += "ghdl-native"
DEPENDS += "ghdl-yosys-plugin-native"
do_build[depends] += "ghdl-yosys-plugin-native:do_check"

# standalone recipe targets
DEPENDS += "prjtrellis-examples-versa5g"
DEPENDS += "nextpnr-xilinx-examples-arty-a35"

# need to depend on the populate_sdk task
do_build[depends] += "fpga-toolkit:do_populate_sdk"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

inherit nopackages

