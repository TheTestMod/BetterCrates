package thetestmod.bettercrates;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.init.ContainerRegistry;
import thetestmod.bettercrates.init.ItemsRegistry;

@Mod.EventBusSubscriber(modid = BetterCrates.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BCEventHandler {

    @SubscribeEvent
    public static void onBlockRegistry(RegistryEvent.Register<Block> event) {
        for (EnumCrate crate : EnumCrate.VALUES) {
            event.getRegistry().register(crate.getBlock());
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        for (EnumCrate crate : EnumCrate.VALUES) {
            event.getRegistry().register(new BlockItem(crate.getBlock(), new Item.Properties().tab(BetterCrates.GROUP).addToolType(ToolType.AXE, 0)).setRegistryName(crate.getBlock().getRegistryName()));
        }
        ItemsRegistry.items.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void onTileRegistry(RegistryEvent.Register<BlockEntityType<?>> event) {
        for (EnumCrate crate : EnumCrate.VALUES) {
            event.getRegistry().register(crate.getType().setRegistryName(BetterCrates.MODID, crate.getName()));
        }
    }

    @SubscribeEvent
    public static void onContainerRegistry(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().register(ContainerRegistry.CONTAINER_9_ROWS.setRegistryName(BetterCrates.MODID, "container9rows"));
        event.getRegistry().register(ContainerRegistry.CONTAINER_13_ROWS.setRegistryName(BetterCrates.MODID, "container13rows"));
    }
}
