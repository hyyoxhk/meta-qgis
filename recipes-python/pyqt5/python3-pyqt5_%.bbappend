DEPENDS += "qtlocation"

PYQT_MODULES += "QtLocation QtPositioning QtPrintSupport QtSql"

do_install:append() {
    sed -i "s,exec .*python3,exec ${bindir}/python3," ${D}/${bindir}/*
}
