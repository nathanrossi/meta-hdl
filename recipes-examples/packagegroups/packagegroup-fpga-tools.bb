SUMMARY = "Packages for FPGA tools and synthesis flows"

inherit packagegroup
inherit fpga

python () {
    # dynamically populate fpga tool dependencies
    for fpga in ["ice40", "ecp5"]:
        pn = "{}-{}".format(d.getVar("PN"), fpga)
        d.appendVar("PACKAGES", " {}".format(pn))
        d.appendVar(d.expand("RDEPENDS:${PN}"), " {}".format(pn))
        d.setVar("RDEPENDS:{}".format(pn),
            fpga_family_depends(d, family = fpga, native = False))
        d.setVar("ALLOW_EMPTY:{}".format(pn), "1")
}

PACKAGES += " \
    ${PN}-synthesis \
    ${PN}-simulation \
    "

RDEPENDS:${PN} = " \
    ${PN}-synthesis \
    ${PN}-simulation \
    "

RDEPENDS:${PN}-synthesis = " \
    yosys \
    "

RDEPENDS:${PN}-simulation = " \
    icarus-verilog \
    verilator \
    "

