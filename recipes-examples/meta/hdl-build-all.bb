SUMMARY = "Build all the meta-hdl recipes for target, native and nativesdk"
LICENSE = "MIT"

TARGETS = " \
    icarus-verilog \
    verilator \
    icestorm \
    prjtrellis \
    prjxray \
    yosys \
    yices2 \
    z3 \
    boolector \
    arachne-pnr \
    nextpnr-ice40 \
    nextpnr-ecp5 \
    nextpnr-xilinx \
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
    litevideo \
    \
    litex-boards \
    \
    litex-pythondata-cpu-vexriscv \
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

# standalone recipe targets
DEPENDS += "prjtrellis-examples-versa5g"
DEPENDS += "nextpnr-xilinx-examples-arty-a35"

# need to depend on the populate_sdk task
do_build[depends] += "fpga-toolkit:do_populate_sdk"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

inherit nopackages

