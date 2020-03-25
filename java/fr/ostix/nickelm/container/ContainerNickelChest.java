package fr.ostix.nickelm.container;

import fr.ostix.nickelm.tileEntity.TileEntityNickelChest;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class ContainerNickelChest extends Container
{
    private final int numRows;
    private final TileEntityNickelChest tileEntityNickelChest;
    public ContainerNickelChest(TileEntityNickelChest tileEntity, InventoryPlayer inventory,EntityPlayer player)
    {
        FMLLog.log.log(Level.INFO,Refs.MODID+" Creation Du Container");
        this.tileEntityNickelChest = tileEntity;
        this.numRows = tileEntity.getSizeInventory()/9;
        tileEntity.openInventory(player);

        for (int i1 = 0;i1<this.numRows;++i1)
        {
            for (int i = 0 ; i <9;++i)
            {
                this.addSlotToContainer(new Slot(tileEntity,i+i1*9,8+i*18,18+i1*18));
            }
        }

        for (int y =0;y<3;y++)
        {
            for (int x=0;x<9;x++)
            {
                this.addSlotToContainer(new Slot(inventory,x+y*9+9,8+x*18,86+y*18));
            }
        }

        for (int x=0;x<9;++x)
        {
            this.addSlotToContainer(new Slot(inventory,x,8+x*18,144));
        }


    }



    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntityNickelChest.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        tileEntityNickelChest.closeInventory(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot!= null&&slot.getHasStack())
        {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index < this.numRows* 9)
            {
                if (!this.mergeItemStack(itemStack1,this.numRows*9,this.inventorySlots.size(),true));
                {
                    return ItemStack.EMPTY;
                }
            }else if (this.mergeItemStack(itemStack1,0,this.numRows*9,false))
            {
                return ItemStack.EMPTY;

            }
            if (itemStack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }else
            {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    public TileEntityNickelChest getTileEntityNickelChest() {
        return tileEntityNickelChest;
    }
}
