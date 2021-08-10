package thetestmod.bettercrates.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.container.ContainerBase13Rows;

public class GuiBase13Rows extends GuiBase<ContainerBase13Rows> {

    public GuiBase13Rows(ContainerBase13Rows container, Inventory playerInv, Component text) {
        super(container, playerInv, text);
    }
}
