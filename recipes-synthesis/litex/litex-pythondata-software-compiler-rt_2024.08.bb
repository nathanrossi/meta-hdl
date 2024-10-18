SUMMARY = "LiteX - compiler_rt software"
HOMEPAGE = "https://github.com/litex-hub/pythondata-software-compiler_rt"
SECTION = "devel/hdl"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f9cda00f501b4995fb04b5a5d896060"

SRC_URI = "git://github.com/litex-hub/pythondata-software-compiler_rt;protocol=https;branch=master"
SRCREV = "fcb03245613ccf3079cc833a701f13d0beaae09d"
PV = "2024.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

# package contains 'data' which includes test scripts, ignore the dependencies in them
INSANE_SKIP:${PN} += "file-rdeps"

BBCLASSEXTEND = "native nativesdk"
