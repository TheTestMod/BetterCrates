package thetestmod.bettercrates;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.init.BlocksRegistry;
import thetestmod.bettercrates.init.GuiHandler;
import thetestmod.bettercrates.init.ItemsRegistry;

@Mod(modid = BetterCrates.MODID, name = "Better Crates", version = "@VERSION@")
public class BetterCrates {

	public static final String MODID = "bettercrates";

	@Mod.Instance(BetterCrates.MODID)
	public static BetterCrates instance;

	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlocksRegistry.WOODEN_CRATE);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		BlocksRegistry.register();
		ItemsRegistry.register();
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			preInit();
		}
	}

	@SideOnly(Side.CLIENT)
	public void preInit() {
		ItemsRegistry.registerRender();
		BlocksRegistry.registerRender();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
}
