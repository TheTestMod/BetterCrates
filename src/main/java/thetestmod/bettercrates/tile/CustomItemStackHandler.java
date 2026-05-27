package thetestmod.bettercrates.tile;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import thetestmod.bettercrates.blocks.BaseCrate;

public class CustomItemStackHandler extends ItemStacksResourceHandler {

    public CustomItemStackHandler(int size) {
        super(size);
    }

    public NonNullList<ItemStack> getStacks() {
        return stacks;
    }

    @Override
    public boolean isValid(int index, ItemResource resource) {
        return !(Block.byItem(resource.getItem()) instanceof BaseCrate);
    }
}
