DESCRIPTION = "Documenting the Xilinx 7-series bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjxray"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10689772e9dbbd781bf98fd78416470a"

PV = "0+git${SRCPV}"
SRC_URI = "gitsm://github.com/SymbiFlow/prjxray;protocol=https"
SRCREV = "77e8b24c883bd527b511413f1939c3a34a237c1c"
S = "${WORKDIR}/git"

inherit python3native
inherit cmake
inherit setuptools3

RDEPENDS_${PN} += "python3-fasm python3-simplejson python3-intervaltree"

do_configure() {
    # suppress warnings as errors, some dependencies don't handle newer compilers
    sed -i 's#^add_compile_options.*#add_compile_options(-Wall)#g' ${S}/CMakeLists.txt
    # HACK: force installation of 'utils' to get fasm2frames working again
    sed -i "s/packages=\[.*\]/packages=['prjxray','utils']/g" ${S}/setup.py

    cmake_do_configure
    distutils3_do_configure
}

do_compile() {
    cmake_do_compile
    distutils3_do_compile
}

do_install() {
    cmake_do_install
    distutils3_do_install
}

BBCLASSEXTEND = "native nativesdk"
