package thetestmod.bettercrates.items.base;

import net.minecraft.item.Item;
import thetestmod.bettercrates.BetterCrates;

public class ItemBase extends Item {

	public ItemBase(String name) {

		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(BetterCrates.CREATIVE_TAB);
	}

}
