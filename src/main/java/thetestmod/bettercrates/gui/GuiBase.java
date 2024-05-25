package thetestmod.bettercrates.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
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
    protected void renderBg(PoseStack stack, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, enumCrate.getBackground());
        blit(stack, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(PoseStack stack, int p_97936_, int p_97937_) {
        drawCenteredString(stack, font, title, imageWidth / 2, enumCrate.getSize()[2], 0x404040);
    }

    @Override
    public void render(PoseStack p_97921_, int p_97922_, int p_97923_, float p_97924_) {
        this.renderBackground(p_97921_);
        super.render(p_97921_, p_97922_, p_97923_, p_97924_);
        this.renderTooltip(p_97921_, p_97922_, p_97923_);
    }
}
