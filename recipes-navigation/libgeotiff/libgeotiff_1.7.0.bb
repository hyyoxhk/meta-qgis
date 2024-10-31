SUMMARY = "A library for reading, and writing GeoTIFF information tags"
HOMEPAGE = "https://trac.osgeo.org/geotiff/"
SECTION = "libs"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e99dbe0cce55f0471235200ae75c772e"

SRC_URI = "http://download.osgeo.org/geotiff/${BPN}/${BP}.tar.gz"

SRC_URI[md5sum] = "e57b69098d70a008ddfc65b675cbfcf4"
SRC_URI[sha256sum] = "fc304d8839ca5947cfbeb63adb9d1aa47acef38fc6d6689e622926e672a99a7e"

DEPENDS = "proj tiff sqlite3"

inherit autotools pkgconfig lib_package

BBCLASSEXTEND = "native"
