package thetestmod.bettercrates.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.BCRegistry;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

public class ContainerBase9Rows extends ContainerBase {

    public ContainerBase9Rows(int id, Inventory playerInv, FriendlyByteBuf buffer) {
        this(id, playerInv, buffer.readBlockPos(), EnumCrate.VALUES[buffer.readInt()]);
    }

    public ContainerBase9Rows(int id, Inventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(BCRegistry.CONTAINER_9_ROWS.get(), id, (TileEntityBase) playerInv.player.level.getBlockEntity(pos), playerInv, pos, enumCrate);
    }
}
