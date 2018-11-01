require litex-target.inc

DEPENDS += "python3-pyserial-native"
DEPENDS += "migen-native"
DEPENDS += "litex-native"
DEPENDS += "litex-buildenv-native"

# device modules
DEPENDS += "litedram-native"
DEPENDS += "liteeth-native"

do_configure[noexec] = "1"

inherit python3native

do_compile() {
    export PYTHONPATH="${S}:${PYTHONPATH}"

    python3 -m make \
        --output-dir ${B} \
        --cpu-type ${TARGET_ARCH} \
        ${@"--cpu-variant ${LITEX_CPU_VARIANT}" if d.getVar("LITEX_CPU_VARIANT") else ""} \
        ${@"--target ${LITEX_TARGET}" if d.getVar("LITEX_TARGET") else ""} \
        ${@"--platform ${LITEX_PLATFORM}" if d.getVar("LITEX_PLATFORM") else ""} \
        --no-compile-gateware \
        --no-compile-software \
        --no-compile-firmware

    # generate a variables.mak with only some options
    sed ${B}/software/include/generated/variables.mak \
        -e '/COPY_TO_MAIN_RAM\|EXECUTE_IN_PLACE/!d' \
        > ${B}/variables-stripped.mak
}

do_install () {
    install -d ${D}${includedir}/litex

    LITEX=${STAGING_LIBDIR_NATIVE}/python3.5/site-packages/litex
    LITEX_INCLUDE=$LITEX/soc/software/include/base
    # Copy in some litex platform specific files that MicroPython may need
    for f in system.h csr-defs.h spr-defs.h; do
        install -m 0644 $LITEX_INCLUDE/$f ${D}${includedir}/litex/$f
    done

    # reused by micropython
    install -d ${D}${includedir}/litex/libbase
    install -m 0644 $LITEX/soc/software/libbase/crt0-${TARGET_ARCH}.S ${D}${includedir}/litex/libbase/

    for i in ${B}/software/include/generated/*.h ${B}/software/include/generated/*.ld; do
        install -m 0644 -D $i ${D}${includedir}/litex/generated/$(basename $i)
    done
    install -m 0644 -D ${B}/variables-stripped.mak ${D}${includedir}/litex/generated/variables.mak
}

