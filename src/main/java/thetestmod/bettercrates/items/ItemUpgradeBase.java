package thetestmod.bettercrates.items;

import com.mojang.logging.LogUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import org.slf4j.Logger;
import thetestmod.bettercrates.tile.TileEntityBase;

import java.util.function.Consumer;

public class ItemUpgradeBase extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();

    private Block block;
    private BlockState state;

    public ItemUpgradeBase(Block block, BlockState state, ResourceKey<Item> key) {
        super(new Properties().setId(key));
        this.block = block;
        this.state = state;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide && context.getLevel().getBlockState(context.getClickedPos()).getBlock() == block) {
            BlockEntity te = context.getLevel().getBlockEntity(context.getClickedPos());
            if (te != null) {
                try (ProblemReporter.ScopedCollector problemreporter$scopedcollector = new ProblemReporter.ScopedCollector(te.problemPath(), LOGGER)) {
                    TagValueOutput tagValueOutput = TagValueOutput.createWithContext(problemreporter$scopedcollector, context.getLevel().registryAccess());

                    NonNullList<ItemStack> inventory = te instanceof ChestBlockEntity ? ((ChestBlockEntity) te).items : ((TileEntityBase) te).getInv();
                    ContainerHelper.saveAllItems(tagValueOutput, inventory);
                    inventory.clear();

                    ValueInput tagValueInput = TagValueInput.create(problemreporter$scopedcollector, context.getLevel().registryAccess(), tagValueOutput.buildResult());

                    context.getLevel().setBlock(context.getClickedPos(), state, context.getLevel().isClientSide ? 11 : 3);
                    TileEntityBase teNew = (TileEntityBase) context.getLevel().getBlockEntity(context.getClickedPos());
                    ContainerHelper.loadAllItems(tagValueInput, teNew.getInv());
                    context.getItemInHand().shrink(1);
                    context.getPlayer().playSound(SoundEvents.ENDER_CHEST_OPEN, 1.0F, 1.0F);
                    context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.ANVIL_PLACE, SoundSource.PLAYERS, 0.4F, 0.8F);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(Component.translatable(getDescriptionId() + ".tooltip"));
        tooltipAdder.accept(Component.translatable("item.up.tooltip_base"));
    }
}
