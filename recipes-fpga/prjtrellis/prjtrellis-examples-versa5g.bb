require prjtrellis.inc

DEPENDS += "yosys-native nextpnr-ecp5-native prjtrellis-native prjtrellis-db-native"

inherit python3native

inherit allarch

do_configure[noexec] = "1"

do_compile() {
    # HACK: related issue/change? https://github.com/YosysHQ/yosys/issues/3109
    sed -i 's/yosys.*/yosys -p "read_verilog $<; synth_ecp5 -json $@"/g' ${S}/examples/versa5g/Makefile

    oe_runmake -C ${S}/examples/versa5g
}

