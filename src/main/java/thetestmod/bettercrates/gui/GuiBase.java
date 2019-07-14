package thetestmod.bettercrates.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import thetestmod.bettercrates.container.ContainerBase;
import thetestmod.bettercrates.enums.EnumCrate;

public class GuiBase<C extends ContainerBase> extends ContainerScreen<C> {

    private EnumCrate enumCrate;

    public GuiBase(C container, PlayerInventory playerInv, ITextComponent text) {
        super(container, playerInv, text);
        this.enumCrate = container.enumCrate;
        xSize = enumCrate.getSize()[0];
        ySize = enumCrate.getSize()[1];
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1, 1, 1, 1);
        minecraft.getTextureManager().bindTexture(enumCrate.getBackground());
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(enumCrate.getTransKey());
        font.drawString(name, xSize / 2 - font.getStringWidth(name) / 2, ySize - enumCrate.getSize()[2], 0x404040);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
}
