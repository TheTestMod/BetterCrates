package thetestmod.bettercrates;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.gui.GuiBase13Rows;
import thetestmod.bettercrates.gui.GuiBase9Rows;

@Mod(BetterCrates.MODID)
public class BetterCrates {

    public static final String MODID = "bettercrates";

    public BetterCrates(IEventBus modEventBus) {
        BCRegistry.register(modEventBus);
        System.out.println("ABC >>> " + EnumCrate.VALUES.length + " ... " + BCRegistry.WOODEN_CRATE);
        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(this::registerMenuScreensEvent);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void registerMenuScreensEvent(RegisterMenuScreensEvent event) {
        event.register(BCRegistry.CONTAINER_9_ROWS.get(), GuiBase9Rows::new);
        event.register(BCRegistry.CONTAINER_13_ROWS.get(), GuiBase13Rows::new);
    }
}
