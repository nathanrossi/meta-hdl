SUMMARY = "An environment for building LiteX based FPGA designs. Makes it easy to get everything you need!"
HOMEPAGE = "https://github.com/timvideos/litex-buildenv/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3ea87f8c14f7802fd5359072308e3c7"

SRC_URI = "git://github.com/timvideos/litex-buildenv;protocol=https"
SRCREV = "c920e7730c2b0c9d02f0393b5cb33c4ba6ca1f8c"
S = "${WORKDIR}/git"

inherit allarch
PACKAGE_ARCH = "all"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

LITEX_MODULES ?= "${prefix}/src/litex"

do_install() {
    install -d ${D}${LITEX_MODULES}
    cp -dR --preserve=mode ${S}/make.py ${D}${LITEX_MODULES}/make.py
    for i in gateware platforms targets; do
        cp -dR --preserve=mode ${S}/$i ${D}${LITEX_MODULES}
    done
}

FILES_${PN} += "${LITEX_MODULES}/*"
SYSROOT_DIRS += "${LITEX_MODULES}"
SYSROOT_DIRS_NATIVE += "${LITEX_MODULES}"

BBCLASSEXTEND = "native nativesdk"
