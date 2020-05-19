HOMEPAGE = "https://github.com/litex-hub/${REPONAME}"
SECTION = "devel/hdl"

REPONAME = "${@d.getVar('BPN').replace('litex-', '')}"

SRC_URI = "git://github.com/litex-hub/${REPONAME};protocol=https"

S = "${WORKDIR}/git"

inherit setuptools3

BBCLASSEXTEND = "native nativesdk"
