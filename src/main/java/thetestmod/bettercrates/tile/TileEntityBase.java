package thetestmod.bettercrates.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Optional;

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
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        writeRestorable(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        readRestorable(input);
        super.loadAdditional(input);
    }

    public void readRestorable(ValueInput input) {
        Optional<ValueInput> tag = input.child("inventory");
        tag.ifPresent(e -> inventory.deserialize(e));
    }

    public void writeRestorable(ValueOutput output) {
        inventory.serialize(output.child("inventory"));
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter getter) {
        super.applyImplicitComponents(getter);
        getter.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getInv());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder pComponents) {
        super.collectImplicitComponents(pComponents);
        pComponents.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getInv()));
    }

    @Override
    public void removeComponentsFromTag(ValueOutput output) {
        output.discard("inventory");
    }

    public boolean stillValid(Player player) {
        if (level.getBlockEntity(worldPosition) != this)
            return false;

        return player.distanceToSqr(worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D) <= 64D;
    }

    private LazyOptional<IItemHandler> holder = LazyOptional.of(() -> inventory);

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        return !remove && capability == ForgeCapabilities.ITEM_HANDLER ? holder.cast()
                : super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        holder.invalidate();
    }

    @Override
    public void reviveCaps() {
        holder = LazyOptional.of(() -> inventory);
    }
}
