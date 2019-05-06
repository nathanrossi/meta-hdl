require prjtrellis.inc

DEPENDS += "yosys-native nextpnr-ecp5-native prjtrellis-native prjtrellis-db-native"

inherit python3native

inherit allarch

do_configure[noexec] = "1"

do_compile() {
    oe_runmake -C ${S}/examples/versa5g
}

