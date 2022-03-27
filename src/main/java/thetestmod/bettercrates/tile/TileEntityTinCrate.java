package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityTinCrate extends TileEntityBase {

    public TileEntityTinCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.TIN.getType(), 45, pos, state);
    }
}
