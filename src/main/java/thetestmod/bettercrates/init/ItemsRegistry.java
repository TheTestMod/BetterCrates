package thetestmod.bettercrates.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.items.ItemUpgradeBase;

public class ItemsRegistry {

	public static Item
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
		register(UP_WOODEN);
		register(UP_COPPER);
		register(UP_TIN);
		register(UP_IRON);
		register(UP_GOLDEN);
		register(UP_GOLDEN2);
		register(UP_GOLDEN3);
		register(UP_DIAMOND);
		register(UP_OBSIDIAN);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRender() {
		setupRender(UP_WOODEN);
		setupRender(UP_COPPER);
		setupRender(UP_TIN);
		setupRender(UP_IRON);
		setupRender(UP_GOLDEN);
		setupRender(UP_GOLDEN2);
		setupRender(UP_GOLDEN3);
		setupRender(UP_DIAMOND);
		setupRender(UP_OBSIDIAN);
	}

	private static void register(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}

	@SideOnly(Side.CLIENT)
	private static void setupRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
