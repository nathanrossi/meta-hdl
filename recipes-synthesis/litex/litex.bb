require litex.inc

DEFAULT_PREFERENCE = "-1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=dd3b74c72200b1eca57634230a03db2f"

SRCREV = "c1225736a8c3dc3fb46dfafcaff763bd5fe9b0a2"
PV = "2024.08+master+git${SRCPV}"

do_configure:prepend() {
    # remove use of "-nodefaultlibs", binutils-ld does not accept this arg
    sed -i 's/-nodefaultlibs//g' ${S}/litex/soc/software/common.mak
}

do_configure:prepend:class-native() {
    # Inject hosttools specifics into the some of the makefiles within the
    # build components in order to handle litex dependencies
    sed -i 's#LDFLAGS += .*\(-lpthread.*\)#LDFLAGS += \x24{BUILD_LDFLAGS} \1#g' ${S}/litex/build/sim/core/Makefile
}
