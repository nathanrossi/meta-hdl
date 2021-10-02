SUMMARY = "Python library for Intel HEX files manipulations"
HOMEPAGE = "https://pypi.python.org/pypi/intelhex/"
SECTION = "devel/python"
LICENSE = "BSD-3-Clause"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=4eba844696655c3eae07aca8e3a94772"

SRC_URI[sha256sum] = "892b7361a719f4945237da8ccf754e9513db32f5628852785aea108dcd250093"

inherit pypi setuptools3

do_install:append() {
    # fix up commands that use exact #! for python
    for i in ${D}${bindir}/*; do
        sed -i 's@^#!/usr/bin/python@#!/usr/bin/env python3@' $i
    done
}

BBCLASSEXTEND = "native nativesdk"
