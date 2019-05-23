package thetestmod.bettercrates.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import thetestmod.bettercrates.BetterCrates;

public class BlockBase extends Block {

	public BlockBase(String name, Material material, SoundType sound) {

		super(material);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(BetterCrates.CREATIVE_TAB);
	}
}
