SUMMARY = "JSON5 serializer and parser for Python 3 written in Cython."
HOMEPAGE = "https://github.com/Kijewski/pyjson5"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi python_setuptools_build_meta

SRC_URI[sha256sum] = "c1158a27c72c99eb3caa817c70a6218028e7f48fca1736db6d05852b1586ce42"

DEPENDS += "python3-cython-native"

BBCLASSEXTEND = "native nativesdk"
