SUMMARY = "An Open-Source Digital Synthesis Flow"
HOMEPAGE = "http://opencircuitdesign.com/qflow/"
# "any" GPL version
LICENSE = "GPL-1.0 | GPL-2.0 | GPL-3.0"
SECTION = "devel/vlsi"

LIC_FILES_CHKSUM = "file://README;beginline=12;endline=12;md5=c49da37d84014c37b2fa86934d4f94c8"

SRC_URI = "git://github.com/RTimothyEdwards/qflow;protocol=https"
SRCREV = "b08302d68272ff15ae2f4d3c0771b3494cbf266a"

S = "${WORKDIR}/git"

PV = "1.4.19+git${SRCPV}"

inherit autotools autotools-brokensep

BBCLASSEXTEND = "native nativesdk"
