DESCRIPTION = "FOSS architecture definitions of FPGA hardware useful for doing PnR device generation."
HOMEPAGE = "https://github.com/SymbiFlow/symbiflow-arch-defs"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=3894bff8746d28aca6650d596b65b37a"

SRC_URI = "git://github.com/SymbiFlow/symbiflow-arch-defs;protocol=https"
SRCREV = "5588c2891f4d773cbbeb85843626fd4972bebeee"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI += " \
        file://0001-common-cmake-env.cmake-Handle-add_conda_pip-with-NO_.patch \
        file://0002-common-cmake-env.cmake-Replace-non-conda-empty-targe.patch \
        file://0003-ice40-Fix-up-use-of-CONDA_DIR.patch \
        file://0004-CMakeLists.txt-vtr_xml_utils-does-not-provide-an-exe.patch \
        file://0005-xc-xc7-CMakeLists.txt-Add-prjxray-as-add_thirdparty_.patch \
        file://0006-xc-xc7-Fix-up-use-of-CONDA_DIR.patch \
        "

inherit allarch
inherit native
inherit cmake
inherit python3native

# disable conda environment
EXTRA_OECMAKE += "-DUSE_CONDA=FALSE"

# netlistsvg setup
DEPENDS += "nodejs-native"
#DEPENDS += "netlistsvg-native"
EXTRA_OECMAKE += "-DNETLISTSVG_BIN=netlistsvg"

DEPENDS += "icarus-verilog-native"

DEPENDS += "libxml2-native libxslt-native"
DEPENDS += "python3-pytest-native python3-pytest-runner-native"
DEPENDS += "python3-yapf-native"
DEPENDS += "python3-lxml-native"
DEPENDS += "python3-flake8-native"
DEPENDS += "python3-progressbar2-native"
DEPENDS += "python3-fasm-native"
DEPENDS += "python3-sdf-timing-native"
DEPENDS += "python3-vtr-xml-utils-native"
DEPENDS += "python3-intervaltree-native"
DEPENDS += "python3-numpy-native"
DEPENDS += "python3-hilbertcurve-native"
DEPENDS += "python3-pycapnp-native"

DEPENDS += "verilog-to-routing-native"
EXTRA_OECMAKE += "-DVPR_CAPNP_SCHEMA_DIR=${STAGING_DIR_NATIVE}${prefix_native}/capnp"

# yosys setup
DEPENDS += "yosys-native"
EXTRA_OECMAKE += "-DYOSYS_DATADIR=${STAGING_DIR_NATIVE}${datadir_native}/yosys"

# icestorm setup
DEPENDS += "icestorm-native"
# need bindir on pythonpath, 'import <icebox,icebox_asc2hlc,icebox_vlog>'
EXTRA_OECMAKE += "-DICESTORM_DB=${STAGING_DIR_NATIVE}${bindir_native}/icebox.py"
EXTRA_OECMAKE += "-DICEBOX_PATH=${STAGING_DIR_NATIVE}${bindir_native}"

DEPENDS += "prjxray-native prjxray-db-native"
PATH_prepend = "${STAGING_DIR_NATIVE}${bindir_native}/prjxray:"
EXTRA_OECMAKE += "-DPRJXRAY_DIR=${STAGING_DIR_NATIVE}${datadir_native}/xray/database"

# dependencies not available or unused (board/device access), they are not needed for defs+tests
export TINYFPGAB = "/dev/null"
export TINYPROG = "/dev/null"
export OPENOCD = "/dev/null"

# HACK: needs vivado, /dev/null is better
export XRAY_VIVADO_SETTINGS = "/dev/null"

do_configure_prepend() {
    # want to be able to import them
    ln -sf icebox_asc2hlc ${STAGING_DIR_NATIVE}${bindir_native}/icebox_asc2hlc.py
    ln -sf icebox_vlog ${STAGING_DIR_NATIVE}${bindir_native}/icebox_vlog.py
    # provide access to icefuzz
    mkdir -p ${S}/third_party/icestorm
    ln -sf ${STAGING_DIR_NATIVE}${libdir_native}/icestorm/icefuzz ${S}/third_party/icestorm

    # HACK: disable netlistsvg setup for now, it is not used during the tested build targets
    sed -i 's/^\(setup_netlistsvg.*\)/# \1/g' ${S}/common/cmake/image_gen.cmake
}

#do_install_append() {
#    install -d ${D}${datadir}/symbiflow-arch
#    for i in artix7 ice40; do
#        for f in $(cd ${B}; find $i -name "arch.merged.xml"); do
#            echo install -D ${B}/$f ${D}${datadir}/symbiflow-arch/$f
#            install -D ${B}/$f ${D}${datadir}/symbiflow-arch/$f
#        done
#    done
#}

FILES_${PN} += "${datadir}/symbiflow-arch"

# populate into the sysroot (not enabled by default on -native)
SYSROOT_DIRS_NATIVE_append = " ${datadir}/symbiflow-arch"

