package thetestmod.bettercrates.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import thetestmod.bettercrates.BetterCrates;
import thetestmod.bettercrates.init.ItemsRegistry;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;
import java.util.List;

public class ItemUpgradeBase extends Item {

    private Block block;
    private BlockState state;

    public ItemUpgradeBase(String name, Block block, BlockState state) {
        super(new Properties().tab(BetterCrates.GROUP));
        setRegistryName(BetterCrates.MODID, name);
        this.block = block;
        this.state = state;
        ItemsRegistry.items.add(this);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if (!context.getLevel().isClientSide && context.getLevel().getBlockState(context.getClickedPos()).getBlock() == block) {
            TileEntity te = context.getLevel().getBlockEntity(context.getClickedPos());
            if (te != null) {
                CompoundNBT tag = new CompoundNBT();
                NonNullList<ItemStack> inventory = te instanceof ChestTileEntity ? ((ChestTileEntity) te).items : ((TileEntityBase) te).getInv();
                ItemStackHelper.saveAllItems(tag, inventory);
                inventory.clear();

                context.getLevel().setBlock(context.getClickedPos(), state, context.getLevel().isClientSide ? 11 : 3);
                TileEntityBase teNew = (TileEntityBase) context.getLevel().getBlockEntity(context.getClickedPos());
                ItemStackHelper.loadAllItems(tag, teNew.getInv());
                context.getPlayer().getItemInHand(context.getHand()).shrink(1);
                context.getPlayer().playSound(SoundEvents.ENDER_CHEST_OPEN, 1.0F, 1.0F);
                context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.ANVIL_PLACE, SoundCategory.PLAYERS, 0.4F, 0.8F);

            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(getDescriptionId() + ".tooltip"));
        tooltip.add(new TranslationTextComponent("item.up.tooltip_base"));
    }
}
