SUMMARY = "A Python Progressbar library to provide visual (yet text based) progress to long running operations."
HOMEPAGE = "https://github.com/WoLpH/python-progressbar"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66059ac10145e829050ae4f023f276ba"

inherit pypi setuptools3

SRC_URI[sha256sum] = "2c21c14482016162852c8265da03886c2b4dea6f84e5a817ad9b39f6bd82a772"

RDEPENDS_${PN} += "python3-python-utils"

BBCLASSEXTEND = "native nativesdk"
