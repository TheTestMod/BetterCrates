package thetestmod.bettercrates.blocks;

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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BaseCrate extends BlockTileEntity {

    private final EnumCrate enumCrate;

    public BaseCrate(EnumCrate enumCrate) {
        super(enumCrate.getName(), Material.WOOD);
        this.enumCrate = enumCrate;
        setHardness(3.0f);
        setResistance(enumCrate.getResistance());
        setHarvestLevel("axe", 0);
        GameRegistry.registerTileEntity(enumCrate.getTile(), getRegistryName());
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(BetterCrates.instance, enumCrate.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return enumCrate.getSupplier().get();
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess w, BlockPos pos, IBlockState s, int f) {
        TileEntity te = w.getTileEntity(pos);
        if (te instanceof TileEntityBase) {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            NBTTagCompound tagCompound = new NBTTagCompound();
            ((TileEntityBase) te).writeRestorableToNBT(tagCompound);
            stack.setTagCompound(tagCompound);
            List<ItemStack> result = new ArrayList<>();
            result.add(stack);
            return result;
        } else {
            return super.getDrops(w, pos, s, f);
        }
    }

    @Override
    public void onBlockPlacedBy(World w, BlockPos pos, IBlockState s, EntityLivingBase p, ItemStack is) {
        NBTTagCompound tagCompound = is.getTagCompound();
        if (tagCompound != null) {
            TileEntity te = w.getTileEntity(pos);
            if (te instanceof TileEntityBase) {
                ((TileEntityBase) te).readRestorableFromNBT(tagCompound);
            }
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState s, World w, BlockPos pos, EntityPlayer p, boolean willHarvest) {
        if (willHarvest) {
            return true;
        }
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

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
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
