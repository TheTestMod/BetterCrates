package thetestmod.bettercrates.tile;

import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityCopperCrate extends TileEntityBase {

    public TileEntityCopperCrate() {
        super(EnumCrate.COPPER.getType(), 45);
    }
}
