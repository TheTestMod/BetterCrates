package thetestmod.bettercrates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BaseCrate extends Block implements EntityBlock {

    private final EnumCrate enumCrate;

    public BaseCrate(EnumCrate enumCrate) {
        super(Properties.of(Material.WOOD).strength(3F, enumCrate.getResistance()));
        this.enumCrate = enumCrate;
        enumCrate.setBlock(this);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            NetworkHooks.openScreen(((ServerPlayer) player), new SimpleMenuProvider(getContainerProvider(pos), Component.translatable(enumCrate.getTransKey())), (buf) -> buf.writeBlockPos(pos).writeInt(enumCrate.ordinal()));

            return InteractionResult.CONSUME;
        }
    }

    private MenuConstructor getContainerProvider(BlockPos pos) {
        if (enumCrate.ordinal() >= 5) {
            return (id, playerInv, player) -> new ContainerBase13Rows(id, playerInv, pos, enumCrate);
        } else {
            return (id, playerInv, player) -> new ContainerBase9Rows(id, playerInv, pos, enumCrate);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return enumCrate.getSupplier().create(blockPos, blockState);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        BlockEntity te = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (te instanceof TileEntityBase) {
            ItemStack stack = new ItemStack(Item.byBlock(this));
            CompoundTag tagCompound = new CompoundTag();
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
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            BlockEntity te = worldIn.getBlockEntity(pos);
            if (te instanceof TileEntityBase) {
                ((TileEntityBase) te).readRestorableFromNBT(tag);
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.crate_base.tooltip"));
    }
}
