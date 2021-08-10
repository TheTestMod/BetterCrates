package thetestmod.bettercrates.init;

import net.minecraft.block.Block;
import thetestmod.bettercrates.blocks.BaseCrate;
import thetestmod.bettercrates.enums.EnumCrate;

public class BlocksRegistry {


    public static Block
            WOODEN_CRATE = new BaseCrate(EnumCrate.WOODEN),
            IRON_CRATE = new BaseCrate(EnumCrate.IRON),
            COPPER_CRATE = new BaseCrate(EnumCrate.COPPER),
            TIN_CRATE = new BaseCrate(EnumCrate.TIN),
            GOLDEN_CRATE = new BaseCrate(EnumCrate.GOLDEN),
            DIAMOND_CRATE = new BaseCrate(EnumCrate.DIAMOND),
            OBSIDIAN_CRATE = new BaseCrate(EnumCrate.OBSIDIAN);

}
