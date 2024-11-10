package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityIronCrate extends TileEntityBase {

    public TileEntityIronCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.IRON.getType(), 63, pos, state);
    }
}
