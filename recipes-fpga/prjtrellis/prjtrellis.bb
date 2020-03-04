require prjtrellis.inc

inherit cmake python3native

DEPENDS += "boost"

OECMAKE_SOURCEPATH = "${S}/libtrellis"
EXTRA_OECMAKE += "-DBUILD_PYTHON=yes"

PACKAGES += "${PN}-openocd"

FILES_${PN} += " \
        ${libdir}/trellis \
        ${datadir}/trellis/database \
        ${datadir}/trellis/misc/basecfgs \
        ${datadir}/trellis/timing \
        ${datadir}/trellis/util \
        "
FILES_${PN}-dev = ""
FILES_${PN}-openocd += "${datadir}/trellis/misc/openocd"

RDEPENDS_${PN} += "prjtrellis-db python3"

SYSROOT_DIRS_NATIVE_append = " ${datadir}/trellis"

BBCLASSEXTEND = "native nativesdk"
