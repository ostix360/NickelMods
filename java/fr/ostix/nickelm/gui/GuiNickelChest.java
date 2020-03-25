package fr.ostix.nickelm.gui;

import fr.ostix.nickelm.container.ContainerNickelChest;
import fr.ostix.nickelm.tileEntity.TileEntityNickelChest;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class GuiNickelChest extends GuiContainer
{

    public static final ResourceLocation GUI_CHEST = new ResourceLocation(Refs.MODID+":textures/gui/nickel_chest.png");
    private final InventoryPlayer inventoryPlayer;
    private final TileEntityNickelChest nickelChest;

    public GuiNickelChest(TileEntityNickelChest tileEntity, InventoryPlayer inventory, EntityPlayer player)
    {

        super(new ContainerNickelChest(tileEntity,inventory,player));
        FMLLog.log.log(Level.INFO,Refs.MODID+" Creation Du GUI");
        this.inventoryPlayer= inventory;
        this.nickelChest =tileEntity;
        ++this.ySize;
        //this.xSize =176;
        //this.ySize =168;

        /*System.out.println(this.guiLeft+ "=guiLeft");
        System.out.println(this.guiTop+ "=guiTop");*/

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(GUI_CHEST);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        //this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0,this.xSize,this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(this.nickelChest.getDisplayName().getFormattedText(),8,6,57008);
        this.fontRenderer.drawString(this.inventoryPlayer.getDisplayName().getUnformattedText(),8,this.ySize-92,57008);

    }
}
