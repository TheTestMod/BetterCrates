package thetestmod.bettercrates.tile;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBase extends TileEntity {

	public ItemStackHandler inventory;

	public TileEntityBase(int size) {
		inventory = new ItemStackHandler(size);
	}

	public NonNullList<ItemStack> getInv() {
		return ReflectionHelper.getPrivateValue(ItemStackHandler.class, inventory, "stacks");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeRestorableToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		readRestorableFromNBT(compound);
		super.readFromNBT(compound);
	}

	// test_nbt
	public void readRestorableFromNBT(NBTTagCompound compound) {
		NBTTagCompound tag = compound.getCompoundTag("inventory");
		inventory.deserializeNBT(tag);
	}

	public void writeRestorableToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());

	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory
				: super.getCapability(capability, facing);
	}

	public boolean isUsableByPlayer(EntityPlayer p) {
		if (world.getTileEntity(pos) != this || world.getBlockState(pos).getBlock() == Blocks.AIR)
			return false;

		return p.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64D;
	}
}