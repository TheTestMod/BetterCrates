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
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
        super(new Properties().group(BetterCrates.GROUP));
        setRegistryName(BetterCrates.MODID, name);
        this.block = block;
        this.state = state;
        ItemsRegistry.items.add(this);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote && context.getWorld().getBlockState(context.getPos()).getBlock() == block) {
            TileEntity te = context.getWorld().getTileEntity(context.getPos());
            if (te != null) {
                CompoundNBT tag = new CompoundNBT();
                NonNullList<ItemStack> inventory = te instanceof ChestTileEntity ? ((ChestTileEntity) te).chestContents : ((TileEntityBase) te).getInv();
                ItemStackHelper.saveAllItems(tag, inventory);
                inventory.clear();

                context.getWorld().setBlockState(context.getPos(), state);
                TileEntityBase teNew = (TileEntityBase) context.getWorld().getTileEntity(context.getPos());
                ItemStackHelper.loadAllItems(tag, teNew.getInv());
                context.getPlayer().getHeldItem(context.getHand()).shrink(1);
                context.getPlayer().playSound(SoundEvents.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);
                context.getPlayer().world.playSound(null, context.getPlayer().getPosition(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.4F, 0.8F);

            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(getTranslationKey() + ".tooltip"));
        tooltip.add(new TranslationTextComponent("item.up.tooltip_base"));
    }
}
