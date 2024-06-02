package thetestmod.bettercrates;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;
import thetestmod.bettercrates.blocks.BaseCrate;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.items.ItemUpgradeBase;
import thetestmod.bettercrates.tile.TileEntityBase;

public class BCRegistry {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, BetterCrates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, BetterCrates.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, BetterCrates.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, BetterCrates.MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BetterCrates.MODID);

    public static DeferredHolder<Block, Block> WOODEN_CRATE = BLOCKS.register(EnumCrate.WOODEN.getName(), () -> new BaseCrate(EnumCrate.WOODEN));
    public static DeferredHolder<Block, Block> IRON_CRATE = BLOCKS.register(EnumCrate.IRON.getName(), () -> new BaseCrate(EnumCrate.IRON));
    public static DeferredHolder<Block, Block> COPPER_CRATE = BLOCKS.register(EnumCrate.COPPER.getName(), () -> new BaseCrate(EnumCrate.COPPER));
    public static DeferredHolder<Block, Block> TIN_CRATE = BLOCKS.register(EnumCrate.TIN.getName(), () -> new BaseCrate(EnumCrate.TIN));
    public static DeferredHolder<Block, Block> GOLDEN_CRATE = BLOCKS.register(EnumCrate.GOLDEN.getName(), () -> new BaseCrate(EnumCrate.GOLDEN));
    public static DeferredHolder<Block, Block> DIAMOND_CRATE = BLOCKS.register(EnumCrate.DIAMOND.getName(), () -> new BaseCrate(EnumCrate.DIAMOND));
    public static DeferredHolder<Block, Block> OBSIDIAN_CRATE = BLOCKS.register(EnumCrate.OBSIDIAN.getName(), () -> new BaseCrate(EnumCrate.OBSIDIAN));

    public static DeferredHolder<Item, Item> UP_WOODEN = ITEMS.register("up_wooden", () -> new ItemUpgradeBase(Blocks.CHEST, WOODEN_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_COPPER = ITEMS.register("up_copper", () -> new ItemUpgradeBase(Blocks.CHEST, COPPER_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_TIN = ITEMS.register("up_tin", () -> new ItemUpgradeBase(Blocks.CHEST, TIN_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_IRON = ITEMS.register("up_iron", () -> new ItemUpgradeBase(WOODEN_CRATE.get(), IRON_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_GOLDEN = ITEMS.register("up_golden", () -> new ItemUpgradeBase(IRON_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_GOLDEN2 = ITEMS.register("up_golden2", () -> new ItemUpgradeBase(COPPER_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_GOLDEN3 = ITEMS.register("up_golden3", () -> new ItemUpgradeBase(TIN_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_DIAMOND = ITEMS.register("up_diamond", () -> new ItemUpgradeBase(GOLDEN_CRATE.get(), DIAMOND_CRATE.get().defaultBlockState()));
    public static DeferredHolder<Item, Item> UP_OBSIDIAN = ITEMS.register("up_obsidian", () -> new ItemUpgradeBase(DIAMOND_CRATE.get(), OBSIDIAN_CRATE.get().defaultBlockState()));

    public static DeferredHolder<MenuType<?>, MenuType<ContainerBase9Rows>> CONTAINER_9_ROWS = CONTAINERS.register("container9rows", () -> IMenuTypeExtension.create(ContainerBase9Rows::new));
    public static DeferredHolder<MenuType<?>, MenuType<ContainerBase13Rows>> CONTAINER_13_ROWS = CONTAINERS.register("container13rows", () -> IMenuTypeExtension.create(ContainerBase13Rows::new));

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register(
            BetterCrates.MODID,
            () -> CreativeModeTab.builder()
                .icon(() -> new ItemStack(BCRegistry.WOODEN_CRATE.get()))
                .title(Component.translatable("itemGroup.bettercrates"))
                .displayItems((supplier, output) ->
                        BCRegistry.ITEMS.getEntries()
                                .stream()
                                .map(DeferredHolder::get)
                                .forEach(output::accept))
                .build()
    );

    public static void register(IEventBus modEventBus) {
        for (EnumCrate crate : EnumCrate.VALUES) {
            ITEMS.register(crate.getName(), () -> new BlockItem(crate.getBlock(), new Item.Properties()));
            TILE_ENTITIES.register(crate.getName(), crate::getType);
        }

        BLOCKS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        ITEMS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        TABS.register(modEventBus);

        modEventBus.addListener((RegisterCapabilitiesEvent event) -> {
            for (EnumCrate crate : EnumCrate.VALUES) {
                event.registerBlock(Capabilities.ItemHandler.BLOCK, (level, pos, state, be, side) -> ((TileEntityBase) be).inventory, crate.getBlock());
            }
        });
    }
}
