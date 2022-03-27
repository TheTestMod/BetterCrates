package thetestmod.bettercrates.items;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUpgradeBase extends Item {

    private Block block;
    private BlockState state;

    public ItemUpgradeBase(Block block, BlockState state) {
        super(new Properties().tab(BetterCrates.GROUP));
        this.block = block;
        this.state = state;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getLevel().getBlockState(context.getClickedPos()).getBlock() == block) {
            BlockEntity te = context.getLevel().getBlockEntity(context.getClickedPos());
            if (te != null) {
                CompoundTag tag = new CompoundTag();
                NonNullList<ItemStack> inventory = te instanceof ChestBlockEntity ? ((ChestBlockEntity) te).items : ((TileEntityBase) te).getInv();
                ContainerHelper.saveAllItems(tag, inventory);
                inventory.clear();

                context.getLevel().setBlock(context.getClickedPos(), state, context.getLevel().isClientSide ? 11 : 3);
                TileEntityBase teNew = (TileEntityBase) context.getLevel().getBlockEntity(context.getClickedPos());
                ContainerHelper.loadAllItems(tag, teNew.getInv());
                context.getPlayer().getItemInHand(context.getHand()).shrink(1);
                context.getPlayer().playSound(SoundEvents.ENDER_CHEST_OPEN, 1.0F, 1.0F);
                context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 0.4F, 0.8F);

            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent(getDescriptionId() + ".tooltip"));
        tooltip.add(new TranslatableComponent("item.up.tooltip_base"));
    }
}
