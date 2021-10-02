inherit python3native

PATH:prepend = "${WORKDIR}/toolchain-wrappers:"

# litex does not provide a mechanism to specify the toolchain tuple
do_create_toolchain_wrappers[dirs] += "${WORKDIR}/toolchain-wrappers"
do_create_toolchain_wrappers[vardeps] += "BUILD_CC BUILD_CFLAGS"
do_create_toolchain_wrappers[vardeps] += "BUILD_CXX BUILD_CXXFLAGS"
do_create_toolchain_wrappers[vardeps] += "BUILD_LD BUILD_LDFLAGS"
do_create_toolchain_wrappers[vardeps] += "CC CFLAGS"
do_create_toolchain_wrappers[vardeps] += "CXX CXXFLAGS"
do_create_toolchain_wrappers[vardeps] += "LD LDFLAGS"
do_create_toolchain_wrappers[vardeps] += "AR OBJCOPY OBJDUMP"
python do_create_toolchain_wrappers() {
    wrapped_root = d.expand("${WORKDIR}/toolchain-wrappers")
    search_path = ":".join(d for d in d.getVar("PATH").split(":") if d != wrapped_root)
    mapped_target = d.expand("${TARGET_ARCH}-unknown-elf-")
    def wrap(prog, args, target = True):
        mapped = (mapped_target + prog) if target else prog
        with open(os.path.join(wrapped_root, mapped), "w") as f:
            f.write("#!/bin/sh\n")
            expanded = d.expand(args).split()
            if not os.path.isabs(expanded[0]):
                expanded[0] = bb.utils.which(search_path, expanded[0])
            if not os.path.exists(expanded[0]):
                bb.fatal("Failed to setup toolchain wrapper for '{}'".format(prog))
            f.write("{0} $*\n".format(d.expand(" ".join(expanded))))
        os.chmod(os.path.join(wrapped_root, mapped), 0o755)

    # pass -nostd* when using baremetal
    if d.getVar("TCLIBC") == "newlib":
        wrap("gcc", "${CC}")
        wrap("g++", "${CXX}")
        wrap("ld", "${LD}")
    else:
        wrap("gcc", "${CC} -nostdlib -nostdinc")
        wrap("g++", "${CXX} -nostdlib -nostdinc")
        wrap("ld", "${LD} -nostdlib")

    wrap("ar", "${AR}")
    wrap("gcc-ar", "${AR}")
    wrap("objcopy", "${OBJCOPY}")
    wrap("objdump", "${OBJDUMP}")
    wrap("readelf", "${READELF}")

    # wrap native build compiler/etc
    wrap("gcc", "${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}", target = False)
    wrap("g++", "${BUILD_CXX} ${BUILD_CXXFLAGS} ${BUILD_LDFLAGS}", target = False)
    wrap("ld", "${BUILD_LD}", target = False)
}
addtask create_toolchain_wrappers before do_configure after do_prepare_recipe_sysroot

