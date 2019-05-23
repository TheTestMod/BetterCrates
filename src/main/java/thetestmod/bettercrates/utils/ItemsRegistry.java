package thetestmod.bettercrates.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.items.ItemUpgradeBase;

public class ItemsRegistry {

	public static Item

	// item
	UP_WOODEN = new ItemUpgradeBase("up_wooden", Blocks.CHEST, BlocksRegistry.WOODEN_CRATE.getDefaultState()),
	UP_COPPER = new ItemUpgradeBase("up_copper", Blocks.CHEST, BlocksRegistry.COPPER_CRATE.getDefaultState()),
	UP_TIN = new ItemUpgradeBase("up_tin", Blocks.CHEST, BlocksRegistry.TIN_CRATE.getDefaultState()),
	UP_IRON = new ItemUpgradeBase("up_iron", BlocksRegistry.WOODEN_CRATE, BlocksRegistry.IRON_CRATE.getDefaultState()),
	UP_GOLDEN = new ItemUpgradeBase("up_golden", BlocksRegistry.IRON_CRATE, BlocksRegistry.GOLDEN_CRATE.getDefaultState()),
	UP_GOLDEN2 = new ItemUpgradeBase("up_golden2", BlocksRegistry.COPPER_CRATE, BlocksRegistry.GOLDEN_CRATE.getDefaultState()),
	UP_GOLDEN3 = new ItemUpgradeBase("up_golden3", BlocksRegistry.TIN_CRATE, BlocksRegistry.GOLDEN_CRATE.getDefaultState()),
	UP_DIAMOND = new ItemUpgradeBase("up_diamond", BlocksRegistry.GOLDEN_CRATE, BlocksRegistry.DIAMOND_CRATE.getDefaultState()),
	UP_OBSIDIAN = new ItemUpgradeBase("up_obsidian", BlocksRegistry.DIAMOND_CRATE, BlocksRegistry.OBSIDIAN_CRATE.getDefaultState());

	public static void register() {

		// items
		setRegister(UP_WOODEN);
		setRegister(UP_COPPER);
		setRegister(UP_TIN);
		setRegister(UP_IRON);
		setRegister(UP_GOLDEN);
		setRegister(UP_GOLDEN2);
		setRegister(UP_GOLDEN3);
		setRegister(UP_DIAMOND);
		setRegister(UP_OBSIDIAN);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender() {

		// items
		setRender(UP_WOODEN);
		setRender(UP_COPPER);
		setRender(UP_TIN);
		setRender(UP_IRON);
		setRender(UP_GOLDEN);
		setRender(UP_GOLDEN2);
		setRender(UP_GOLDEN3);
		setRender(UP_DIAMOND);
		setRender(UP_OBSIDIAN);
	}

	private static void setRegister(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}

	@SideOnly(Side.CLIENT)
	private static void setRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
