package fr.ostix.nickelm.container;

import fr.ostix.nickelm.container.furnace.SlotOutput;
import fr.ostix.nickelm.container.furnace.SlotSingleItem;
import fr.ostix.nickelm.tileEntity.TileEntityNickelFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerNickelFurnace extends Container {
    private TileEntityNickelFurnace tile;
    private int timePassed = 0;
    private int burnTimeLeft = 0;

    public ContainerNickelFurnace(TileEntityNickelFurnace tileEntityNickelFurnace, InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        this.tile = tileEntityNickelFurnace;
        for (int i=0;i<2;i++)
        {
            this.addSlotToContainer(new Slot(tile,i,33+i*18,7));
        }
        for (int i=0;i<2;i++)
        {
            this.addSlotToContainer(new SlotSingleItem(tile,i+2,42,40+i*18,i==0? Item.getItemFromBlock(Blocks.PLANKS): Items.WHEAT));
        }
        this.addSlotToContainer(new SlotOutput(tile,4,116,17));

        for (int y =0;y<3;y++)
        {
            for (int x=0;x<9;x++)
            {
                this.addSlotToContainer(new Slot(playerInventory,x+y*9+9,8+x*18,84+y*18));
            }
        }

        for (int x=0;x<9;++x)
        {
            this.addSlotToContainer(new Slot(playerInventory,x,8+x*18,142));
        }

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return tile.isUsableByPlayer(playerIn);
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this,this.tile);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0;i<this.listeners.size();i++)
        {
            IContainerListener iContainerListener= (IContainerListener) this.listeners.get(i);
            if (this.burnTimeLeft != this.tile.getField(0))
            {
                iContainerListener.sendWindowProperty(this,0,this.tile.getField(0));
            }

            if (this.timePassed != this.tile.getField(1))
            {
                iContainerListener.sendWindowProperty(this,1,this.tile.getField(1));
            }

        }

        this.burnTimeLeft = this.tile.getField(0);
        this.timePassed = this.tile.getField(1);
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
