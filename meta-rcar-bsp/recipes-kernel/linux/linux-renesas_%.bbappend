FILESEXTRAPATHS:prepend := "${THISDIR}/linux-renesas:"

SRC_URI += " \
    file://0004-makefile.patch \
    file://r8a779g0-whitehawk-native-modified.dts \
    file://r8a779h0-gray-hawk-uio-modified.dts \
"

do_configure:prepend() {
    cp ${WORKDIR}/r8a779g0-whitehawk-native-modified.dts \
       ${S}/arch/arm64/boot/dts/renesas/

    cp ${WORKDIR}/r8a779h0-gray-hawk-uio-modified.dts \
       ${S}/arch/arm64/boot/dts/renesas/
}

KERNEL_DEVICETREE:append = " \
    renesas/r8a779g0-whitehawk-native-modified.dtb \
    renesas/r8a779h0-gray-hawk-uio-modified.dtb \
"