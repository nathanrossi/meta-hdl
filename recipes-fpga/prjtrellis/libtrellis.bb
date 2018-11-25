DESCRIPTION = "Documenting the Lattice ECP5 bit-stream format."
HOMEPAGE = "https://github.com/SymbiFlow/prjtrellis"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://../COPYING;md5=0a676b266f07580d67852e8e29fe81b1"

SRC_URI = "git://github.com/SymbiFlow/prjtrellis;protocol=https"
SRCREV = "7b84101c0c55964115815fb2be1e46a53bb2259a"

S = "${WORKDIR}/git/libtrellis"

PV = "0+git${SRCPV}"

inherit cmake python3native

DEPENDS += "boost"

EXTRA_OECMAKE += "-DBUILD_PYTHON=yes"

do_configure_prepend() {
    # HACK: work around python35 missing check
    sed -i 's#COMPONENTS python36#COMPONENTS python35#' ${S}/CMakeLists.txt
    # CMAKE_CXX_FLAGS are used to pass in --sysroot, this gets trashed otherwise
    sed -i 's#\(set(CMAKE_CXX_FLAGS "\)#\1${CMAKE_CXX_FLAGS} #g' ${S}/CMakeLists.txt
}

do_install_append() {
    # clean up empty trellis database content
    rm -rf ${D}${datadir}

    # install the pytrellis module
    install -Dm 0755 ${B}/pytrellis.so ${D}${libdir}/prjtrellis/libtrellis/pytrellis.so

    # install the utils/common python modules
    install -d ${D}${libdir}/prjtrellis/util/common
    for i in ${S}/../util/common/*; do
        install -m 0644 $i ${D}${libdir}/prjtrellis/util/common/
    done

    # install the timing/util python modules
    install -d ${D}${libdir}/prjtrellis/timing/util
    for i in ${S}/../timing/util/*; do
        install -m 0644 $i ${D}${libdir}/prjtrellis/timing/util/
    done
}

FILES_${PN}-dev = ""

FILES_${PN} += " \
        ${libdir}/libtrellis.so \
        ${libdir}/prjtrellis/libtrellis/pytrellis.so \
        ${libdir}/prjtrellis/util/common \
        ${libdir}/prjtrellis/timing/util \
        "
RDEPENDS_${PN} += "prjtrellis-db python3"

BBCLASSEXTEND = "native nativesdk"
