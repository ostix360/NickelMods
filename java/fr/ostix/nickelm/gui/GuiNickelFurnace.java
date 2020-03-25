package fr.ostix.nickelm.gui;

import fr.ostix.nickelm.container.ContainerNickelFurnace;
import fr.ostix.nickelm.tileEntity.TileEntityNickelFurnace;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiNickelFurnace extends GuiContainer {
    private static final ResourceLocation background = new ResourceLocation(Refs.MODID+":textures/gui/nickel_furnace.png");
    private TileEntityNickelFurnace tile;

    public GuiNickelFurnace(TileEntityNickelFurnace tileEntity, InventoryPlayer inventory, EntityPlayer player)
    {
        super(new ContainerNickelFurnace(tileEntity,inventory,player));
        this.tile = tileEntity;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(background);
        System.out.println("creation du GUI du four");
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int timePassed = this.tile.getField(1);
        int textureWidth = (int) (23f / 200f * timePassed);
        this.drawTexturedModalRect(i + 81, j + 24, 177, 18, textureWidth, 7);

        if (this.tile.isBurning()) {
            int burningTime = this.tile.getField(0);
            int textureHeight = (int) (12f / this.tile.getFullBurnTime() * burningTime);
            this.drawTexturedModalRect(i + 37, j + 26 + 12 - textureHeight,
                    177, 12 - textureHeight, 27, textureHeight);
        }

        this.fontRenderer.drawString(this.tile.getName(), i + 80, j + 45, 0xFFFFFF);

    }
}
