SUMMARY = "An environment for building LiteX based FPGA designs. Makes it easy to get everything you need!"
HOMEPAGE = "https://github.com/timvideos/litex-buildenv/"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a3ea87f8c14f7802fd5359072308e3c7"

#SRC_URI = "git://github.com/timvideos/litex-buildenv;protocol=https"
#SRCREV = "c920e7730c2b0c9d02f0393b5cb33c4ba6ca1f8c"

SRC_URI = "git://github.com/nathanrossi/litex-buildenv;protocol=https;branch=nrossi/fix-version-data"
SRCREV = "${AUTOREV}"

PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

LITEX_CPU_VARIANT ??= ""

export PLATFORM = "${LITEX_PLATFORM}"
export TARGET = "${LITEX_TARGET}"
export CPU = "${TARGET_ARCH}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "litex-platform"

