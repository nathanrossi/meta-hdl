SUMMARY = "Editable interval tree data structure for Python 2 and 3"
HOMEPAGE = "https://github.com/chaimleib/intervaltree"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit pypi setuptools3

SRC_URI[md5sum] = "4159801f23d2d9eb98db6c60f9c3b665"
SRC_URI[sha256sum] = "cb4f61c81dcb4fea6c09903f3599015a83c9bdad1f0bbd232495e6681e19e273"

RDEPENDS_${PN} += "python3-sortedcontainers"

BBCLASSEXTEND = "native nativesdk"
