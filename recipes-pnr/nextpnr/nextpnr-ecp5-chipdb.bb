require nextpnr-common.inc

inherit allarch

# make this recipe available via native/nativesdk naming
PROVIDES += "${BPN}-native nativesdk-${BPN}"

EXTRA_OECMAKE += "-DARCH=ecp5"
EXTRA_OECMAKE += "-S ${S}/ecp5"

DEPENDS += "prjtrellis-native prjtrellis-db-native"
EXTRA_OECMAKE += "-DTRELLIS_INSTALL_PREFIX=${STAGING_DIR_NATIVE}${prefix_native}"

do_install() {
    install -d ${D}${datadir}/chipdb/ecp5
    install ${B}/chipdb/* ${D}${datadir}/chipdb/ecp5
}

FILES:${PN} += "${datadir}/chipdb"
