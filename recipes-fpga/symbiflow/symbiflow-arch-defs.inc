DESCRIPTION = "FOSS architecture definitions of FPGA hardware useful for doing PnR device generation."
HOMEPAGE = "https://github.com/SymbiFlow/symbiflow-arch-defs"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=3894bff8746d28aca6650d596b65b37a"

SRC_URI = "git://github.com/SymbiFlow/symbiflow-arch-defs;protocol=https"
SRCREV = "31e66fb74c760e2d35cc5be8f99fff0fdd670a48"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit allarch
inherit cmake python3native

# disable conda environment
EXTRA_OECMAKE += "-DUSE_CONDA=FALSE"

# netlistsvg setup
DEPENDS += "nodejs-native netlistsvg-native"
EXTRA_OECMAKE += "-DNETLISTSVG_BIN=netlistsvg"

DEPENDS += "python3-pytest-native python3-pytest-runner-native"
DEPENDS += "python3-yapf-native"
DEPENDS += "libxml2-native libxslt-native"
DEPENDS += "python3-lxml-native"
DEPENDS += "yosys-native"
DEPENDS += "verilog-to-routing-native"
DEPENDS += "icarus-verilog-native"

EXTRA_OECMAKE += "-DYOSYS_DATADIR=${STAGING_DIR_NATIVE}${datadir_native}/yosys"

# icestorm setup
DEPENDS += "icestorm-native"
# need bindir on pythonpath, 'import <icebox,icebox_asc2hlc,icebox_vlog>'
export PYTHONPATH = "${STAGING_DIR_NATIVE}${bindir_native}"
EXTRA_OECMAKE += "-DICESTORM_DB=${STAGING_DIR_NATIVE}${bindir_native}/icebox.py"

DEPENDS += "prjxray-db-native"
EXTRA_OECMAKE += "-DPRJXRAY_DIR=${STAGING_DIR_NATIVE}${datadir_native}/xray/database"

# disable non-ci targets
export CI = "1"

# dependencies not available or unused (board/device access), they are not needed for defs+tests
export TINYFPGAB = "/dev/null"
export TINYPROG = "/dev/null"
export OPENOCD = "/dev/null"

do_configure_prepend() {
    # want to be able to import them
    ln -sf icebox_asc2hlc ${STAGING_DIR_NATIVE}${bindir_native}/icebox_asc2hlc.py
    ln -sf icebox_vlog ${STAGING_DIR_NATIVE}${bindir_native}/icebox_vlog.py

    # tools use the path explictly, make them work
    if [ ! -L ${S}/third_party/prjxray-db -a -d ${S}/third_party/prjxray-db ]; then
        rmdir ${S}/third_party/prjxray-db
    fi
    ln -sf ${STAGING_DIR_NATIVE}${datadir_native}/xray/database ${S}/third_party/prjxray-db
    # prevent the CMake from including prjxray as a subdirectory
    sed -i '/^add_subdirectory.*third_party\/prjxray.*/d' ${S}/CMakeLists.txt

    # HACK: disable netlistsvg setup for now, it is not used during the build targets below
    sed -i 's/^\(setup_netlistsvg.*\)/# \1/g' ${S}/make/image_gen.cmake
}

do_install() {
    install -d ${D}${datadir}/symbiflow-arch
    for i in artix7 ice40; do
        echo ${B}
        ls ${B}
        for f in $(cd ${B}; find $i -name "arch.merged.xml"); do
            echo install -D ${B}/$f ${D}${datadir}/symbiflow-arch/$f
            install -D ${B}/$f ${D}${datadir}/symbiflow-arch/$f
        done
    done
}

FILES_${PN} += "${datadir}/symbiflow-arch"

# populate into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/symbiflow-arch"

BBCLASSEXTEND = "native nativesdk"
