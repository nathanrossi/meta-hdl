# meta-hdl

This layer contains recipes for Hardware Description Languages (HDL) tools,
which include tools for simulation, synthesis, place and route, FPGA bitstream
packing/analysis as well as example projects.

Only projects released under open source licenses are contained in this layer.

# Examples

## [PicoSoC](https://github.com/YosysHQ/picorv32/tree/master/picosoc)

To build this target, set `MACHINE = "picosoc"` in your local.conf and build the
`picosoc-hx8k` recipe with `bitbake picosoc-hx8k`. The built bitstream and
firmware are populated into the `deploy/images/picosoc` directory on completion.

## [Linux on LiteX-VexRiscv](https://github.com/litex-hub/linux-on-litex-vexriscv) (Versa ECP5)

To build this target, set `MACHINE = "versa-ecp5"` in your local.conf and build
`core-image-minimal`. The built bitstream, firmware, linux kernel, root
filesystem and device tree are populated into the `deploy/image/versa-ecp5`
directory on completion. Follow the documentation provided by the project itself
to boot the images.

A `load` task exists to execute the `--load` command of `make.py`. To run the
task execute `bitbake linux-on-litex-vexriscv -c load`. In order to use the
LiteX terminal, build `bitbake litex-native -c addto_recipe_sysroot` and use
`oe-run-native litex-native lxterm ...` to run `lxterm`.

Additionally the Verilator simulation can be run with `bitbake
linux-on-litex-vexricv -c sim`. This will start the simulation in a
terminal (similar to devshell).

# Dependencies

This layer depends on:

	URI: git://git.openembedded.org/bitbake

	URI: git://git.openembedded.org/openembedded-core
	layers: meta

	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe, meta-python

