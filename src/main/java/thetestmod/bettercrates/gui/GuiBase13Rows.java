package thetestmod.bettercrates.gui;

import net.minecraft.entity.player.InventoryPlayer;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

public class GuiBase13Rows extends GuiBase {

    public GuiBase13Rows(InventoryPlayer playerInv, TileEntityBase tile, EnumCrate enumCrate) {
        super(new ContainerBase13Rows(playerInv, tile, enumCrate), enumCrate);
    }
}
