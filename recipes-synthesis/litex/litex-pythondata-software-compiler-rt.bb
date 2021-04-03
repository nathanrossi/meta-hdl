SUMMARY = "LiteX - compiler_rt software"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-compiler_rt"
SECTION = "devel/hdl"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://github.com/litex-hub/pythondata-software-compiler_rt;protocol=https"
SRCREV = "fcb03245613ccf3079cc833a701f13d0beaae09d"
PV = "2020.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

# package contains 'data' which includes test scripts, ignore the dependencies in them
INSANE_SKIP_${PN} += "file-rdeps"

BBCLASSEXTEND = "native nativesdk"
