package thetestmod.bettercrates;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.gui.GuiBase13Rows;
import thetestmod.bettercrates.gui.GuiBase9Rows;
import thetestmod.bettercrates.init.BlocksRegistry;
import thetestmod.bettercrates.init.ContainerRegistry;

@Mod(BetterCrates.MODID)
public class BetterCrates {

    public static final String MODID = "bettercrates";

    public static final ItemGroup GROUP = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlocksRegistry.WOODEN_CRATE);
        }
    };

    public BetterCrates() {
        BlocksRegistry.class.getName();
        System.out.println("Just a log from BetterCrates >>> " + EnumCrate.VALUES.length + " ... " + BlocksRegistry.WOODEN_CRATE);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetupEvent);
    }

    public void commonSetupEvent(FMLClientSetupEvent event) {

//        ScreenManager.class.getName();
        ScreenManager.registerFactory(ContainerRegistry.CONTAINER_9_ROWS, GuiBase9Rows::new);
        ScreenManager.registerFactory(ContainerRegistry.CONTAINER_13_ROWS, GuiBase13Rows::new);
    }
}
