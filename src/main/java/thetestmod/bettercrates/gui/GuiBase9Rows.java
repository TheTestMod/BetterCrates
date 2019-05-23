package thetestmod.bettercrates.gui;

import net.minecraft.entity.player.InventoryPlayer;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

public class GuiBase9Rows extends GuiBase {

    public GuiBase9Rows(InventoryPlayer playerInv, TileEntityBase tile, EnumCrate enumCrate) {
        super(new ContainerBase9Rows(playerInv, tile, enumCrate), enumCrate);
    }
}
