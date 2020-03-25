package fr.ostix.nickelm.gui;

import fr.ostix.nickelm.container.ContainerUpgradeTool;
import fr.ostix.nickelm.tileEntity.TileEntityUpgradeTool;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiUpgradeTool extends GuiContainer {

    private static final ResourceLocation background = new ResourceLocation(Refs.MODID+":textures/gui/upgrade.png");
    private TileEntityUpgradeTool tile;
    public GuiUpgradeTool(TileEntityUpgradeTool tileEntity, InventoryPlayer inventory, EntityPlayer player) {
        super(new ContainerUpgradeTool(tileEntity,inventory,player));
        this.tile = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.ySize = 225;
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(background);
        System.out.println("creation du GUI du upgrading");
        System.out.println(this.ySize);
        this.drawTexturedModalRect(i, j+29, 0, 0, this.xSize, this.ySize);

        int timePassed = this.tile.getField(0);
        int textureWidth = (int) (32f / 150f * timePassed);
        this.drawTexturedModalRect(i + 101, j + 51+29, 177, 13, textureWidth, 15);


        this.fontRenderer.drawString(this.tile.getName(), i + 80, j + 45, 0xFFFFFF);


    }
}
