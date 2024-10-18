require litex.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=0ce55a94e98cbec2110586bb5264bd9a"

SRCREV = "f72368abaa8e66f819bc3e5e4a3b9ccf67134f3b"
PV = "2024.08+git${SRCPV}"

do_configure:prepend() {
    # remove use of "-nodefaultlibs", binutils-ld does not accept this arg
    sed -i 's/-nodefaultlibs//g' ${S}/litex/soc/software/common.mak
}

do_configure:prepend:class-native() {
    # Inject hosttools specifics into the some of the makefiles within the
    # build components in order to handle litex dependencies
    sed -i 's#LDFLAGS += .*\(-lpthread.*\)#LDFLAGS += \x24{BUILD_LDFLAGS} \1#g' ${S}/litex/build/sim/core/Makefile
}
