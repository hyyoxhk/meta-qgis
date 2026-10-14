# meta-qgis: QGIS Layer for Yocto Project

meta-qgis is an OpenEmbedded/Yocto Project layer for building **QGIS 3.22.14**
for embedded Linux systems with the **Kirkstone** release series. It provides
BitBake recipes for QGIS and GIS dependencies including GDAL, PROJ, GEOS, and
SpatiaLite, and uses Qt 5 through meta-qt5.

Use this layer to cross-compile QGIS and include it in a Yocto-built Linux image.
Target hardware, graphics configuration, and image integration are supplied by
your BSP and image layers.

## Compatibility and recipes

The supported release series declared in [conf/layer.conf](conf/layer.conf) is
`kirkstone`. Compatibility with other Yocto releases is not declared.

| Component | Recipe version |
| --- | --- |
| QGIS | 3.22.14 |
| GDAL | 3.5.3 |
| PROJ | 8.2.1 |
| GEOS | 3.10.6 |
| SpatiaLite | 5.0.1 |
| libgeotiff | 1.7.0 |
| SpatialIndex | 2.0.0 |
| Qwt (Qt 5) | 6.1.6 |
| QScintilla and its Python bindings | 2.11.6 |
| QtKeychain | 0.13.2 |
| QCA | 2.3.4 |
| SIP | 4.19.25 |

The layer also includes append files for PyQt5 and native Qt base builds.
The versions above describe the recipes in this repository, rather than the
latest upstream releases.

## Dependencies

Use the `kirkstone` branch of each repository:

| Repository | Layers used |
| --- | --- |
| [Poky](https://git.yoctoproject.org/poky) | OE-Core and the standard Poky build environment |
| [meta-openembedded](https://github.com/openembedded/meta-openembedded) | `meta-oe`, `meta-python`, `meta-networking` |
| [meta-qt5](https://github.com/meta-qt5/meta-qt5) | `meta-qt5` |

`meta-networking` supplies `cyrus-sasl`, a dependency of the QCA recipe, and
requires `meta-python` in addition to `meta-oe`.

Prepare a build host with the host packages required by Yocto Kirkstone and
select the BSP layer and `MACHINE` appropriate for your target.

### Host crssync prerequisite

This layer does not provide a `qgis-native` recipe. Before building target QGIS,
build and install QGIS 3.22.14 for the build host to obtain the native `crssync`
tool. The executable must run on the build host, not on the target device.

Once the build directory has been created, add the following to
`build/conf/local.conf`, adjusting the path to your host installation:

```bitbake
EXTRA_OECMAKE:append:pn-qgis = " -DNATIVE_CRSSYNC_BIN=/usr/local/bin/crssync"
```

The equivalent setting in the [QGIS recipe](recipes-navigation/qgis/qgis_3.22.14.bb)
is commented out; setting it in `local.conf` avoids editing the layer.
Check that the configured executable exists and that its shared library
dependencies are available on the host.

## Quick start: build QGIS with Yocto

Clone the layers and initialize the build directory:

```sh
git clone -b kirkstone https://git.yoctoproject.org/poky
cd poky
git clone -b kirkstone https://github.com/openembedded/meta-openembedded.git
git clone -b kirkstone https://github.com/meta-qt5/meta-qt5.git
git clone -b kirkstone https://github.com/hyyoxhk/meta-qgis.git
source oe-init-build-env

bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-qt5
bitbake-layers add-layer ../meta-qgis
```

Add your target BSP layer if needed, configure `MACHINE` in `conf/local.conf`,
and set the host `crssync` path as described above. Then check that the QGIS
recipe is visible and build it:

```sh
bitbake-layers show-recipes qgis
bitbake qgis
```

These commands build the QGIS packages. To install QGIS into a target image,
add this line to `conf/local.conf` for a development build:

```bitbake
IMAGE_INSTALL:append = " qgis"
```

Then build your chosen image recipe with `bitbake <your-image-recipe>`.
For a maintained product image, put the package selection in your image recipe
or image append file.

QGIS needs a suitable Qt graphical environment to run. Configure the display
backend and graphics support for your BSP and image. This README does not
currently document a verified board or runtime configuration.

## Troubleshooting

- **Missing recipe or provider:** check `bitbake-layers show-layers` and ensure
  the dependency layers above are enabled on compatible branches.
- **crssync cannot be found or executed:** check the `NATIVE_CRSSYNC_BIN` path,
  executable permissions, and host shared libraries. A target-architecture
  executable cannot replace the host tool.
- **QGIS builds but cannot open a window:** check the target display service,
  Qt platform plugins, and graphics configuration in your image.

When reporting a problem, include your layer revisions, `MACHINE`, image recipe,
and the relevant BitBake task log or target runtime error.

## Project links and contributing

- [Source repository](https://github.com/hyyoxhk/meta-qgis)
- [Report an issue](https://github.com/hyyoxhk/meta-qgis/issues)
- [Submit a pull request](https://github.com/hyyoxhk/meta-qgis/pulls)
- [QGIS project](https://qgis.org/)
- [Yocto Project](https://www.yoctoproject.org/)
- [OpenEmbedded](https://www.openembedded.org/)

Contributions to recipes, patches, build instructions, and tested target
configurations are welcome. Include the release branch and any build or runtime
validation performed with your change.

## License

Layer metadata is licensed under the [MIT License](COPYING.MIT).
Software built by the recipes has its own licenses; refer to each recipe's
`LICENSE` and `LIC_FILES_CHKSUM` fields for details.

## Maintainer

He Yong — <hyyoxhk@163.com>
