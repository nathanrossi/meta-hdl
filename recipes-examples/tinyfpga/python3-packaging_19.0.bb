SUMMARY = "Core utilities for Python packages"
HOMEPAGE = "https://pypi.python.org/pypi/packaging/"
SECTION = "devel/python"
LICENSE = "BSD-2-Clause | Apache-2.0"

LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=faadaedca9251a90b205c9167578ce91 \
    file://LICENSE.BSD;md5=7bef9bf4a8e4263634d0597e7ba100b8 \
    file://LICENSE.APACHE;md5=a832eda17114b48ae16cda6a500941c2 \
    "

SRC_URI[md5sum] = "dd118dc389f8e90840d44240292bf3d9"
SRC_URI[sha256sum] = "0c98a5d0be38ed775798ece1b9727178c4469d9c3b4ada66e8e6b7849f8732af"

inherit pypi setuptools3

RDEPENDS_${PN} += " \
        ${PYTHON_PN}-pyparsing \
        ${PYTHON_PN}-six \
        "

BBCLASSEXTEND = "native nativesdk"
