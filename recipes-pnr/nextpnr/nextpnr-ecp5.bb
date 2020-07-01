require nextpnr.inc

DEPENDS += "prjtrellis-native prjtrellis-db-native"

EXTRA_OECMAKE += "-DARCH=ecp5"
EXTRA_OECMAKE += "-DTRELLIS_INSTALL_PREFIX=${STAGING_DIR_NATIVE}${prefix_native}"

