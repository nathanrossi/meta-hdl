# meta-hdl

This layer contains recipes for Hardware Description Languages (HDL) tools,
which include tools for simulation, synthesis, place and route, FPGA bitstream
packing/analysis as well as example projects.

Only projects released under open source licenses are contained in this layer.

# Tools

The following tools are available in this layer.

* [Arachne-PNR](https://github.com/cseed/arachne-pnr)
* [Icarus Verilog](http://iverilog.icarus.com/)
* [IceStorm](http://www.clifford.at/icestorm/)
* [nextpnr](https://github.com/YosysHQ/nextpnr)
* [Yosys](http://www.clifford.at/yosys/)

# Examples

The following example project is included in this layer, along with a machine
(`picosoc-hx8k`) to demonstrate the build of bitstream and firmware within the
OE environment.

* [PicoSoC](https://github.com/cliffordwolf/picorv32/tree/master/picosoc)

To build this target, set `MACHINE = "picosoc-hx8k"` in your local.conf and
build the `picosoc` recipe with `bitbake picosoc`. The built bitstream and
firmware are populated into the `deploy/images/picosoc-hx8k` directory on
completion.

# Dependencies

This layer depends on:

	URI: git://git.openembedded.org/bitbake

	URI: git://git.openembedded.org/openembedded-core
	layers: meta

	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe

