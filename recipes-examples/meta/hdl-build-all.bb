SUMMARY = "Build all the meta-hdl recipes for target, native and nativesdk"
LICENSE = "MIT"

TARGETS = " \
    icarus-verilog \
    icestorm \
    prjtrellis \
    yosys \
    yices2 \
    arachne-pnr \
    nextpnr-ice40 \
    nextpnr-ecp5 \
    migen \
    tinyfpga-programmer \
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

# need to depend on the populate_sdk task
do_build[depends] += "fpga-toolkit:do_populate_sdk"

