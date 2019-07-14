package thetestmod.bettercrates.tile;

import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityWoodenCrate extends TileEntityBase {

    public TileEntityWoodenCrate() {
        super(EnumCrate.WOODEN.getType(), 36);
    }
}
