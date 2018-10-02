require symbiflow-arch-defs.inc

do_compile()  {
    # limit build to arch defs/etc. no checking/routing tests
    cmake_runcmake_build --target all_merged_arch_xmls
    cmake_runcmake_build --target all_rrgraph_xmls
    cmake_runcmake_build --target all_xml_lint
}

