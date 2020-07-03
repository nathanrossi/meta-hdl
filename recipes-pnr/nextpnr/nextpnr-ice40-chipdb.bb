require nextpnr-common.inc

inherit allarch

# make this recipe available via native/nativesdk naming
PROVIDES += "${BPN}-native nativesdk-${BPN}"

EXTRA_OECMAKE += "-S ${S}/ice40"
EXTRA_OECMAKE += "-DARCH=ice40"

DEPENDS += "icestorm-native"
EXTRA_OECMAKE += "-DICESTORM_INSTALL_PREFIX=${STAGING_DIR_NATIVE}${prefix_native}"

do_install() {
    install -d ${D}${datadir}/chipdb/ice40
    install ${B}/chipdb/* ${D}${datadir}/chipdb/ice40
}

FILES_${PN} += "${datadir}/chipdb"
