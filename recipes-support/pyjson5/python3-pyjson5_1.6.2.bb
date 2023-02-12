SUMMARY = "JSON5 serializer and parser for Python 3 written in Cython."
HOMEPAGE = "https://github.com/Kijewski/pyjson5"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[sha256sum] = "227a1e5eff5c570281a9154002ad4e15dcbe7c93334fc1b8f3cd132481382a33"

DEPENDS += "python3-cython-native"

BBCLASSEXTEND = "native nativesdk"
