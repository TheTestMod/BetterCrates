package thetestmod.bettercrates.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import thetestmod.bettercrates.container.ContainerBase;
import thetestmod.bettercrates.enums.EnumCrate;

public class GuiBase<C extends ContainerBase> extends AbstractContainerScreen<C> {

    private EnumCrate enumCrate;

    public GuiBase(C container, Inventory playerInv, Component text) {
        super(container, playerInv, text);
        this.enumCrate = container.enumCrate;
        imageWidth = enumCrate.getSize()[0];
        imageHeight = enumCrate.getSize()[1];
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        graphics.blit(enumCrate.getBackground(), leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
        graphics.drawCenteredString(font, title, imageWidth / 2, enumCrate.getSize()[2], 0x404040);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        super.render(graphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}
