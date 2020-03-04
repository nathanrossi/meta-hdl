SUMMARY = "LiteX is a FPGA design/SoC builder that can be used to build cores, create SoCs and full FPGA designs."
HOMEPAGE = "https://github.com/enjoy-digital/litex"
SECTION = "devel/hdl"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=012b04b707bcdd5547cbec1345d56eac"

SRC_URI = "gitsm://github.com/enjoy-digital/litex;protocol=https"
SRCREV = "e124aed9a2ac20f1f649ba67a5087eea73751e9f"
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
