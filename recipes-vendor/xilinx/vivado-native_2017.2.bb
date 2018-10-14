SUMMARY = "Xilinx Vivado vendor toolchain"
HOMEPAGE = "https://xilinx.com"

LICENSE = "Proprietary"
LICENSE_FLAGS = "xilinx xilinx-3rd-party xilinx-webpack"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Proprietary;md5=0557f9d92cf58f2ccdd50f62f8ac0b28"

RELEASE_VERSION = "2017.2"
RELEASE_ID = "0616_1"
PV = "${RELEASE_VERSION}"

PROVIDES += "virtual/xilinx-vivado-native"

INHIBIT_DEFAULT_DEPS = "1"

# install is only ever native, license prevents redistribution
inherit native
inherit fetch-restricted

SRC_URI = "https://www.xilinx.com/member/forms/download/xef.html?filename=Xilinx_Vivado_SDK_${RELEASE_VERSION}_${RELEASE_ID}.tar.gz;downloadfilename=Xilinx_Vivado_SDK_${RELEASE_VERSION}_${RELEASE_ID}.tar.gz;restricted=1"
SRC_URI[md5sum] = "958f190a089ad3f39d327d972c7dcf35"
SRC_URI[sha256sum] = "32b98ffb8d0de79be4e053b0616639c76045a29f3bf9f921e4949bf32527eb1a"

S = "${WORKDIR}/Xilinx_Vivado_SDK_${RELEASE_VERSION}_${RELEASE_ID}"

# don't attempt to split or strip binaries
INHIBIT_SYSROOT_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

# push the install into the sysroot
SYSROOT_DIRS_NATIVE += "${vendordir}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

VIVADO_PATH = "${D}${vendordir}/Vivado/${PV}"
VIVADO_HLS_PATH = "${D}${vendordir}//Vivado_HLS/${PV}"
vendordir = "${root_prefix}/opt/xilinx"

python do_install_generate_config () {
    configtxt = os.path.join(d.getVar("WORKDIR"), "config.txt")
    installdir = d.expand("${D}${vendordir}")
    with open(configtxt, "w") as f:
        f.write("Edition=Vivado HL Design Edition\n")
        f.write("Destination={0}\n".format(installdir))

        modules = {
            "Zynq UltraScale+ MPSoC" : "1",
            "Software Development Kit (SDK)" : "0",
            "Kintex UltraScale" : "1",
            "Engineering Sample Devices" : "0",
            "Kintex-7" : "1",
            "Virtex UltraScale+" : "1",
            "Zynq-7000" : "1",
            "Kintex UltraScale+ ES" : "0",
            "Kintex UltraScale+" : "1",
            "Spartan-7" : "1",
            "Zynq UltraScale+ RFSoC ES" : "0",
            "Virtex-7" : "1",
            "Virtex UltraScale" : "1",
            "Virtex UltraScale+ ES" : "0",
            "Zynq UltraScale+ MPSoC ES" : "0",
            "Artix-7" : "1",
            }
        f.write("Modules={0}\n".format(",".join("{0}:{1}".format(k, v) for k, v in modules.items())))

        options = {
            "Acquire or Manage a License Key" : "0",
            "Enable WebTalk for SDK to send usage statistics to Xilinx" : "0",
            "Enable WebTalk for Vivado to send usage statistics to Xilinx (Always enabled for WebPACK license)" : "1",
            }
        f.write("InstallOptions={0}\n".format(",".join("{0}:{1}".format(k, v) for k, v in options.items())))

        # shortcut disable
        f.write("CreateProgramGroupShortcuts=0\n")
        f.write("ProgramGroupFolder=Xilinx Design Tools\n")
        f.write("CreateShortcutsForAllUsers=0\n")
        f.write("CreateDesktopShortcuts=0\n")
        f.write("CreateFileAssociation=0\n")
}
addtask do_install_generate_config before do_install

# TODO: ${HOME}/.Xilinx/xinstall/xinstall_....log
do_install() {
    # the installer will try to push data into the home directory, map that into the workdir
    export HOME="${WORKDIR}/build-home"
    mkdir -p $HOME

    ${S}/xsetup \
        -b Install \
        -a XilinxEULA,3rdPartyEULA,WebTalkTerms \
        -c ${WORKDIR}/config.txt
}

require vivado-wrapper.inc

python do_install_wrappers () {
    for i in d.getVar("EXTERNAL_TOOLS").split():
        install_xilinx_shell_wrapper(d, i, relative = d.getVar("vendordir"))
}
addtask do_install_wrappers after do_install before do_populate_sysroot

