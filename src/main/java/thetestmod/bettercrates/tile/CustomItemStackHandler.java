package thetestmod.bettercrates.tile;

import net.minecraft.core.NonNullList;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraftforge.items.ItemStackHandler;
import thetestmod.bettercrates.blocks.BaseCrate;

import javax.annotation.Nonnull;

public class CustomItemStackHandler extends ItemStackHandler {

    public CustomItemStackHandler(int size) {
        super(size);
    }

    public NonNullList<ItemStack> getStacks() {
        return stacks;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return Block.byItem(stack.getItem()) instanceof BaseCrate  ? stack : super.insertItem(slot, stack, simulate);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return !(Block.byItem(stack.getItem()) instanceof BaseCrate);
    }

    public void serialize(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.stacks);
    }

    public void deserialize(ValueInput input) {
        ContainerHelper.loadAllItems(input, stacks);
        onLoad();
    }
}
