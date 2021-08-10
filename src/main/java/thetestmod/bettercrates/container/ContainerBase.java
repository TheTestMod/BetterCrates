package thetestmod.bettercrates.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;

public class ContainerBase extends AbstractContainerMenu {

    private TileEntityBase tile;
    public EnumCrate enumCrate;

    public ContainerBase(@Nullable MenuType<?> type, int id, TileEntityBase tile, Inventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(type, id);
        this.tile = tile;
        this.enumCrate = enumCrate;
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);

        for (int i = 0; i < enumCrate.getSlots()[0]; i++) {
            for (int j = 0; j < enumCrate.getSlots()[1]; j++) {
                addSlot(new SlotItemHandler(inventory, j + i * enumCrate.getSlots()[1], 12 + j * 18, 5 + i * 18));
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
    public boolean stillValid(Player playerIn) {
        return tile.stillValid(playerIn);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            int containerSlots = slots.size() - player.getInventory().items.size();

            if (index < containerSlots) {
                if (!this.moveItemStackTo(itemstack1, containerSlots, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
