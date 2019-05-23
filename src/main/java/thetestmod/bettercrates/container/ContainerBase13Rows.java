package thetestmod.bettercrates.container;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.InventoryPlayer;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

@ChestContainer(isLargeChest = true, rowSize = 13)
public class ContainerBase13Rows extends ContainerBase {

    public ContainerBase13Rows(InventoryPlayer playerInv, TileEntityBase tile, EnumCrate enumCrate) {
        super(playerInv, tile, enumCrate);
    }
}
