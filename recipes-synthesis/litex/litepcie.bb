SUMMARY = "Small footprint and configurable PCIe core"
HOMEPAGE = "https://github.com/enjoy-digital/litepcie"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=44143d0fdf0e8c41437a04b052be86c5"

SRC_URI = "git://github.com/enjoy-digital/litepcie;protocol=https"
SRCREV = "a5ba3a8152f49acacbbe0eefc9c7cc1a03b794ee"
PV = "2021.08+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
