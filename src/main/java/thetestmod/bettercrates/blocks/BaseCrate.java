package thetestmod.bettercrates.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BaseCrate extends Block {

    private final EnumCrate enumCrate;

    public BaseCrate(EnumCrate enumCrate) {
        super(Properties.create(Material.WOOD).hardnessAndResistance(3F, enumCrate.getResistance()));
        setRegistryName(BetterCrates.MODID, enumCrate.getName());
        this.enumCrate = enumCrate;
        enumCrate.setBlock(this);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        if (!world.isRemote) {
            NetworkHooks.openGui(((ServerPlayerEntity) player), new SimpleNamedContainerProvider(getContainerProvider(pos), new TranslationTextComponent(enumCrate.getTransKey())), (buf) -> buf.writeBlockPos(pos).writeInt(enumCrate.ordinal()));
        }
        return true;
    }

    private IContainerProvider getContainerProvider(BlockPos pos) {
        if (enumCrate.ordinal() >= 5) {
            return (id, playerInv, player) -> new ContainerBase13Rows(id, playerInv, pos, enumCrate);
        } else {
            return (id, playerInv, player) -> new ContainerBase9Rows(id, playerInv, pos, enumCrate);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return enumCrate.getSupplier().get();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity te = builder.get(LootParameters.BLOCK_ENTITY);
        if (te instanceof TileEntityBase) {
            ItemStack stack = new ItemStack(Item.getItemFromBlock(this));
            CompoundNBT tagCompound = new CompoundNBT();
            ((TileEntityBase) te).writeRestorableToNBT(tagCompound);
            stack.setTag(tagCompound);
            List<ItemStack> result = new ArrayList<>();
            result.add(stack);
            return result;
        } else {
            return super.getDrops(state, builder);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        if (tag != null) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityBase) {
                ((TileEntityBase) te).readRestorableFromNBT(tag);
            }
        }
    }

    @Override
    public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
//        if (willHarvest) {
//            return true;
//        }
        return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("item.crate_base.tooltip"));
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
