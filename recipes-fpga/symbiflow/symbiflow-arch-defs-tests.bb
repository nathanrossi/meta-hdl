require symbiflow-arch-defs.inc

do_compile()  {
    # run all the targets including tests
    cmake_runcmake_build --target all_merged_arch_xmls
    cmake_runcmake_build --target all_rrgraph_xmls
    cmake_runcmake_build --target all_route_tests
    cmake_runcmake_build --target all_xml_lint
    cmake_runcmake_build --target all_check_tests
    cmake_runcmake_build --target all
}

# don't install any output from testing
do_install[noexec] = "1"

