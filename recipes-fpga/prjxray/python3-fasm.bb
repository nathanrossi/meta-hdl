SUMMARY = "FPGA Assembly (FASM) Parser and Generator "
HOMEPAGE = "https://github.com/chipsalliance/fasm"
LICENSE = "Apache-2.0"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/chipsalliance/fasm;protocol=https;branch=master"
SRCREV = "ca7df28b8e23061f139362392ee5414ac7a72328"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-cython-native"

RDEPENDS:${PN} += "python3-textx"

BBCLASSEXTEND = "native nativesdk"
