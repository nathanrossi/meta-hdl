
# bbasm specific setup
python () {
    # needs a native build of bbasm tool
    d.appendVar("DEPENDS", " nextpnr-bbasm-native")
    d.appendVar("EXTRA_OECMAKE", " -DIMPORT_EXECUTABLES=${WORKDIR}/bbasm.cmake")
    bb.build.addtask("do_configure_bbasm", "do_configure", None, d)
}

do_configure_bbasm() {
    # create cmake setup for bbasm
    echo "add_executable(bbasm IMPORTED)" > ${WORKDIR}/bbasm.cmake
    echo "set_property(TARGET bbasm APPEND PROPERTY IMPORTED_CONFIGURATIONS RELEASE)" >> ${WORKDIR}/bbasm.cmake
    echo "set_target_properties(bbasm PROPERTIES IMPORTED_LOCATION_RELEASE \"${RECIPE_SYSROOT_NATIVE}${bindir_native}/bbasm\")" >> ${WORKDIR}/bbasm.cmake
}

