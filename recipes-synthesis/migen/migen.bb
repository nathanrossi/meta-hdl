SUMMARY = "A Python toolbox for building complex digital hardware"
HOMEPAGE = "https://m-labs.hk/migen/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe415a1adab562fdef3f458f01add8df"

SRC_URI = "git://github.com/m-labs/migen;protocol=https"
SRCREV = "57c44674c2a6c38bd01804e69db61a9efd287524"

PV = "0.8+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-colorama"

BBCLASSEXTEND = "native nativesdk"
