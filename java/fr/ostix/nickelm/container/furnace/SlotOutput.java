package fr.ostix.nickelm.container.furnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotOutput extends Slot {
    public SlotOutput(TileEntity tile, int index, int xPosition, int yPosition) {
        super((IInventory) tile,index,xPosition,yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }
}
