SUMMARY = "Fast, Extensible Progress Meter"
HOMEPAGE = "https://pypi.python.org/pypi/tqdm/"
SECTION = "devel/python"
LICENSE = "MIT & MPL-2.0"

LIC_FILES_CHKSUM = "file://LICENCE;md5=e77392b1859ca4a2610b6134ae72402d"

SRC_URI[md5sum] = "99a22940069dc5ce93328afd73d7c0f2"
SRC_URI[sha256sum] = "b856be5cb6cfaee3b2733655c7c5bbc7751291bb5d1a4f54f020af4727570b3e"

inherit pypi setuptools3

BBCLASSEXTEND = "native nativesdk"
