SUMMARY = "LiteX's fork of a fast open source processor emulator"
HOMEPAGE = "https://github.com/timvideos/qemu-litex"

QEMU_TARGETS = "lm32"

require recipes-devtools/qemu/qemu.inc

LIC_FILES_CHKSUM = " \
		file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
		file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913 \
		"

SRC_URI = "git://github.com/timvideos/qemu-litex.git;protocol=https"
SRCREV = "${AUTOREV}"

PV = "3.0.50+litex+git${SRCPV}"

S = "${WORKDIR}/git"

# disable some default configs
PACKAGECONFIG_remove = "kvm"

# append a suffix dir, to allow multiple versions of QEMU to be installed
EXTRA_OECONF_append = " \
		--bindir=${bindir}/qemu-litex \
		--libexecdir=${libexecdir}/qemu-litex \
		"

# need common headers
DEPENDS += "litex"
CFLAGS_append = " -I${STAGING_DIR_HOST}${libdir}/python3.5/site-packages/litex/soc/software/include"

# allow the build to include from the generated headers
# TODO: this is a bit hacky, qemu should probably be runtime configured
CFLAGS_append = " -I${RECIPE_SYSROOT}/usr/include/litex/"
do_configure[depends] += "litex-firmware-platform:do_populate_sysroot"

do_install_append() {
	# Prevent QA warnings about installed ${localstatedir}/run
	if [ -d ${D}${localstatedir}/run ]; then rmdir ${D}${localstatedir}/run; fi
}

