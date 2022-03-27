package thetestmod.bettercrates.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.container.ContainerBase9Rows;

public class GuiBase9Rows extends GuiBase<ContainerBase9Rows> {

    public GuiBase9Rows(ContainerBase9Rows container, Inventory playerInv, Component text) {
        super(container, playerInv, text);
    }
}
