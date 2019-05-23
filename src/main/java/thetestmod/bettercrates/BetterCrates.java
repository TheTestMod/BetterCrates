package thetestmod.bettercrates;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thetestmod.bettercrates.proxy.CommonProxy;
import thetestmod.bettercrates.utils.BlocksRegistry;
import thetestmod.bettercrates.utils.GuiRegistry;

@Mod(modid = BetterCrates.MODID, version = BetterCrates.VERSION, canBeDeactivated = true)

public class BetterCrates {

	public static final String MODID = "bettercrates";
	public static final String VERSION = "0.0.1";

	@SidedProxy(clientSide = "thetestmod.bettercrates.proxy.ClientProxy", serverSide = "thetestmod.bettercrates.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("bettercrates") {

		@Override
		public ItemStack getTabIconItem() {

			return new ItemStack(BlocksRegistry.WOODEN_CRATE);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		proxy.preInit(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiRegistry());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		proxy.postInit(event);
	}

	@Mod.Instance(BetterCrates.MODID)
	public static BetterCrates instance;

}
