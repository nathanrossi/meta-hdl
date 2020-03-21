SUMMARY = "flake8 is a python tool that glues together pep8, pyflakes, mccabe, and third-party plugins to check the style and quality of some python code."
HOMEPAGE = "https://gitlab.com/pycqa/flake8"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75b26781f1adf1aa310bda6098937878"

inherit pypi setuptools3

SRC_URI[sha256sum] = "45681a117ecc81e870cbf1262835ae4af5e7a8b08e40b944a8a6e6b895914cfb"

BBCLASSEXTEND = "native nativesdk"
