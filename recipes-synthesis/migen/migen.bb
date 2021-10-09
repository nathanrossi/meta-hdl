SUMMARY = "A Python toolbox for building complex digital hardware"
HOMEPAGE = "https://m-labs.hk/migen/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=984d220f96f7cade953c15160221941b"

SRC_URI = "git://github.com/m-labs/migen;protocol=https"
SRCREV = "6e3f8e565704b4293174aedfb15b3470d233f528"

PV = "0.9.2+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += "python3-colorama"

BBCLASSEXTEND = "native nativesdk"
