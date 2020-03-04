SUMMARY = "Small footprint and configurable video cores"
HOMEPAGE = "https://github.com/enjoy-digital/litevideo"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6d6f3af72cb210e0a30683d3e6f5eda"

SRC_URI = "git://github.com/enjoy-digital/litevideo;protocol=https"
SRCREV = "49d812694951a924617d8e429d72c0d4da96372a"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
