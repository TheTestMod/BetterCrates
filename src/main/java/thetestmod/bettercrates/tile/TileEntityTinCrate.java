package thetestmod.bettercrates.tile;

import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityTinCrate extends TileEntityBase {

    public TileEntityTinCrate() {
        super(EnumCrate.TIN.getType(), 45);
    }
}
