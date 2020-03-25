package fr.ostix.nickelm.blocks.chest;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ValidatingNChestSlot extends Slot
{
    private NickelChestType type;

    public ValidatingNChestSlot(IInventory inventoryIn, int index, int xPosition, int yPosition,NickelChestType type) {
        super(inventoryIn, index, xPosition, yPosition);

        this.type = type;
    }


}
