package fr.ostix.nickelm.container.furnace;

import fr.ostix.nickelm.tileEntity.TileEntityNickelFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSingleItem extends Slot {
    private Item item;
    public SlotSingleItem(TileEntityNickelFurnace tile, int index, int xPosition, int yPosition, Item item) {
        super(tile,index,xPosition,yPosition);
        this.item = item;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.isEmpty()||stack.getItem()==item;
    }
}
