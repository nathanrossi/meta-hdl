require nextpnr.inc

# only build the bba subdirectory
EXTRA_OECMAKE += "-S ${S}/bba"
EXTRA_OECMAKE += "-DARCH=generic"

do_install() {
    # modify the path to point at the install path (this gets correctly rewritten by staging)
    sed -i 's#${B}#${bindir}#g' ${B}/bba-export.cmake
    install -D ${B}/bba-export.cmake ${D}${includedir}/nextpnr/bba-export.cmake
    install -Dm 744 ${B}/bbasm ${D}${bindir}/bbasm
}

