package thetestmod.bettercrates.utils;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.blocks.CopperCrate;
import thetestmod.bettercrates.blocks.DiamondCrate;
import thetestmod.bettercrates.blocks.GoldenCrate;
import thetestmod.bettercrates.blocks.IronCrate;
import thetestmod.bettercrates.blocks.ObsidianCrate;
import thetestmod.bettercrates.blocks.TinCrate;
import thetestmod.bettercrates.blocks.WoodenCrate;

public class BlocksRegistry {

	public static Block
	// crate
	     	WOODEN_CRATE = new WoodenCrate("wooden_crate", Material.WOOD, SoundType.WOOD),
			IRON_CRATE = new IronCrate("iron_crate", Material.WOOD, SoundType.WOOD),
			COPPER_CRATE = new CopperCrate("copper_crate", Material.WOOD, SoundType.WOOD),
			TIN_CRATE = new TinCrate("tin_crate", Material.WOOD, SoundType.WOOD),
			GOLDEN_CRATE = new GoldenCrate("golden_crate", Material.WOOD, SoundType.WOOD),
			DIAMOND_CRATE = new DiamondCrate("diamond_crate", Material.WOOD, SoundType.WOOD),
			OBSIDIAN_CRATE = new ObsidianCrate("obsidian_crate", Material.WOOD, SoundType.WOOD);

	public static void register() {

		// crate
		setRegister(WOODEN_CRATE, true);
		setRegister(IRON_CRATE, true);
		setRegister(COPPER_CRATE, true);
		setRegister(TIN_CRATE, true);
		setRegister(GOLDEN_CRATE, true);
		setRegister(DIAMOND_CRATE, true);
		setRegister(OBSIDIAN_CRATE, true);
		// tile
		GameRegistry.registerTileEntity(((WoodenCrate) WOODEN_CRATE).getTileEntityClass(),
				WOODEN_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((IronCrate) IRON_CRATE).getTileEntityClass(),
				IRON_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((CopperCrate) COPPER_CRATE).getTileEntityClass(),
				COPPER_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((TinCrate) TIN_CRATE).getTileEntityClass(),
				TIN_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((GoldenCrate) GOLDEN_CRATE).getTileEntityClass(),
				GOLDEN_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((DiamondCrate) DIAMOND_CRATE).getTileEntityClass(),
				DIAMOND_CRATE.getRegistryName().toString());
		GameRegistry.registerTileEntity(((ObsidianCrate) OBSIDIAN_CRATE).getTileEntityClass(),
				OBSIDIAN_CRATE.getRegistryName().toString());
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender() {

		// crate
		setRender(WOODEN_CRATE);
		setRender(IRON_CRATE);
		setRender(COPPER_CRATE);
		setRender(TIN_CRATE);
		setRender(GOLDEN_CRATE);
		setRender(DIAMOND_CRATE);
		setRender(OBSIDIAN_CRATE);
	}

	private static void setRegister(Block block, boolean isTrue) // true = item block, false = only block
	{
		ForgeRegistries.BLOCKS.register(block);
		if (isTrue)
			ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	private static void setRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

}
