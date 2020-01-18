SUMMARY = "Domain-Specific Languages and parsers in Python made easy"
HOMEPAGE = "https://github.com/textX/textX"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f22f950f1b1a9b633ccfb1a119c791e8"

PYPI_PACKAGE = "textX"

inherit pypi setuptools3

SRC_URI[md5sum] = "e39861f50f03f168817b5c4c31e3e787"
SRC_URI[sha256sum] = "159c710f98401f090eb39fa44cd20bc48d697cb96684d1f457428c7ab37bb61c"

RDEPENDS_${PN} += "python3-arpeggio python3-click"

BBCLASSEXTEND = "native nativesdk"
