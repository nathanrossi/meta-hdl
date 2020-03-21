SUMMARY = "Python library for working Standard Delay Format (SDF) Timing Annotation files."
HOMEPAGE = "https://github.com/SymbiFlow/python-sdf-timing"
SECTION = "devel/fpga"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=1ec16110c4d7e0a155d03a00c6d74a7a"

SRC_URI = "git://github.com/SymbiFlow/python-sdf-timing;protocol=https"
SRCREV = "d557a5d438a8f512ded6d26b500599a436979c47"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-ply"

do_compile_prepend() {
    # disable dependency on pytest
    sed -i '/setup_requires=/d' ${S}/setup.py
}

BBCLASSEXTEND = "native nativesdk"
