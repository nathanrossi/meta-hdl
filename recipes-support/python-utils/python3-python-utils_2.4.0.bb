SUMMARY = "Python Utils is a module with some convenient utilities not included with the standard Python install"
HOMEPAGE = "https://github.com/WoLpH/python-utils"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f390846164a454d58ecb4f29c070a4b8"

inherit pypi setuptools3

SRC_URI[sha256sum] = "f21fc09ff58ea5ebd1fd2e8ef7f63e39d456336900f26bdc9334a03a3f7d8089"

RDEPENDS_${PN} += "python3-six"

BBCLASSEXTEND = "native nativesdk"
