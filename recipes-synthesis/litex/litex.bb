SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed9510879ab9728f8e0e3d8c4f3e5bc8"

SRC_URI = "gitsm://github.com/enjoy-digital/litex;protocol=https"
SRCREV = "de205d4a8457d0c226d69ae93f30a47d0ebcc1d1"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-pyserial"
RDEPENDS_${PN} += "migen"

# source contains some prebuilt binaries, leave them alone
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

BBCLASSEXTEND = "native nativesdk"
