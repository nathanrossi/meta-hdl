DESCRIPTION = "nextpnr, a portable FPGA place and route tool"
HOMEPAGE = "https://github.com/daveshah1/nextpnr-xilinx"
LICENSE = "ISC"
SECTION = "devel/fpga"

LIC_FILES_CHKSUM = "file://COPYING;;md5=d6e454a24247f9ba6d2c656f97de17e9"

SRC_URI = "gitsm://github.com/gatecat/nextpnr-xilinx;protocol=https;branch=xilinx-upstream"
SRCREV = "634f1b5e86e233428c97f3153031415af4284c33"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"

do_configure:prepend() {
    # HACK: This is based on an older nextpnr tree missing the updated python
    # searching logic, hack it to handle newer python versions
    sed -i 's/PyVer 3.*)/PyVer 3 36 37 38 39 310 311)/g' ${S}/CMakeLists.txt

    # HACK: fix <set> include issue
    sed -i '/^#include <vector>/a #include <set>' ${S}/common/nextpnr.h
}
