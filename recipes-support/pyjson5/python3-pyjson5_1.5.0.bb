SUMMARY = "JSON5 serializer and parser for Python 3 written in Cython."
HOMEPAGE = "https://github.com/Kijewski/pyjson5"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[sha256sum] = "aaba2f73e6c2194fbfbf214410320ff96245c40fff8c9c734a4d6e2d9881bbc6"

BBCLASSEXTEND = "native nativesdk"
