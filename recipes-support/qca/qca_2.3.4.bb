# SPDX-FileCopyrightText: 2019-2020 Volker Krause <vkrause@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "QCA"
HOMEPAGE = "https://invent.kde.org/libraries/qca/"
SECTION = "libs"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI = "https://download.kde.org/stable/${BPN}/${PV}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "5a5398e9dc7fef37d1e7f070260f4886"
SRC_URI[sha256sum] = "6b695881a7e3fd95f73aaee6eaeab96f6ad17e515e9c2b3d4b3272d7862ff5c4"

DEPENDS = "botan cyrus-sasl libgcrypt nss qtbase"

inherit cmake_qt5 pkgconfig lib_package

EXTRA_OECMAKE += " \
    -DQCA_FEATURE_INSTALL_DIR=${libdir}${QT_DIR_NAME}/mkspecs/features \
    -DLIB_SUFFIX=${@d.getVar('baselib').replace('lib', '')} \
"

FILES:${PN} += " \
    ${libdir}/qca-qt5/crypto/libqca-*.so \
"

FILES:${PN}-dev += " \
    ${libdir}/mkspecs/features/*  \
    ${libdir}/cmake/Qca-qt5/*.cmake \
"
