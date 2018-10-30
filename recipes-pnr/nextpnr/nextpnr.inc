DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/YosysHQ/nextpnr"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "git://github.com/YosysHQ/nextpnr;protocol=https"
SRCREV = "49f2fff2b06cefd59a7d314c6e049f224bb2ee11"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit cmake python3native

DEPENDS += "python3 boost"

# don't build the gui components
EXTRA_OECMAKE += "-DBUILD_GUI=no"

do_configure_prepend() {
    # HACK: work around python35 missing check
    sed -i 's#COMPONENTS python36#COMPONENTS python35#' ${S}/CMakeLists.txt
}

BBCLASSEXTEND = "native nativesdk"

# bbasm specific setup
python () {
    if not bb.data.inherits_class("native", d):
        # needs a native build of bbasm tool
        d.appendVar("DEPENDS", " {0}-native".format(d.getVar("BPN")))
        d.appendVar("EXTRA_OECMAKE", " -DIMPORT_EXECUTABLES=${WORKDIR}/bbasm.cmake")
        bb.build.addtask("do_configure_bbasm", "do_configure", None, d)
}

do_configure_bbasm() {
    # create cmake setup for bbasm
    echo "add_executable(bbasm IMPORTED)" > ${WORKDIR}/bbasm.cmake
    echo "set_property(TARGET bbasm APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)" >> ${WORKDIR}/bbasm.cmake
    echo "set_target_properties(bbasm PROPERTIES IMPORTED_LOCATION_RELEASE \"${RECIPE_SYSROOT_NATIVE}${bindir_native}/bbasm\")" >> ${WORKDIR}/bbasm.cmake
}

do_install_append_class-native() {
    install -Dm 744 ${B}/bbasm ${D}${bindir}/bbasm
}

