DESCRIPTION = "Project Trellis database"
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis-db"
LICENSE = "CC0-1.0"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=65d3616852dbf7b1a6d4b53b00626032"

SRC_URI = "git://github.com/SymbiFlow/prjtrellis-db;protocol=https"
SRCREV = "5b5bb70bae13e6b8c971b4b2d26931f4a64b51bc"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/trellis/database
    install ${S}/devices.json ${D}${datadir}/trellis/database
    for i in ${S}/*; do
        if [ -d $i ]; then
            for f in $(cd ${S}; find $(basename $i) -type f); do
                install -D ${S}/$f ${D}${datadir}/trellis/database/$f
            done
        fi
    done
}

FILES_${PN} += "${datadir}/trellis/database"

# populate the databse into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/trellis/database"

BBCLASSEXTEND = "native nativesdk"
