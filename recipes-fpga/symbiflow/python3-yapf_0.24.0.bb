SUMMARY = "A formatter for Python files"
HOMEPAGE = "https://github.com/google/yapf"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[md5sum] = "82507eb64263ddf03be6f755a64f5cb3"
SRC_URI[sha256sum] = "cebb6faf35c9027c08996c07831b8971f3d67c0eb615269f66dfd7e6815fdc2a"

BBCLASSEXTEND = "native nativesdk"
