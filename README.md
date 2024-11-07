# Summary

This README file contains information on the contents of the meta-qgis layer.

Please see the corresponding sections below for details.

# Dependencies

[POKY]

- URI: https://git.yoctoproject.org/poky

- branch: kirkstone

[META-QT5]

- URI: https://github.com/meta-qt5/meta-qt5.git

- branch: kirkstone

[META-OPENEMBEDDED]

- URI: https://github.com/openembedded/meta-openembedded.git

- branch: kirkstone

# Quick Start

```shell
$> git clone -b kirkstone https://git.yoctoproject.org/poky
$> cd poky
$> git clone -b kirkstone git://github.com/openembedded/meta-openembedded.git
$> git clone -b kirkstone git://github.com/hyyoxhk/meta-qgis.git
$>
$> source oe-init-build-env
...
$ bitbake-layers add-layer ../meta-openembedded/meta-oe
$ bitbake-layers add-layer ../meta-openembedded/...
$ bitbake-layers add-layer ../meta-qgis
$ bitbake qgis
```

# Note

This layer does not implement qgis-native, so qgis-3.22.14 needs to be compiled and installed to get the `crssync` tool on the host machine

```
# Note:
EXTRA_OECMAKE += "-DNATIVE_CRSSYNC_BIN=/usr/local/bin/crssync"
```

# Contributing

If you want to contribute changes, you can send Github pull requests at https://github.com/hyyoxhk/meta-qgis/pulls

# Maintainer

- He Yong <hyyoxhk@163.com>
