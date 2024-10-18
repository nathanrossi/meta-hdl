DESCRIPTION = "CryptoMiniSat is an advanced incremental SAT solver."
HOMEPAGE = " https://www.msoos.org"
LICENSE = "MIT"
SECTION = "devel/verilog"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f859c6bc8d7015709644c89d1ef36d35"
SRC_URI = "git://github.com/msoos/cryptominisat;protocol=https;branch=master"
SRCREV = "4c9a6b6b459b7c1381744115ccfe744f1121794b"

S = "${WORKDIR}/git"

PV = "5.11.21+git${SRCPV}"

inherit cmake python3native

EXTRA_OECMAKE = "-DNOM4RI=ON"

DEPENDS += "boost"

do_configure:prepend() {
    # prevent buildpaths from being included in the binary
    sed -i "s#@CMAKE_CXX_COMPILER@#${HOST_PREFIX}g++#g" ${S}/src/GitSHA1.cpp.in
    sed -i "s#@CMAKE_CXX_FLAGS@#${HOST_CC_ARCH}#g" ${S}/src/GitSHA1.cpp.in
    sed -i "s#@PYTHON_EXECUTABLE@#python3#g" ${S}/src/GitSHA1.cpp.in
}

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
