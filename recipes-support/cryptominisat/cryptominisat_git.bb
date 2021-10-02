DESCRIPTION = "CryptoMiniSat is an advanced incremental SAT solver."
HOMEPAGE = " https://www.msoos.org"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=cc245d1844e36accfee431a45bb42d73"
SRC_URI = "git://github.com/msoos/cryptominisat;protocol=https"
SRCREV = "641f91597d1292f691d8cc8fd191bed73d004ebf"

S = "${WORKDIR}/git"

PV = "5.8.0+git${SRCPV}"

inherit cmake python3native

EXTRA_OECMAKE = "-DENABLE_PYTHON_INTERFACE=OFF -DNOM4RI=ON -DONLY_SIMPLE=ON"

DEPENDS = " \
        boost \
        "

# Work around the following cross-compiling error:
# | -- Performing Test HAVE__FPU_SETCW
# | CMake Error: TRY_RUN() invoked in cross-compiling mode, please set the following cache variables appropriately:
# |    HAVE__FPU_SETCW_EXITCODE (advanced)
# |    HAVE__FPU_SETCW_EXITCODE__TRYRUN_OUTPUT (advanced)
cmake_do_generate_toolchain_file:append() {
	cat >> ${WORKDIR}/toolchain.cmake <<EOF
set(HAVE__FPU_SETCW FALSE CACHE INTERNAL "" FORCE)
EOF
}

BBCLASSEXTEND = "native nativesdk"
