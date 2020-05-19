SUMMARY = "Fomu Workshop"
HOMEPAGE = "https://github.com/im-tomu/foboot"
SECTION = "devel/hdl"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/im-tomu/fomu-workshop;protocol=https"
SRCREV = "aacbc76542e199cac19d787560d993c058f5d093"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "fomu"

