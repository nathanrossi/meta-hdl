DESCRIPTION = "Documenting the Xilinx 7-series bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjxray"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10689772e9dbbd781bf98fd78416470a"

PV = "0+git${SRCPV}"
SRC_URI = "gitsm://github.com/SymbiFlow/prjxray;protocol=https"
SRCREV = "9749d6d51f47906af949c2fbb75b07f9d4f2a307"
S = "${WORKDIR}/git"

inherit python3native
inherit cmake
inherit setuptools3

RDEPENDS_${PN} += "python3-fasm python3-simplejson python3-intervaltree"

do_configure() {
    # suppress warnings as errors, some dependencies don't handle newer compilers
    sed -i 's#^add_compile_options.*#add_compile_options(-Wall)#g' ${S}/CMakeLists.txt

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
