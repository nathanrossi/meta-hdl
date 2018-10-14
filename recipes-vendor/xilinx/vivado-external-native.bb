SUMMARY = "Xilinx Vivado vendor toolchain (external install)"
HOMEPAGE = "https://xilinx.com"

LICENSE = "Proprietary"
LICENSE_FLAGS = "xilinx xilinx-3rd-party xilinx-webpack"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

PV = "${RELEASE_VERSION}"

PROVIDES += "virtual/xilinx-vivado-native"

INHIBIT_DEFAULT_DEPS = "1"

inherit native

XILINX_INSTALL_PATH ??= ""

# push the install into the sysroot
SYSROOT_DIRS_NATIVE += "${vendordir}"

vendordir = "${root_prefix}/opt/xilinx"

python __anonymous () {
    import re

    installpath = d.getVar("XILINX_INSTALL_PATH")
    if not(installpath):
        raise bb.parse.SkipRecipe("XILINX_INSTALL_PATH unset.")
    if not(os.path.exists(installpath)):
        raise bb.parse.SkipRecipe("XILINX_INSTALL_PATH missing.")

    # get the installs version
    vivadopath = os.path.join(installpath, "Vivado")
    vivadoversion = next((i for i in os.listdir(vivadopath) if re.search("\d+\.\d+", i)), None)
    if not(vivadoversion):
        raise bb.parse.SkipRecipe("XILINX_INSTALL_PATH has no installed versions.")

    # populate info for wrapping
    d.setVar("RELEASE_VERSION", vivadoversion)
    d.setVar("VIVADO_PATH", os.path.join(installpath, "Vivado", vivadoversion))
    d.setVar("VIVADO_HLS_PATH", os.path.join(installpath, "Vivado_HLS", vivadoversion))
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"

require vivado-wrapper.inc

python do_install () {
    # create a dummy tree within the sysroot for the external tools
    wrapperbase = os.path.join(d.expand("${D}${vendordir}"), "Vivado", d.getVar("RELEASE_VERSION"))
    for i in d.getVar("EXTERNAL_TOOLS").split():
        install_xilinx_shell_wrapper(d, i, install = os.path.join(wrapperbase, "bin"))

    # create dummy settings*.sh files
    with open(os.path.join(wrapperbase, "settings.sh"), "w") as f:
        f.write("# empty")
    with open(os.path.join(wrapperbase, "settings64.sh"), "w") as f:
        f.write("# empty")

    for i in d.getVar("EXTERNAL_TOOLS").split():
        install_xilinx_shell_wrapper(d, i)
}

