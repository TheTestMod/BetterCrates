package thetestmod.bettercrates;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thetestmod.bettercrates.blocks.BaseCrate;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.items.ItemUpgradeBase;

public class BCRegistry {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BetterCrates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BetterCrates.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BetterCrates.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BetterCrates.MODID);

    public static RegistryObject<Block> WOODEN_CRATE = BLOCKS.register(EnumCrate.WOODEN.getName(), () -> new BaseCrate(EnumCrate.WOODEN));
    public static RegistryObject<Block> IRON_CRATE = BLOCKS.register(EnumCrate.IRON.getName(), () -> new BaseCrate(EnumCrate.IRON));
    public static RegistryObject<Block> COPPER_CRATE = BLOCKS.register(EnumCrate.COPPER.getName(), () -> new BaseCrate(EnumCrate.COPPER));
    public static RegistryObject<Block> TIN_CRATE = BLOCKS.register(EnumCrate.TIN.getName(), () -> new BaseCrate(EnumCrate.TIN));
    public static RegistryObject<Block> GOLDEN_CRATE = BLOCKS.register(EnumCrate.GOLDEN.getName(), () -> new BaseCrate(EnumCrate.GOLDEN));
    public static RegistryObject<Block> DIAMOND_CRATE = BLOCKS.register(EnumCrate.DIAMOND.getName(), () -> new BaseCrate(EnumCrate.DIAMOND));
    public static RegistryObject<Block> OBSIDIAN_CRATE = BLOCKS.register(EnumCrate.OBSIDIAN.getName(), () -> new BaseCrate(EnumCrate.OBSIDIAN));

    public static RegistryObject<Item> UP_WOODEN = ITEMS.register("up_wooden", () -> new ItemUpgradeBase(Blocks.CHEST, WOODEN_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_COPPER = ITEMS.register("up_copper", () -> new ItemUpgradeBase(Blocks.CHEST, COPPER_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_TIN = ITEMS.register("up_tin", () -> new ItemUpgradeBase(Blocks.CHEST, TIN_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_IRON = ITEMS.register("up_iron", () -> new ItemUpgradeBase(WOODEN_CRATE.get(), IRON_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_GOLDEN = ITEMS.register("up_golden", () -> new ItemUpgradeBase(IRON_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_GOLDEN2 = ITEMS.register("up_golden2", () -> new ItemUpgradeBase(COPPER_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_GOLDEN3 = ITEMS.register("up_golden3", () -> new ItemUpgradeBase(TIN_CRATE.get(), GOLDEN_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_DIAMOND = ITEMS.register("up_diamond", () -> new ItemUpgradeBase(GOLDEN_CRATE.get(), DIAMOND_CRATE.get().defaultBlockState()));
    public static RegistryObject<Item> UP_OBSIDIAN = ITEMS.register("up_obsidian", () -> new ItemUpgradeBase(DIAMOND_CRATE.get(), OBSIDIAN_CRATE.get().defaultBlockState()));

    public static RegistryObject<MenuType<ContainerBase9Rows>> CONTAINER_9_ROWS = CONTAINERS.register("container9rows", () -> IForgeMenuType.create(ContainerBase9Rows::new));
    public static RegistryObject<MenuType<ContainerBase13Rows>> CONTAINER_13_ROWS = CONTAINERS.register("container13rows", () -> IForgeMenuType.create(ContainerBase13Rows::new));

    public static void register() {
        for (EnumCrate crate : EnumCrate.VALUES) {
            ITEMS.register(crate.getName(), () -> new BlockItem(crate.getBlock(), new Item.Properties().tab(BetterCrates.GROUP)));
            TILE_ENTITIES.register(crate.getName(), crate::getType);
        }

        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
