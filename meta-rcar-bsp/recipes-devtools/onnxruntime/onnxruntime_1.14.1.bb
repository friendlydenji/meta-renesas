SUMMARY = "ONNX Runtime"
DESCRIPTION = "ONNX Runtime prebuilt library for Linux AArch64"
HOMEPAGE = "https://onnxruntime.ai"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f7e3b1308cb5c00b372a6e78835732d"

PV = "1.14.1"

SRC_URI = "https://github.com/microsoft/onnxruntime/releases/download/v${PV}/onnxruntime-linux-aarch64-${PV}.tgz"

SRC_URI[sha256sum] = "20965e05d2da6a0e6acedda8aca1a8f75c3f4cb1d7932403a9977b753b1b2120"

S = "${WORKDIR}/onnxruntime-linux-aarch64-${PV}"

COMPATIBLE_HOST = "aarch64.*-linux"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}

    cp -R --no-preserve=ownership ${S}/lib/. ${D}${libdir}/
    cp -R --no-preserve=ownership ${S}/include/. ${D}${includedir}/
}

# Prebuilt binary
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Để toàn bộ .so nằm trong runtime package.
# Header nằm trong -dev.
FILES:${PN} += "${libdir}/*"
FILES:${PN}-dev = "${includedir}"

INSANE_SKIP:${PN} += "already-stripped ldflags dev-so"