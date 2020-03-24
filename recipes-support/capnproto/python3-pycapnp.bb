SUMMARY = "Cap'n Proto serialization/RPC system - Python bindings"
HOMEPAGE = "https://github.com/capnproto/pycapnp"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8821e089698a4651beea79452a42922d"

SRC_URI = "git://github.com/capnproto/pycapnp;protocol=https"
SRCREV = "bca832c61df97226f6c105161e85fb2b44d7c9f9"
PV = "v1.0.0b1+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

CXXFLAGS_append = " -fPIC"

DEPENDS += "python3-cython-native"
DEPENDS += "capnproto"

BBCLASSEXTEND = "native nativesdk"
