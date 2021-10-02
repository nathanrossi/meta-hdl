SUMMARY = "Domain-Specific Languages and parsers in Python made easy"
HOMEPAGE = "https://github.com/textX/textX"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=f22f950f1b1a9b633ccfb1a119c791e8"

PYPI_PACKAGE = "textX"

inherit pypi setuptools3

SRC_URI[sha256sum] = "265afc12d4ae421a7794c8cdc58c6eed44cc879f078cb54c32d5dc6bc74efbac"

DEPENDS += "python3-wheel-native"

RDEPENDS:${PN} += "python3-arpeggio python3-click"

BBCLASSEXTEND = "native nativesdk"
