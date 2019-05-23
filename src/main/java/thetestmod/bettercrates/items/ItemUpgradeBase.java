package thetestmod.bettercrates.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.items.base.ItemBase;
import thetestmod.bettercrates.tile.TileEntityBase;

public class ItemUpgradeBase extends ItemBase {

	private Block block;
	private IBlockState state;

	public ItemUpgradeBase(String name, Block block, IBlockState state) {
		super(name);
		this.block = block;
		this.state = state;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer p, World w, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

		if (!w.isRemote && block != null && w.getBlockState(pos).getBlock() == block) {

			TileEntity te = w.getTileEntity(pos);
			if (te != null) {

				NBTTagCompound tag = new NBTTagCompound();
				if (te instanceof TileEntityChest) {

					NonNullList<ItemStack> inv = ReflectionHelper.getPrivateValue(TileEntityChest.class,
							(TileEntityChest) te, "chestContents", "field_145985_p", "p");
					ItemStackHelper.saveAllItems(tag, inv);
					inv.clear();
				}

				else {

					NonNullList<ItemStack> inv = ((TileEntityBase) te).getInv();
					ItemStackHelper.saveAllItems(tag, inv);
					inv.clear();
				}
				w.setBlockState(pos, state);
				TileEntityBase teNew = (TileEntityBase) w.getTileEntity(pos);
				ItemStackHelper.loadAllItems(tag, teNew.getInv());
				p.getHeldItem(hand).shrink(1);
				p.playSound(SoundEvents.BLOCK_ENDERCHEST_OPEN, 1.0F, 1.0F);
				p.world.playSound(null, p.getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.4F, 0.8F);

				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {

		tooltip.add(I18n.format("item." + getUnlocalizedName().substring(5) + ".tooltip"));
		tooltip.add(I18n.format("item.up.tooltip_base"));
		
	}
}