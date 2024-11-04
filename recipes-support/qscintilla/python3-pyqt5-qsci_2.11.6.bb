SUMMARY = "Python3 bindings for QScintilla with Qt5"
HOMEPAGE = "https://riverbankcomputing.com/software/qscintilla/intro/"
SECTION = "devel/python"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "https://www.riverbankcomputing.com/static/Downloads/QScintilla/${PV}/QScintilla-${PV}.tar.gz"
SRC_URI[md5sum] = "343cd0c2c8b425518df2e51eb994fbc6"
SRC_URI[sha256sum] = "e7346057db47d2fb384467fafccfcb13aa0741373c5d593bc72b55b2f0dd20a7"

S = "${WORKDIR}/QScintilla-${PV}/Python"

DEPENDS = "sip3 sip3-native python3 qscintilla python3-pyqt5"

inherit qmake5 python3native

do_configure:prepend() {
    cd ${S}
    echo "py_platform = linux" > qsci.cfg
    echo "py_inc_dir = ${STAGING_INCDIR}/python%(py_major).%(py_minor)${PYTHON_ABI}" >> qsci.cfg
    echo "py_sip_dir = ${STAGING_EXECPREFIXDIR}/share/sip" >> qsci.cfg
    echo "sip_bin_dir = ${D}/${bindir}" >> qsci.cfg
    echo "sip_inc_dir = ${D}/${includedir}" >> qsci.cfg
    echo "sip_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> qsci.cfg
    echo "sip_sip_dir = ${D}/${datadir}/sip" >> qsci.cfg
    echo "sip_module = PyQt5.sip" >> qsci.cfg
    echo "[PyQt 5.0.0]" >> qsci.cfg
    echo yes | ${PYTHON} configure.py --verbose --pyqt PyQt5 --configuration qsci.cfg --destdir /${D}${libdir}/${PYTHON_DIR}/site-packages/
}

do_configure:append() {
    #Fix installation paths
    sed -i -e s:'$(INSTALL_ROOT)'${STAGING_EXECPREFIXDIR}:'$(INSTALL_ROOT)'${D}${exec_prefix}:g ${S}/Makefile
    sed -i -e s:'$(INSTALL_ROOT)'${STAGING_EXECPREFIXDIR}:'$(INSTALL_ROOT)'${D}${exec_prefix}:g ${S}/Qsci/Makefile
    #Skip installed.txt creation
    sed -i -e s:" install_distinfo ":" ": ${S}/Makefile
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake MAKEFLAGS='-j 1' install
}

FILES:${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/"
