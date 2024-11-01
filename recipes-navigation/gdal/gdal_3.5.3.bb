SUMMARY = "GDAL is a translator library for raster geospatial data formats"
HOMEPAGE = "http://www.gdal.org/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=f7649e9ba282c52e57d3d98521c64842"

SRC_URI = "http://download.osgeo.org/${BPN}/${PV}/gdal-${PV}.tar.gz"
SRC_URI[md5sum] = "d1defb6200dc419c4bfcaf1d8ebd0d4a"
SRC_URI[sha256sum] = "a9ea0300d17e35bab71df4f16e62bb2fb8081caf994ab3ee0502ce4cf0d4e593"

# By default $S source dir is set to gdal-${PV}, we need to set it to match unpacked archive
S = "${WORKDIR}/gdal-${PV}"

DEPENDS = "proj sqlite3 tiff json-c expat libpng jpeg zstd openssl libpcre2"

inherit cmake pkgconfig bash-completion lib_package

FILES:${PN} += "${libdir}/gdalplugins/* "

BBCLASSEXTEND = "native"
