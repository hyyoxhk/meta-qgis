# SPDX-FileCopyrightText: 2021-2022 Andreas Cord-Landwehr <cordlandwehr@kde.org>
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "QtKeychain"
HOMEPAGE = "https://github.com/frankosterfeld/qtkeychain"
SECTION = "libs"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=ebece3896618ecf0b60c4fade17f026f"

SRC_URI = "git://github.com/frankosterfeld/qtkeychain.git;protocol=https;branch=main"
SRCREV = "c236f9241e37f1754879cf8726d59351a2b2b24a"

S = "${WORKDIR}/git"

DEPENDS = "qtbase libsecret qttools qttools-native"

inherit cmake_qt5 pkgconfig

FILES:${PN} += " \
    ${datadir}/qt5keychain/translations \
"

FILES:${PN}-dev += " \
    ${prefix}/mkspecs/*  \
    ${prefix}/share/* \
"
