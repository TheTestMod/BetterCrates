package thetestmod.bettercrates.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.init.ContainerRegistry;
import thetestmod.bettercrates.tile.TileEntityBase;

public class ContainerBase13Rows extends ContainerBase {

    public ContainerBase13Rows(int id, PlayerInventory playerInv, PacketBuffer buffer) {
        this(id, playerInv, buffer.readBlockPos(), EnumCrate.VALUES[buffer.readInt()]);
    }

    public ContainerBase13Rows(int id, PlayerInventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(ContainerRegistry.CONTAINER_13_ROWS, id, (TileEntityBase) playerInv.player.level.getBlockEntity(pos), playerInv, pos, enumCrate);
    }
}
