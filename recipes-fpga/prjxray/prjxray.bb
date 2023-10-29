DESCRIPTION = "Documenting the Xilinx 7-series bit-stream format."
HOMEPAGE = "https://github.com/f4pga/prjxray"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=10689772e9dbbd781bf98fd78416470a"

PV = "0+git${SRCPV}"
SRC_URI = "gitsm://github.com/f4pga/prjxray;protocol=https;branch=master"
SRCREV = "b9ed4b9a81faccec779e22c5af3aeb90c38bbc66"
S = "${WORKDIR}/git"

inherit python3native
inherit cmake
inherit setuptools3

RDEPENDS:${PN} += "python3-fasm"
RDEPENDS:${PN} += "python3-intervaltree"
RDEPENDS:${PN} += "python3-numpy"
RDEPENDS:${PN} += "python3-pyjson5"
RDEPENDS:${PN} += "python3-pyyaml"
RDEPENDS:${PN} += "python3-simplejson"

do_configure() {
    # suppress warnings as errors, some dependencies don't handle newer compilers
    sed -i 's#^add_compile_options.*#add_compile_options(-Wall)#g' ${S}/CMakeLists.txt

    cmake_do_configure
    setuptools3_do_configure
}

do_compile() {
    cmake_do_compile
    setuptools3_do_compile
}

do_install() {
    cmake_do_install
    setuptools3_do_install
}

BBCLASSEXTEND = "native nativesdk"
