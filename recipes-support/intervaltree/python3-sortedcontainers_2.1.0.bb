SUMMARY = "Sorted Containers is an Apache2 licensed sorted collections library, written in pure-Python, and fast as C-extensions."
HOMEPAGE = "http://www.grantjenks.com/docs/sortedcontainers/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2ebe8995d6e8700e860ad047d221e04"

inherit pypi setuptools3

SRC_URI[md5sum] = "41a4a1eaf7b85e6b3beb14cfb160bc27"
SRC_URI[sha256sum] = "974e9a32f56b17c1bac2aebd9dcf197f3eb9cd30553c5852a3187ad162e1a03a"

BBCLASSEXTEND = "native nativesdk"
