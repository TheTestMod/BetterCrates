package thetestmod.bettercrates.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import thetestmod.bettercrates.BetterCrates;

public class BlockBase extends Block {

	public BlockBase(String name, Material material) {
		super(material);
		setRegistryName(name);
		setTranslationKey(name);
		setCreativeTab(BetterCrates.CREATIVE_TAB);
	}
}
