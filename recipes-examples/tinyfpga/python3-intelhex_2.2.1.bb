SUMMARY = "Python library for Intel HEX files manipulations"
HOMEPAGE = "https://pypi.python.org/pypi/intelhex/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4eba844696655c3eae07aca8e3a94772"

SRC_URI[md5sum] = "9ce7402e0fd33249aef52cbf437b4ab7"
SRC_URI[sha256sum] = "009d8511e0d50639230c39af9607deee771cf026f67ef7507a8c3fd4fa927832"

inherit pypi setuptools3

do_install_append() {
    # fix up commands that use exact #! for python
    for i in ${D}${bindir}/*; do
        sed -i 's@^#!/usr/bin/python@#!/usr/bin/env python3@' $i
    done
}

BBCLASSEXTEND = "native nativesdk"
