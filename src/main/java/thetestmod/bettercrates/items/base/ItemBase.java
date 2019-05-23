package thetestmod.bettercrates.items.base;

import net.minecraft.item.Item;
import thetestmod.bettercrates.BetterCrates;

public class ItemBase extends Item {

	public ItemBase(String name) {
		setRegistryName(name);
		setTranslationKey(name);
		setCreativeTab(BetterCrates.CREATIVE_TAB);
	}
}
