package thetestmod.bettercrates.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import thetestmod.bettercrates.container.ContainerBase9Rows;

public class GuiBase9Rows extends GuiBase<ContainerBase9Rows> {

    public GuiBase9Rows(ContainerBase9Rows container, PlayerInventory playerInv, ITextComponent text) {
        super(container, playerInv, text);
    }
}
