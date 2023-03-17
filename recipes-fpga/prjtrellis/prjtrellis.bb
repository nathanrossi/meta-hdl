require prjtrellis.inc

inherit cmake python3native python3targetconfig

DEPENDS += "boost"

OECMAKE_SOURCEPATH = "${S}/libtrellis"
EXTRA_OECMAKE += "-DBUILD_PYTHON=yes"

PACKAGES += "${PN}-openocd"

FILES:${PN} += " \
        ${libdir}/trellis \
        ${datadir}/trellis/database \
        ${datadir}/trellis/misc/basecfgs \
        ${datadir}/trellis/timing \
        ${datadir}/trellis/util \
        "
FILES:${PN}-dev = ""
FILES:${PN}-openocd += "${datadir}/trellis/misc/openocd"

RDEPENDS:${PN} += "prjtrellis-db python3"

SYSROOT_DIRS_NATIVE:append = " ${datadir}/trellis"

BBCLASSEXTEND = "native nativesdk"
