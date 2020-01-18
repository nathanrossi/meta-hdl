SUMMARY = "A Python toolbox for building complex digital hardware"
HOMEPAGE = "https://m-labs.hk/migen/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=984d220f96f7cade953c15160221941b"

SRC_URI = "git://github.com/m-labs/migen;protocol=https"
SRCREV = "7bc80d79d87d3e659459c5afdc5a36db10bf2115"

PV = "0.8+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-colorama"

BBCLASSEXTEND = "native nativesdk"
