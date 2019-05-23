package thetestmod.bettercrates.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

	public static void register() {
		register(WOODEN_CRATE);
		register(IRON_CRATE);
		register(COPPER_CRATE);
		register(TIN_CRATE);
		register(GOLDEN_CRATE);
		register(DIAMOND_CRATE);
		register(OBSIDIAN_CRATE);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender() {
		setupRender(WOODEN_CRATE);
		setupRender(IRON_CRATE);
		setupRender(COPPER_CRATE);
		setupRender(TIN_CRATE);
		setupRender(GOLDEN_CRATE);
		setupRender(DIAMOND_CRATE);
		setupRender(OBSIDIAN_CRATE);
	}

	private static void register(Block block)
	{
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	private static void setupRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

}
