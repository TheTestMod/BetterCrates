package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.enums.EnumCrate;

public class TileEntityObsidianCrate extends TileEntityBase {

    public TileEntityObsidianCrate(BlockPos pos, BlockState state) {
        super(EnumCrate.OBSIDIAN.getType(), 117, pos, state);
    }
}
