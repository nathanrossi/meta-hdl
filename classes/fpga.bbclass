
def fpga_family_depends(d, family = None, native = True):
    family = family or d.getVar("FPGA_FAMILY")

    if not family:
        return ""

    depends = ["yosys"]
    if family == "ice40":
        depends.append("nextpnr-ice40")
        depends.append("icestorm")
    elif family == "ecp5":
        depends.append("nextpnr-ecp5")
        depends.append("prjtrellis")
        depends.append("prjtrellis-db")
    else:
        bb.fatal("Unknown FPGA family '{}'".format(family))

    if native:
        return " ".join(d + "-native" for d in depends)
    return " ".join(depends)

