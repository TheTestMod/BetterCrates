package thetestmod.bettercrates;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegistryObject;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.gui.GuiBase13Rows;
import thetestmod.bettercrates.gui.GuiBase9Rows;

@Mod(BetterCrates.MODID)
public class BetterCrates {

    public static final String MODID = "bettercrates";

    public BetterCrates() {
        BCRegistry.register();
        System.out.println("ABC >>> " + EnumCrate.VALUES.length + " ... " + BCRegistry.WOODEN_CRATE);
        if (FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetupEvent);
        }

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerTabs);
    }

    @OnlyIn(Dist.CLIENT)
    public void clientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(BCRegistry.CONTAINER_9_ROWS.get(), GuiBase9Rows::new);
            MenuScreens.register(BCRegistry.CONTAINER_13_ROWS.get(), GuiBase13Rows::new);
        });
    }

    private void registerTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(MODID, MODID), builder -> builder
            .icon(() -> new ItemStack(BCRegistry.WOODEN_CRATE.get()))
            .title(Component.translatable("itemGroup.bettercrates"))
            .displayItems((featureFlags, output, hasOp) ->
                    BCRegistry.ITEMS.getEntries()
                            .stream()
                            .map(RegistryObject::get)
                            .forEach(output::accept)
            )
        );
    }
}
