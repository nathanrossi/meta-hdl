SUMMARY = "vtr-xml-utils"
HOMEPAGE = "https://github.com/SymbiFlow/python-sdf-timing"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=2acb15b952782dfbe73d8f8698be6245"

SRC_URI = "git://github.com/SymbiFlow/vtr-xml-utils;protocol=https"
SRCREV = "cbe5c2b654262f9dca9ebee0577d79eb84935542"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-lxml"

do_compile_prepend() {
    # disable dependency on pytest
    sed -i '/setup_requires=/d' ${S}/setup.py
}

BBCLASSEXTEND = "native nativesdk"
