package fr.ostix.nickelm.container;

import fr.ostix.nickelm.container.furnace.SlotOutput;
import fr.ostix.nickelm.tileEntity.TileEntityUpgradeTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerUpgradeTool extends Container {
    private TileEntityUpgradeTool tile;
    private int timePassed = 0;
    private static int sloty = 0;
    public ContainerUpgradeTool(TileEntityUpgradeTool tileEntityUpgradeTool, InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.tile = tileEntityUpgradeTool;
        this.addSlotToContainer(new Slot(tile,0,47,49+sloty));
        this.addSlotToContainer(new Slot(tile,1,22,30+sloty));
        this.addSlotToContainer(new Slot(tile,2,47,7+sloty));
        this.addSlotToContainer(new Slot(tile,3,72,30+sloty));
        this.addSlotToContainer(new Slot(tile,4,72,68+sloty));
        this.addSlotToContainer(new Slot(tile,5,47,93+sloty));
        this.addSlotToContainer(new Slot(tile,6,22,68+sloty));
        this.addSlotToContainer(new SlotOutput(tile,7,140,50+sloty));


        for (int y =0;y<3;y++)
        {
            for (int x=0;x<9;x++)
            {
                this.addSlotToContainer(new Slot(playerInventory,x+y*9+9,8+x*18,143+y*18));
            }
        }

        for (int x=0;x<9;++x)
        {
            this.addSlotToContainer(new Slot(playerInventory,x,8+x*18,201));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {

        return tile.isUsableByPlayer(playerIn);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); i++) {
            IContainerListener iContainerListener = this.listeners.get(i);

            if (this.timePassed != this.tile.getField(0)) {
                iContainerListener.sendWindowProperty(this, 0, this.tile.getField(0));
            }

        }


        this.timePassed = this.tile.getField(0);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tile.setField(id, data);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
