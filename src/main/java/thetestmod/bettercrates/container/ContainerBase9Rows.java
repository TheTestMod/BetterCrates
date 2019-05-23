package thetestmod.bettercrates.container;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.InventoryPlayer;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

@ChestContainer(isLargeChest = true, rowSize = 9)
public class ContainerBase9Rows extends ContainerBase {

    public ContainerBase9Rows(InventoryPlayer playerInv, TileEntityBase tile, EnumCrate enumCrate) {
        super(playerInv, tile, enumCrate);
    }
}
