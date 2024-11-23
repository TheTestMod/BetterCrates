package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityCopperCrate extends TileEntityBase {

    public TileEntityCopperCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.COPPER.getType(), 45, pos, state);
    }
}
