SUMMARY = "Renode - virtual development framework for complex embedded systems"
HOMEPAGE = "https://renode.io"
SECTION = "devel/simulation"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbe11f5a6be424a6c05b56f43f76c914"
# TODO: licenses in subdirs/submodules

SRC_URI = " \
        gitsm://github.com/renode/renode;protocol=https \
        git://github.com/renode/renode-resources.git;protocol=https;name=resources;destsuffix=git/lib/resources \
        "
SRCREV = "4a88d1fc25e0f06f346d58e155773ec701284f2a"
SRCREV_resources = "fe75dff1ba5332bd35cd25fa83e5b8643456ae48"
SRCREV_FORMAT = "default"
PV = "0+git${SRCPV}"

S = "${WORKDIR}/git"
B = "${S}/output"

DEPENDS = "mono-native"
DEPENDS += "gtk-sharp"
RDEPENDS_${PN} += "mono"

export MONO_CFG_DIR="${STAGING_ETCDIR_NATIVE}"

do_configure[noexec] = "1"

do_compile() {
    # lib/resources already fetched, do not try to clone it again
    sed -i '/clone_if_/d' ${S}/tools/building/fetch_libraries.sh

    (cd ${S}; ./build.sh)

    # create wrapper script to execute with mono
    echo "#!/bin/sh" > renode
    echo "bindir=\$(dirname \$(realpath \$0))" >> renode
    echo "rootdir=\$bindir/${@os.path.relpath(d.getVar("datadir"), d.getVar("bindir"))}" >> renode
    if ${@"true" if bb.data.inherits_class("nativesdk", d) else "false"}; then
        echo "export MONO_CFG_DIR=\$bindir/${@os.path.relpath(d.getVar("sysconfdir"), d.getVar("bindir"))}" >> renode
    fi
    echo "exec mono \$rootdir/renode/bin/Renode.exe \"\$@\"" >> renode
}

do_install() {
    mkdir -p ${D}${bindir} ${D}${datadir}/renode ${D}${datadir}/renode/bin
    install -m 0755 ${B}/renode ${D}${bindir}/renode
    cp ${S}/.renode-root ${D}${datadir}/renode
    cp ${B}/bin/Release/* ${D}${datadir}/renode/bin
    cp -r ${S}/scripts ${D}${datadir}/renode
    cp -r ${S}/platforms ${D}${datadir}/renode
}

FILES_${PN} += "${datadir}/renode/*"

# renode ships libLLVM.so which is already stripped, libraries also not inside libdir
INSANE_SKIP_${PN} += "already-stripped libdir"

BBCLASSEXTEND += "native nativesdk"
