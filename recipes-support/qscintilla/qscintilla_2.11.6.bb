SUMMARY = "QScintilla is a port to Qt of Neil Hodgson's Scintilla C++ editor control"
HOMEPAGE = "https://riverbankcomputing.com/software/qscintilla/intro/"
SECTION = "libs"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://www.riverbankcomputing.com/static/Downloads/QScintilla/${PV}/QScintilla-${PV}.tar.gz"
SRC_URI[md5sum] = "343cd0c2c8b425518df2e51eb994fbc6"
SRC_URI[sha256sum] = "e7346057db47d2fb384467fafccfcb13aa0741373c5d593bc72b55b2f0dd20a7"

S = "${WORKDIR}/QScintilla-${PV}/Qt4Qt5"

DEPENDS = "qtbase qttools-native"

inherit qmake5

FILES:${PN} += "${prefix}/share/* "
FILES:${PN}-dev += " ${libdir}/mkspecs/* "
