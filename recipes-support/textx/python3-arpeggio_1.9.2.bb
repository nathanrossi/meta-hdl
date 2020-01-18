SUMMARY = "Parser interpreter based on PEG grammars written in Python"
HOMEPAGE = "https://github.com/textX/Arpeggio"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=33b8d1ba459a2fa4d801acfd1d1b7ceb"

PYPI_PACKAGE = "Arpeggio"

inherit pypi setuptools3

SRC_URI[md5sum] = "39667a626217c670bc634444be6e904a"
SRC_URI[sha256sum] = "948ce06163a48a72c97f4fe79ad3d1c1330b6fec4f22ece182fb60ef60bd022b"

do_compile_prepend() {
    # disable dependency on pytest
    sed -i '/setup_requires=/d' ${S}/setup.py
}

BBCLASSEXTEND = "native nativesdk"
