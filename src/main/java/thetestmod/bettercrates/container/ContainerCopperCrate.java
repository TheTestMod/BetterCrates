package thetestmod.bettercrates.container;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import thetestmod.bettercrates.tile.TileEntityCopperCrate;

@ChestContainer(isLargeChest = true, rowSize = 9)
public class ContainerCopperCrate extends Container {
	
	//add slot
		public ContainerCopperCrate(InventoryPlayer playerInv, final TileEntityCopperCrate crate) {
			IItemHandler inventory = crate.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 9; j++)
			addSlotToContainer(new SlotItemHandler(inventory, j + i * 9, 12/**ширина*/ + j * 18, 5/**высота*/ + i * 18) {
				@Override
				public void onSlotChanged() {
					crate.markDirty();
				}
			});
		
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 12/**ширина*/ + j * 18, 102/**высота*/ + i * 18));
				}
			}
			
		
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(playerInv, k, 12/**ширина*/ + k * 18, 160/**высота*/));
			}
			
		}
		
		//interact
		@Override
		public boolean canInteractWith(EntityPlayer player) {
			return true;
		}
		
		//transfer slot
		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int index) {
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
