SUMMARY = "QGIS is a free, open source, cross platform (lin/win/mac) geographical information system (GIS)"
HOMEPAGE = "https://qgis.org/"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=02c41ffcd1d6df2b4ae6fafffe7c942e"

SRC_URI = " \
    https://download.qgis.org/downloads/${BP}.tar.bz2 \
    file://0001-Add-PYQT_SIP_DIR_SYSROOT-to-find-additional-cmake-fi.patch \
"
SRC_URI[md5sum] = "07b8678ed459a8dba1769b20484a173f"
SRC_URI[sha256sum] = "882aa88dfbf2aef389329f789b5929f0c2c89b8b33ff4c481308f95ff0213972"

inherit cmake_qt5 pkgconfig mime-xdg python3native

OECMAKE_GENERATOR = "Unix Makefiles"

#DEPENDS += "flex-native bison-native protobuf-native pdal-native qttools-native sip3-native python3-pyqt5-native"

DEPENDS += "flex-native bison-native protobuf-native qttools-native sip3-native python3-pyqt5-native"

#DEPENDS += "proj geos gdal expat spatialindex qwt-qt5 libzip sqlite3 protobuf zlib exiv2 libspatialite zstd pdal" 

DEPENDS += "proj geos gdal expat spatialindex qwt-qt5 libzip sqlite3 protobuf zlib exiv2 libspatialite zstd"

DEPENDS += "qscintilla python3-pyqt5-qsci qtkeychain qca gsl sip3 python3-pyqt5 libgeotiff qtserialport qtbase qtsvg qtwebkit qtlocation qttools"

EXTRA_OECMAKE += "-DSIP_VERSION=41319"
EXTRA_OECMAKE += "-DSIP_VERSION_STR=4.19.25"
EXTRA_OECMAKE += "-DSIP_BINARY_PATH=${STAGING_BINDIR_NATIVE}/sip"
EXTRA_OECMAKE += "-DSIP_INCLUDE_DIR=${STAGING_INCDIR}"
EXTRA_OECMAKE += "-DPYQT_SIP_DIR_SYSROOT=${STAGING_DIR_HOST}"
#EXTRA_OECMAKE += "-DNATIVE_CRSSYNC_BIN=/usr/local/bin/crssync"

do_configure:prepend () {
    if [ -f ${S}/cmake/FindGDAL.cmake ]; then
        rm ${S}/cmake/FindGDAL.cmake
    fi

    sed -i "s,exec .*python3,exec ${PYTHON}," ${STAGING_BINDIR_NATIVE}/pylupdate5
    sed -i "s,exec .*python3,exec ${PYTHON}," ${STAGING_BINDIR_NATIVE}/pyrcc5
    sed -i "s,exec .*python3,exec ${PYTHON}," ${STAGING_BINDIR_NATIVE}/pyuic5
}

do_install:append () {
    mv ${D}${prefix}/man ${D}${datadir}
}

FILES:${PN} += " \
    ${datadir}/icons/* \
    ${datadir}/metainfo/* \
"
