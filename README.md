# meta-hdl

This layer contains recipes for Hardware Description Languages (HDL) tools,
which includes tools for simulation, synthesis, etc.

Only tools released under open source licenses are contained in this layer.

# Tools

The following tools are available in this layer.

* [Arachne-PNR](https://github.com/cseed/arachne-pnr)
* [Icarus Verilog](http://iverilog.icarus.com/)
* [IceStorm](http://www.clifford.at/icestorm/)
* [Yosys](http://www.clifford.at/yosys/)

# Dependencies

This layer depends on:

	URI: git://git.openembedded.org/bitbake

	URI: git://git.openembedded.org/openembedded-core
	layers: meta

	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe

