require nextpnr.inc
require nextpnr-bbasm-native.inc

DEPENDS += "prjtrellis-native prjtrellis-db-native"

EXTRA_OECMAKE += "-DARCH=ecp5"
EXTRA_OECMAKE += "-DTRELLIS_ROOT=${STAGING_DIR_NATIVE}${libdir_native}/prjtrellis"

do_configure_prepend() {
    # Setup TRELLIS_ROOT/database symlink to datadir
    ln -sf ../../../${datadir_native}/trellis/database ${STAGING_DIR_NATIVE}${libdir_native}/prjtrellis/database
}

