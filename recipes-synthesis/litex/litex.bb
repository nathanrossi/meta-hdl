SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed9510879ab9728f8e0e3d8c4f3e5bc8"

SRC_URI = "gitsm://github.com/enjoy-digital/litex;protocol=https"
SRCREV = "7b92a17c6e1cbbff7ff203075ad65038284c82b1"
PV = "0.2-dev+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-pyserial"
RDEPENDS_${PN} += "migen"

# source contains some prebuilt binaries, leave them alone
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"

do_install_append() {
    # some submodules have python scripts that depend on python2 or oddly pathed /usr/bin/sh
    installpath=${D}${PYTHON_SITEPACKAGES_DIR}/litex
    rm \
        $installpath/soc/cores/cpu/rocket/verilog/update.sh \
        $installpath/soc/cores/cpu/vexriscv/verilog/ext/VexRiscv/src/test/python/gcloud/gcloud.py \
        $installpath/soc/cores/cpu/vexriscv/verilog/ext/VexRiscv/src/test/python/gcloud/remotePull.py \
        $installpath/soc/cores/cpu/vexriscv/verilog/ext/VexRiscv/src/test/python/gcloud/remoteTest.py \
        $installpath/soc/cores/cpu/vexriscv/verilog/ext/VexRiscv/src/test/python/gcloud/try.py \
        $installpath/soc/software/compiler_rt/lib/asan/scripts/asan_symbolize.py \
        $installpath/soc/software/compiler_rt/lib/dfsan/scripts/build-libc-list.py \
        $installpath/soc/software/compiler_rt/lib/sanitizer_common/scripts/cpplint.py \
        $installpath/soc/software/compiler_rt/lib/sanitizer_common/scripts/gen_dynamic_list.py \
        $installpath/soc/software/compiler_rt/lib/sanitizer_common/scripts/litlint.py \
        $installpath/soc/software/compiler_rt/lib/sanitizer_common/scripts/litlint_test.py \
        $installpath/soc/software/compiler_rt/lib/sanitizer_common/scripts/sancov.py \
        $installpath/soc/software/compiler_rt/make/filter-inputs \
        $installpath/soc/software/compiler_rt/test/asan/android_commands/android_compile.py \
        $installpath/soc/software/compiler_rt/test/asan/android_commands/android_run.py
}

BBCLASSEXTEND = "native nativesdk"
