package thetestmod.bettercrates.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import thetestmod.bettercrates.container.ContainerBase;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.tile.TileEntityBase;

public class GuiBase extends GuiContainer {

    private EnumCrate enumCrate;

    public GuiBase(ContainerBase container, EnumCrate enumCrate) {
        super(container);
        this.enumCrate = enumCrate;
        xSize = enumCrate.getSize()[0];
        ySize = enumCrate.getSize()[1];
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(enumCrate.getBackground());
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(enumCrate.getTransKey());
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, ySize - enumCrate.getSize()[2], 0x404040);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

}
