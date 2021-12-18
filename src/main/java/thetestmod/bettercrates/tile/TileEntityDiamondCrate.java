package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityDiamondCrate extends TileEntityBase {

    public TileEntityDiamondCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.DIAMOND.getType(), 117, pos, state);
    }
}
