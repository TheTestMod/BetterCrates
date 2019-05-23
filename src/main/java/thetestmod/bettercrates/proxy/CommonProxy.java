package thetestmod.bettercrates.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thetestmod.bettercrates.utils.BlocksRegistry;
import thetestmod.bettercrates.utils.ItemsRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		
		BlocksRegistry.register();
		ItemsRegistry.register();

	}

	public void init(FMLInitializationEvent event) {
		
	}

	public void postInit(FMLPostInitializationEvent event) {

	}

}
