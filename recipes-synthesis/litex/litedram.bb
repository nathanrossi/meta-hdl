SUMMARY = "Small footprint and configurable DRAM core"
HOMEPAGE = "https://github.com/enjoy-digital/litedram"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=825d77c5dc305ac32d5329b5136e62a4"

SRC_URI = "git://github.com/enjoy-digital/litedram;protocol=https"
SRCREV = "198bcbab676e2b4065e5b6a7dc8a7733bae8315a"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
