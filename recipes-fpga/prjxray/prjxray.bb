DESCRIPTION = "Documenting the Xilinx 7-series bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjxray"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;md5=d45384788db1e5711ff67f3912c54749"

SRC_URI = " \
        git://github.com/SymbiFlow/prjxray;protocol=https;name=prjxray \
        git://github.com/google/cctz;protocol=https;destsuffix=git/third_party/cctz;name=cctz \
        git://github.com/abseil/abseil-cpp;protocol=https;destsuffix=git/third_party/abseil-cpp;name=abseil-cpp \
        git://github.com/google/googletest;protocol=https;destsuffix=git/third_party/googletest;name=googletest \
        git://github.com/jbeder/yaml-cpp;protocol=https;destsuffix=git/third_party/yaml-cpp;name=yaml-cpp \
        git://github.com/gflags/gflags;protocol=https;destsuffix=git/third_party/gflags;name=gflags \
        "
SRCREV_prjxray = "825d2dd9f003471b180ad2381d4e4ce3e202207f"
SRCREV_cctz = "a59b930afc821e5f5a0b868dfee56482075db185"
SRCREV_abseil-cpp = "95ddf85f8075d5645a754bac5742b72ec9c81f2a"
SRCREV_googletest = "d175c8bf823e709d570772b038757fadf63bc632"
SRCREV_yaml-cpp = "86ae3a5aa7e2109d849b2df89176d6432a35265d"
SRCREV_gflags = "77592648e3f3be87d6c7123eb81cbad75f9aef5a"
SRCREV_FORMAT = "prjxray"

S = "${WORKDIR}/git"

PV = "0+git${SRCPV}"

inherit cmake python3native

do_configure_prepend() {
    # suppress warnings as errors, some dependencies don't handle newer compilers
    sed -i 's#^add_compile_options.*#add_compile_options(-Wall)#g' ${S}/CMakeLists.txt
}

do_install_append() {
    # install the tools binaries
    install -d ${D}${bindir}/prjxray
    for i in bitread bittool frame_address_decoder gen_part_base_yaml segmatch xc7patch; do
        install -m 0755 ${B}/tools/$i ${D}${bindir}/prjxray/$(basename $i)
    done
}

BBCLASSEXTEND = "native nativesdk"
