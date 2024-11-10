package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityGoldenCrate extends TileEntityBase {

    public TileEntityGoldenCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.GOLDEN.getType(), 81, pos, state);
    }
}
