SUMMARY = "Construct Hilbert Curves."
HOMEPAGE = "https://github.com/galtay/hilbertcurve"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32b1dcedbb0ac77f97ac9065cba9ceb"

inherit pypi setuptools3

SRC_URI[sha256sum] = "cb041f40f1b26843ef093d104954c55cf0dba5b6e976371a510432c193ee951d"

BBCLASSEXTEND = "native nativesdk"
