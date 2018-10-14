
EXTERNAL_TOOLS ??= "vivado"

def install_xilinx_shell_wrapper(d, tool, relative = None, install = None):
    bindirs = [
        os.path.join(d.getVar("VIVADO_PATH"), "bin"),
        os.path.join(d.getVar("VIVADO_HLS_PATH"), "bin"),
        ]
    installpath = d.getVar("VIVADO_PATH")

    toolpath = None
    for i in bindirs:
        ipath = os.path.join(i, tool)
        if os.path.exists(ipath) and os.access(ipath, os.X_OK):
            toolpath = ipath
            break

    if toolpath is None:
        return

    toolbin = os.path.join(install or d.expand("${D}${bindir}"), tool)
    if not os.path.exists(os.path.dirname(toolbin)):
        os.makedirs(os.path.dirname(toolbin))

    if relative is not None:
        # translate paths to be relative to the script, assuming it is installed in the same sysroot
        rpath = os.path.relpath(relative, d.getVar("bindir"))
        iroot = d.getVar("D") + relative
        toolpath = "$base/" + os.path.join(rpath, os.path.relpath(toolpath, iroot))
        bindirs = [("$base/" + os.path.join(rpath, os.path.relpath(d, iroot))) for d in bindirs]
        installpath = "$base/" + os.path.join(rpath, os.path.relpath(installpath, iroot))

    with open(toolbin, "w") as f:
        f.write("\n".join([
            "#!/bin/sh",
            "base=\"$(cd $(dirname $0) && pwd)\"",
            "export PATH={0}:$PATH".format(":".join(bindirs)),
            "export XILINX_VIVADO={0}".format(installpath),
            "exec {0} $*".format(toolpath),
            ]) + "\n")
    # make executable
    os.chmod(toolbin, 0o755)

