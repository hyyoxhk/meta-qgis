SUMMARY = "C++ implementation of R*-tree, an MVR-tree and a TPR-tree with C API"
HOMEPAGE = "https://libspatialindex.org/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbfd7d9d8e3ab1b72cd49bc41b8867bc"

SRC_URI = "https://github.com/libspatialindex/libspatialindex/releases/download/${PV}/${BPN}-src-${PV}.tar.gz"
SRC_URI[md5sum] = "b8b6fd4ee15fee5fcbb5d90a31ec13d8"
SRC_URI[sha256sum] = "f1d5a369681fa6ac3301a54db412ccf3180fc17163ebc3252f32c752f77345de"

S = "${WORKDIR}/${BPN}-src-${PV}"

inherit cmake

PACKAGES =+ "${PN}lib ${PN}-c1"

DESCRIPTION:${PN}lib = "General framework for developing spatial indices"
FILES:${PN}lib += "${libdir}/libspatialindex-${PV}.so"

DESCRIPTION:${PN}-c1 = "General framework for developing spatial indices - C library"
FILES:${PN}-c1 += "${libdir}/libspatialindex_c.so.*"

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN} += "${PN}lib ${PN}-c1"
