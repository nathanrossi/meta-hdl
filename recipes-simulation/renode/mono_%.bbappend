do_install_append() {
    # map 'dl' -> libdl.so.2
    sed -i '/<dllmap dll="dl"/d' ${D}${sysconfdir}/mono/config
    sed -i '/^<\/configuration/i \\t<dllmap dll="dl" target="libdl.so.2" os="!windows"\/>' ${D}${sysconfdir}/mono/config
}
