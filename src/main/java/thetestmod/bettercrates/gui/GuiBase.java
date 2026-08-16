package thetestmod.bettercrates.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.container.ContainerBase;
import thetestmod.bettercrates.enums.EnumCrate;

public class GuiBase<C extends ContainerBase> extends AbstractContainerScreen<C> {

    private EnumCrate enumCrate;

    public GuiBase(C container, Inventory playerInv, Component text) {
        super(container, playerInv, text, container.enumCrate.getSize()[0], container.enumCrate.getSize()[1]);
        this.enumCrate = container.enumCrate;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, enumCrate.getBackground(), leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        graphics.centeredText(font, title, imageWidth / 2, enumCrate.getSize()[2], 0x404040);
    }
}
