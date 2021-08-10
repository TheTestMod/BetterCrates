package thetestmod.bettercrates.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.init.ContainerRegistry;
import thetestmod.bettercrates.tile.TileEntityBase;

public class ContainerBase13Rows extends ContainerBase {

    public ContainerBase13Rows(int id, Inventory playerInv, FriendlyByteBuf buffer) {
        this(id, playerInv, buffer.readBlockPos(), EnumCrate.VALUES[buffer.readInt()]);
    }

    public ContainerBase13Rows(int id, Inventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(ContainerRegistry.CONTAINER_13_ROWS, id, (TileEntityBase) playerInv.player.level.getBlockEntity(pos), playerInv, pos, enumCrate);
    }
}
