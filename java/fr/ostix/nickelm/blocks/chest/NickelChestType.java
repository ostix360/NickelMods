package fr.ostix.nickelm.blocks.chest;

import fr.ostix.nickelm.tileEntity.TileEntityNickelChest;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum  NickelChestType implements IStringSerializable {

    NICKEL(48,5,true,"nickel_chest.png",TileEntityNickelChest.class,184,158);

    public final String name = this.name().toLowerCase();
    public final int size;
    public final int rowLength;
    public final boolean tieredChest;
    public final ResourceLocation modelTexture;
    public final Class<? extends TileEntityNickelChest> clazz;
    public final int xSize;
    public final int ySize;
    @Override
    public String getName() {
        return this.name;
    }

    NickelChestType(int Size, int rowLength, boolean tieredChest, String modelTexture, Class<? extends TileEntityNickelChest> clazz,int xSize,int ySize) {
        this.size=Size;
        this.rowLength = rowLength;
        this.tieredChest = tieredChest;
        this.modelTexture = new ResourceLocation(Refs.MODID, "textures/model/chest/" + modelTexture);
        this.clazz = clazz;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public Slot makeSlot(IInventory iInventory,int slotIndex,int x,int y) {
        return new ValidatingNChestSlot(iInventory,slotIndex,x,y,this);
    }
}
