package thetestmod.bettercrates.container;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.BCRegistry;
import thetestmod.bettercrates.enums.EnumCrate;

public class ContainerBase13Rows extends ContainerBase {

    public ContainerBase13Rows(int id, Inventory playerInv, FriendlyByteBuf buffer) {
        this(id, playerInv, buffer.readBlockPos(), EnumCrate.VALUES[buffer.readInt()]);
    }

    public ContainerBase13Rows(int id, Inventory playerInv, BlockPos pos, EnumCrate enumCrate) {
        super(BCRegistry.CONTAINER_13_ROWS.get(), id, playerInv, pos, enumCrate);
    }
}
