package thetestmod.bettercrates.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;

public class ContainerBase extends Container {

    private TileEntityBase tile;
    public EnumCrate enumCrate;

    public ContainerBase(@Nullable ContainerType<?> type, int id, TileEntityBase tile, PlayerInventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(type, id);
        this.tile = tile;
        this.enumCrate = enumCrate;
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);

        for (int i = 0; i < enumCrate.getSlots()[0]; i++) {
            for (int j = 0; j < enumCrate.getSlots()[1]; j++) {
                addSlot(new SlotItemHandler(inventory, j + i * enumCrate.getSlots()[1], 12 + j * 18, 5 + i * 18) {
                    @Override
                    public void onSlotChanged() {
                        tile.markDirty();
                    }
                });
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInv, j + i * 9 + 9, enumCrate.getSlots()[2] + j * 18, enumCrate.getSlots()[3] + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlot(new Slot(playerInv, k, enumCrate.getSlots()[4] + k * 18, enumCrate.getSlots()[5]));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return tile.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

            if (index < containerSlots) {
                if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
