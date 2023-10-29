require nextpnr-common.inc

DEPENDS += "python3 boost libeigen abseil-cpp"

# Needed to ensure the correct sysconfig is used by cmake
inherit python3targetconfig

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

do_configure:prepend() {
    # force use of abseil-cpp from sysroot
    sed -i 's#add_subdirectory(3rdparty/abseil-cpp EXCLUDE_FROM_ALL)#find_package(absl REQUIRED)#g' ${S}/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"
