require nextpnr-common.inc

DEPENDS += "python3 boost libeigen"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

python () {
    if not bb.data.inherits_class("native", d):
        # cross bbasm specific setup
        d.appendVar("DEPENDS", " nextpnr-bbasm-native")
        d.appendVar("EXTRA_OECMAKE", " -DBBA_IMPORT=${RECIPE_SYSROOT_NATIVE}${includedir_native}/nextpnr/bba-export.cmake")

    # setup target fpga architecture
    suffix = d.getVar("BPN").replace("nextpnr-", "")
    d.appendVar("EXTRA_OECMAKE", " -DARCH={}".format(suffix))

    if suffix in ["ice40", "ecp5"]:
        # use prebuilt chipdb
        d.appendVar("DEPENDS", " nextpnr-{}-chipdb".format(suffix))
        # path to built chipdb (allarch packages are "target")
        d.appendVar("EXTRA_OECMAKE", " -D" + suffix.upper() + "_CHIPDB=${RECIPE_SYSROOT}${target_datadir}/chipdb/" + suffix)
}

BBCLASSEXTEND = "native nativesdk"
