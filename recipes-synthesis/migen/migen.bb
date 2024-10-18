SUMMARY = "A Python toolbox for building complex digital hardware"
HOMEPAGE = "https://m-labs.hk/migen/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=984d220f96f7cade953c15160221941b"

SRC_URI = "git://github.com/m-labs/migen;protocol=https;branch=master"
SRCREV = "c19ae9f8ae162ffe2d310a92bfce53ac2a821bc8"

PV = "0.9.2+git${SRCPV}"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

RDEPENDS:${PN} += "python3-colorama"

BBCLASSEXTEND = "native nativesdk"
