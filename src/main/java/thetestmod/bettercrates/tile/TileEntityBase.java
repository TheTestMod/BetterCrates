package thetestmod.bettercrates.tile;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public abstract class TileEntityBase extends TileEntity /*implements INamedContainerProvider*/ {

    public CustomItemStackHandler inventory;

    public TileEntityBase(TileEntityType<?> type, int size) {
        super(type);
        inventory = new CustomItemStackHandler(size);
    }

    public NonNullList<ItemStack> getInv() {
        return inventory.getStacks();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        writeRestorableToNBT(compound);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        readRestorableFromNBT(compound);
        super.read(compound);
    }

    public void readRestorableFromNBT(CompoundNBT compound) {
        CompoundNBT tag = compound.getCompound("inventory");
        inventory.deserializeNBT(tag);
    }

    public void writeRestorableToNBT(CompoundNBT compound) {
        compound.put("inventory", inventory.serializeNBT());
    }

    private final LazyOptional<IItemHandler> holder = LazyOptional.of(() -> inventory);

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        return !removed && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? holder.cast()
                : super.getCapability(capability, facing);
    }

    public boolean isUsableByPlayer(PlayerEntity player) {
        if (world.getTileEntity(pos) != this || world.getBlockState(pos).getBlock() == Blocks.AIR)
            return false;

        return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
    }
}
