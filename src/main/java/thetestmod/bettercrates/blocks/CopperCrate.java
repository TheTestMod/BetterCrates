package thetestmod.bettercrates.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.blocks.base.BlockTileEntity;
import thetestmod.bettercrates.tile.TileEntityCopperCrate;
import thetestmod.bettercrates.utils.GuiRegistry;

public class CopperCrate extends BlockTileEntity<TileEntityCopperCrate> {

	public CopperCrate(String name, Material material, SoundType sound) {

		super(name, material, sound);
		this.setHardness(3.0f);
		this.setResistance(5.0f);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public Class<TileEntityCopperCrate> getTileEntityClass() {
		return TileEntityCopperCrate.class;
	}

	@Nullable
	@Override
	public TileEntityCopperCrate createTileEntity(World world, IBlockState state) {
		return new TileEntityCopperCrate();
	}

	// logic
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			ItemStack heldItem = player.getHeldItem(hand);
			TileEntityCopperCrate tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			player.openGui(BetterCrates.instance, GuiRegistry.COPPER_CRATE, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess w, BlockPos pos, IBlockState s, int f) {
		TileEntity te = w.getTileEntity(pos);
		if (te instanceof TileEntityCopperCrate) {
			ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
			NBTTagCompound tagCompound = new NBTTagCompound();
			((TileEntityCopperCrate) te).writeRestorableToNBT(tagCompound);
			stack.setTagCompound(tagCompound);
			List<ItemStack> result = new ArrayList<>();
			result.add(stack);
			return result;
		} else
			return super.getDrops(w, pos, s, f);
	}

	@Override
	public void onBlockPlacedBy(World w, BlockPos pos, IBlockState s, EntityLivingBase p, ItemStack is) {
		NBTTagCompound tagCompound = is.getTagCompound();
		if (tagCompound != null) {
			TileEntity te = w.getTileEntity(pos);
			if (te instanceof TileEntityCopperCrate)
				((TileEntityCopperCrate) te).readRestorableFromNBT(tagCompound);
		}
	}

	@Override
	public boolean removedByPlayer(IBlockState s, World w, BlockPos pos, EntityPlayer p, boolean willHarvest) {
		if (willHarvest)
			return true;
		return super.removedByPlayer(s, w, pos, p, willHarvest);
	}

	@Override
	public void harvestBlock(World w, EntityPlayer p, BlockPos pos, IBlockState s, TileEntity te, ItemStack is) {
		super.harvestBlock(w, p, pos, s, te, is);
		w.setBlockToAir(pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {

		tooltip.add(I18n.format("item.crate_base.tooltip"));
	}

	// render
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}
