package thetestmod.bettercrates.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import thetestmod.bettercrates.items.ItemUpgradeBase;

import java.util.ArrayList;
import java.util.List;

public class ItemsRegistry {

    public static List<Item> items = new ArrayList<>();

    public static Item
            UP_WOODEN = new ItemUpgradeBase("up_wooden", Blocks.CHEST, BlocksRegistry.WOODEN_CRATE.defaultBlockState()),
            UP_COPPER = new ItemUpgradeBase("up_copper", Blocks.CHEST, BlocksRegistry.COPPER_CRATE.defaultBlockState()),
            UP_TIN = new ItemUpgradeBase("up_tin", Blocks.CHEST, BlocksRegistry.TIN_CRATE.defaultBlockState()),
            UP_IRON = new ItemUpgradeBase("up_iron", BlocksRegistry.WOODEN_CRATE, BlocksRegistry.IRON_CRATE.defaultBlockState()),
            UP_GOLDEN = new ItemUpgradeBase("up_golden", BlocksRegistry.IRON_CRATE, BlocksRegistry.GOLDEN_CRATE.defaultBlockState()),
            UP_GOLDEN2 = new ItemUpgradeBase("up_golden2", BlocksRegistry.COPPER_CRATE, BlocksRegistry.GOLDEN_CRATE.defaultBlockState()),
            UP_GOLDEN3 = new ItemUpgradeBase("up_golden3", BlocksRegistry.TIN_CRATE, BlocksRegistry.GOLDEN_CRATE.defaultBlockState()),
            UP_DIAMOND = new ItemUpgradeBase("up_diamond", BlocksRegistry.GOLDEN_CRATE, BlocksRegistry.DIAMOND_CRATE.defaultBlockState()),
            UP_OBSIDIAN = new ItemUpgradeBase("up_obsidian", BlocksRegistry.DIAMOND_CRATE, BlocksRegistry.OBSIDIAN_CRATE.defaultBlockState());

}
