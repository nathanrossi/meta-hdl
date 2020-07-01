require nextpnr.inc

DEPENDS += "icestorm-native"

# default ice40
EXTRA_OECMAKE += "-DARCH=ice40"
# icestorm/icebox chipdb
EXTRA_OECMAKE += "-DICEBOX_ROOT=${STAGING_DIR_NATIVE}${datadir_native}/icebox"

