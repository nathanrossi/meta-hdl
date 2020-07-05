DESCRIPTION = "Project X-Ray database"
HOMEPAGE = "https://github.com/SymbiFlow/prjxray-db"
LICENSE = "CC0-1.0"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=65d3616852dbf7b1a6d4b53b00626032"

SRC_URI = "git://github.com/SymbiFlow/prjxray-db;protocol=https"
SRCREV = "e45604d941615d6eef722f61fe857b6688088b6b"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/xray/database
    install ${S}/Info.md ${D}${datadir}/xray/database
    for i in ${S}/*; do
        if [ -d $i ]; then
            for f in $(cd ${S}; find $(basename $i) -type f); do
                install -D ${S}/$f ${D}${datadir}/xray/database/$f
            done
        fi
    done
}

FILES_${PN} += "${datadir}/xray/database"

# populate the databse into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/xray/database"

BBCLASSEXTEND = "native nativesdk"
