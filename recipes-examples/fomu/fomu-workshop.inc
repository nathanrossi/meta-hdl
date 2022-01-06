SUMMARY = "Fomu Workshop"
HOMEPAGE = "https://github.com/im-tomu/foboot"
SECTION = "devel/hdl"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/im-tomu/fomu-workshop;protocol=https;branch=master"
SRCREV = "6e6318d820271750a99c8e419ee1b9abd9aa6b81"
PV = "0.1+git${SRCPV}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "fomu"

