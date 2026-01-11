package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityWoodenCrate extends TileEntityBase {

    public TileEntityWoodenCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.WOODEN.getType(), 36, pos, state);
    }
}
