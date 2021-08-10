package thetestmod.bettercrates.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import thetestmod.bettercrates.container.ContainerBase;
import thetestmod.bettercrates.enums.EnumCrate;

public class GuiBase<C extends ContainerBase> extends ContainerScreen<C> {

    private EnumCrate enumCrate;

    public GuiBase(C container, PlayerInventory playerInv, ITextComponent text) {
        super(container, playerInv, text);
        this.enumCrate = container.enumCrate;
        imageWidth = enumCrate.getSize()[0];
        imageHeight = enumCrate.getSize()[1];
    }

    @Override
    protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bind(enumCrate.getBackground());
        blit(p_230450_1_, leftPos, topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(MatrixStack matrix, int p_230451_2_, int p_230451_3_) {
        drawCenteredString(matrix, font, title, imageWidth / 2, enumCrate.getSize()[2], 0x404040);
    }

    @Override
    public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }
}
