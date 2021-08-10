package thetestmod.bettercrates.tile;

import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityGoldenCrate extends TileEntityBase {

    public TileEntityGoldenCrate() {
        super(EnumCrate.GOLDEN.getType(), 81);
    }
}
