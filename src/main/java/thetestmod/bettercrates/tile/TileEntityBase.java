package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TileEntityBase extends BlockEntity /*implements INamedContainerProvider*/ {

    public CustomItemStackHandler inventory;

    public TileEntityBase(BlockEntityType<?> type, int size, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = new CustomItemStackHandler(size);
    }

    public NonNullList<ItemStack> getInv() {
        return inventory.getStacks();
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        writeRestorableToNBT(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        readRestorableFromNBT(compound);
        super.load(compound);
    }

    public void readRestorableFromNBT(CompoundTag compound) {
        CompoundTag tag = compound.getCompound("inventory");
        inventory.deserializeNBT(tag);
    }

    public void writeRestorableToNBT(CompoundTag compound) {
        compound.put("inventory", inventory.serializeNBT());
    }

    public boolean stillValid(Player player) {
        if (level.getBlockEntity(worldPosition) != this)
            return false;

        return player.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64D;
    }
}
