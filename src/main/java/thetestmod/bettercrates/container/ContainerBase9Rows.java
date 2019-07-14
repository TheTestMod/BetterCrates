package thetestmod.bettercrates.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.init.ContainerRegistry;
import thetestmod.bettercrates.tile.TileEntityBase;

import javax.annotation.Nullable;

public class ContainerBase9Rows extends ContainerBase {

    public ContainerBase9Rows(int id, PlayerInventory playerInv, PacketBuffer buffer) {
        this(id, playerInv, buffer.readBlockPos(), EnumCrate.VALUES[buffer.readInt()]);
    }

    public ContainerBase9Rows(int id, PlayerInventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(ContainerRegistry.CONTAINER_9_ROWS, id, (TileEntityBase) playerInv.player.world.getTileEntity(pos), playerInv, pos, enumCrate);
    }
}
