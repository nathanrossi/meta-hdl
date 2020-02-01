SUMMARY = "AdaCore pre-built GNAT"
HOMEPAGE = "https://www.adacore.com/download"
SECTION = "devel"
# using licnese of gcc
LICENSE = "GPL-3.0-with-GCC-exception & GPLv3 & GPLv2"
LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/GPL-3.0-with-GCC-exception;md5=aef5f35c9272f508be848cd99e0151df \
    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
    file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
    "

SRC_URI = "https://community.download.adacore.com/v1/${FILESHA1}?filename=${FILENAME};downloadfilename=${FILENAME}"

FILENAME = "gnat-community-2019-20190517-x86_64-linux-bin"
FILESHA1 = "0cd3e2a668332613b522d9612ffa27ef3eb0815b"
SRC_URI[md5sum] = "c21c279318d90dad8fbe1f76fcd68da9"
SRC_URI[sha256sum] = "f67dc70eff36cc5c37f8bf8ed3125139592ed4bc36c6fe9d6caedde261de1d72"

inherit native nopackages

do_configure[noexec] = "1"
do_compile[noexec] = "1"

gnatdir = "${STAGING_DIR_NATIVE}/opt/gnat"

do_install() {
    chmod +x ${WORKDIR}/${FILENAME}
    ${WORKDIR}/${FILENAME} -v \
        PREFIX=${D}${gnatdir} \
        --platform minimal

    # remove some unused content
    (
        cd ${D}${gnatdir}/bin;
        rm -f x86_64-pc-linux-gnu-*;
        rm -f c++* gcc* cpp g++;
        rm -f ar addr2line nm obj* size readelf ranlib strings size strip;
        rm -f gdb*;
        rm -f gps* gpr*;
        rm -f wsdl* webxref xml2gnat aws* ada2wsdl;
    )
    (
        cd ${D}${gnatdir}/lib;
        rm -rf aws* gir* gpr* gps* gtk-3.0* python2.7* libxmlada*;
    )
    (
        cd ${D}${gnatdir}/libexec;
        rm -rf gprbuild spark;
    )
    (
        cd ${D}${gnatdir}/include;
        rm -rf aws* gpr pycairo pygobject-3.0 xmlada python2.7;
    )
    (
        cd ${D}${gnatdir}/share;
        rm -rf doc examples glib-2.0 gpr* gps icons info man spark themes why3;
    )
    rm -rf ${D}${gnatdir}/docs ${D}${gnatdir}/etc
    rm -f ${D}${gnatdir}/maintenancetool*
}

# stage the entire install
SYSROOT_DIRS_NATIVE += "${gnatdir}"
# disable stripping on the installed files
INHIBIT_SYSROOT_STRIP = "1"

COMPATIABLE_HOST = "x86-64-linux.*"
