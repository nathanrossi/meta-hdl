inherit python3native

PATH_prepend = "${WORKDIR}/toolchain-wrappers:"

# litex does not provide a mechanism to specify the toolchain tuple
do_create_toolchain_wrappers[dirs] += "${WORKDIR}/toolchain-wrappers"
python do_create_toolchain_wrappers() {
    wrappedroot = d.expand("${WORKDIR}/toolchain-wrappers")
    mapped_target = d.expand("${TARGET_ARCH}-unknown-elf-")
    def wrap(prog, args):
        mapped = mapped_target + prog
        with open(os.path.join(wrappedroot, mapped), "w") as f:
            f.write("#!/bin/sh\n")
            f.write("{0} $*\n".format(d.expand(args)))
        os.chmod(os.path.join(wrappedroot, mapped), 0o755)

    # pass -nostd* when using baremetal
    if d.getVar("TCLIBC") == "baremetal":
        wrap("gcc", "${CC} -nostdlib -nostdinc")
        wrap("ld", "${LD} -nostdlib")
    else:
        wrap("gcc", "${CC}")
        wrap("ld", "${LD}")

    wrap("ar", "${AR}")
    wrap("objcopy", "${OBJCOPY}")
    wrap("objdump", "${OBJDUMP}")
}
addtask create_toolchain_wrappers before do_compile

