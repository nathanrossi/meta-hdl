SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=012b04b707bcdd5547cbec1345d56eac"

SRC_URI = "git://github.com/enjoy-digital/litex;protocol=https"
SRCREV = "51976008126d25ed70268ffbe112f24969ed9432"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-pyserial"
RDEPENDS_${PN} += "migen"

do_install_append() {
    # some scripts in litex exist but are unused
    installpath=${D}${PYTHON_SITEPACKAGES_DIR}/litex
    rm $installpath/soc/cores/cpu/blackparrot/setEnvironment.sh
}

BBCLASSEXTEND = "native nativesdk"
