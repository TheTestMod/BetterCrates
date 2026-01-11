package thetestmod.bettercrates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BaseCrate extends Block implements EntityBlock {

    private final EnumCrate enumCrate;

    public BaseCrate(EnumCrate enumCrate, ResourceKey<Block> key) {
        super(Properties.of().strength(3F, enumCrate.getResistance()).setId(key));
        this.enumCrate = enumCrate;
        enumCrate.setBlock(this);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            ((ServerPlayer) player).openMenu(new SimpleMenuProvider(getContainerProvider(pos), Component.translatable(enumCrate.getTransKey())), (buf) -> buf.writeBlockPos(pos).writeInt(enumCrate.ordinal()));

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
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity te = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (te instanceof TileEntityBase) {
            ItemStack stack = new ItemStack(Item.byBlock(this));
            saveBlockEntityToItem(te, stack, builder.getLevel().registryAccess());
            List<ItemStack> result = new ArrayList<>();
            result.add(stack);
            return result;
        } else {
            return super.getDrops(state, builder);
        }
    }

    private static void saveBlockEntityToItem(BlockEntity te, ItemStack stack, HolderLookup.Provider provider) {
        CompoundTag compoundtag = te.saveCustomOnly(provider);
        te.removeComponentsFromTag(compoundtag);
        BlockItem.setBlockEntityData(stack, te.getType(), compoundtag);
        stack.applyComponents(te.collectComponents());
    }
}
