SUMMARY = "xc3sprog is a suite of utilities for programming Xilinx FPGAs, CPLDs, and EEPROMs"
HOMEPAGE = "http://xc3sprog.sourceforge.net/"
SECTION = "devel/fpga"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

DEPENDS += "libftdi libusb-compat"

SRC_URI = "svn://svn.code.sf.net/p/xc3sprog/code;module=trunk;protocol=https"
SRCREV = "795"
PV = "0.0+svnr${SRCPV}"

S = "${WORKDIR}/trunk"

do_configure:prepend() {
    # the cmakefile file trashes the flags passed in, this is used for cross-compiling
    sed -i '/^set(CMAKE_CXX_FLAGS/d' ${S}/CMakeLists.txt
}

inherit cmake

BBCLASSEXTEND = "native nativesdk"
