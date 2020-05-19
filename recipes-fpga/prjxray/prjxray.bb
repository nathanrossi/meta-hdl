DESCRIPTION = "Documenting the Xilinx 7-series bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjxray"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=d45384788db1e5711ff67f3912c54749"

PV = "0+git${SRCPV}"
SRC_URI = "gitsm://github.com/SymbiFlow/prjxray;protocol=https"
SRCREV = "acb6210a7606c5fd72e5ea729aedb37be23f505d"
S = "${WORKDIR}/git"

inherit cmake python3native

RDEPENDS_${PN} += "python3-fasm python3-simplejson python3-intervaltree"

do_configure_prepend() {
    # suppress warnings as errors, some dependencies don't handle newer compilers
    sed -i 's#^add_compile_options.*#add_compile_options(-Wall)#g' ${S}/CMakeLists.txt
}

do_install_append() {
    # install prjxray module
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    cp -r ${S}/prjxray ${D}${PYTHON_SITEPACKAGES_DIR}

    # install the tools binaries
    install -d ${D}${bindir}/prjxray
    for i in bitread bittool frame_address_decoder gen_part_base_yaml segmatch xc7patch xc7frames2bit; do
        install -m 0755 ${B}/tools/$i ${D}${bindir}/prjxray/$(basename $i)
    done
    # install the utils scripts
    for i in fasm2frames.py; do
        install -m 0755 ${S}/utils/$i ${D}${bindir}/prjxray/$(basename $i)
    done
}

FILES_${PN} += "${PYTHON_SITEPACKAGES_DIR}/prjxray"

BBCLASSEXTEND = "native nativesdk"
