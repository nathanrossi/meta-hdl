SUMMARY = "A Python toolbox for building complex digital hardware"
HOMEPAGE = "https://m-labs.hk/migen/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe415a1adab562fdef3f458f01add8df"

SRC_URI = "git://github.com/m-labs/migen;protocol=https"
SRCREV = "657c0c72e63597162837809dfe3635d69a98cfd9"

PV = "0.8+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-colorama"

BBCLASSEXTEND = "native nativesdk"
