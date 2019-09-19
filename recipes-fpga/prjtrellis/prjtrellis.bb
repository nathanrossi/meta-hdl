require prjtrellis.inc

inherit cmake python3native

DEPENDS += "boost"

OECMAKE_SOURCEPATH = "${S}/libtrellis"
EXTRA_OECMAKE += "-DBUILD_PYTHON=yes"

do_configure_prepend() {
    # HACK: work around python35 missing check
    sed -i 's#COMPONENTS python36#COMPONENTS python35#' ${S}/libtrellis/CMakeLists.txt
    # CMAKE_CXX_FLAGS are used to pass in --sysroot, this gets trashed otherwise
    sed -i 's#\(set(CMAKE_CXX_FLAGS "\)#\1${CMAKE_CXX_FLAGS} #g' ${S}/libtrellis/CMakeLists.txt
}

do_install_append() {
    # clean up empty trellis database content
    rm -rf ${D}${datadir}

    # install the pytrellis module
    install -Dm 0755 ${B}/pytrellis.so ${D}${libdir}/prjtrellis/libtrellis/pytrellis.so

    # install the utils/common python modules
    install -d ${D}${libdir}/prjtrellis/util/common
    for i in ${S}/util/common/*; do
        install -m 0644 $i ${D}${libdir}/prjtrellis/util/common/
    done

    # install the timing/util python modules
    install -d ${D}${libdir}/prjtrellis/timing/util
    for i in ${S}/timing/util/*; do
        install -m 0644 $i ${D}${libdir}/prjtrellis/timing/util/
    done

    install -d ${D}${datadir}/trellis/misc/openocd
    for i in ${S}/misc/openocd/*.cfg; do
        install $i ${D}${datadir}/trellis/misc/openocd/
    done
}

PACKAGES += "${PN}-openocd"

FILES_${PN} += " \
        ${libdir}/trellis \
        ${libdir}/prjtrellis/libtrellis \
        ${libdir}/prjtrellis/util/common \
        ${libdir}/prjtrellis/timing/util \
        "
FILES_${PN}-dev = ""
FILES_${PN}-openocd += "${datadir}/trellis/misc/openocd"

RDEPENDS_${PN} += "prjtrellis-db python3"

SYSROOT_DIRS_NATIVE_append = " ${datadir}/trellis/misc/openocd"

BBCLASSEXTEND = "native nativesdk"
