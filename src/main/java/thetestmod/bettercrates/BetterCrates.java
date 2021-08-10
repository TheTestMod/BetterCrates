package thetestmod.bettercrates;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.gui.GuiBase13Rows;
import thetestmod.bettercrates.gui.GuiBase9Rows;
import thetestmod.bettercrates.init.BlocksRegistry;
import thetestmod.bettercrates.init.ContainerRegistry;

@Mod(BetterCrates.MODID)
public class BetterCrates {

    public static final String MODID = "bettercrates";

    public static final CreativeModeTab GROUP = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlocksRegistry.WOODEN_CRATE);
        }
    };

    public BetterCrates() {
        BlocksRegistry.class.getName();
        System.out.println("ABC >>> " + EnumCrate.VALUES.length + " ... " + BlocksRegistry.WOODEN_CRATE);
        if (FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetupEvent);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void clientSetupEvent(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerRegistry.CONTAINER_9_ROWS, GuiBase9Rows::new);
        MenuScreens.register(ContainerRegistry.CONTAINER_13_ROWS, GuiBase13Rows::new);
    }
}
