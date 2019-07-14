package thetestmod.bettercrates.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import thetestmod.bettercrates.container.ContainerBase13Rows;

public class GuiBase13Rows extends GuiBase<ContainerBase13Rows> {

    public GuiBase13Rows(ContainerBase13Rows container, PlayerInventory playerInv, ITextComponent text) {
        super(container, playerInv, text);
    }
}
