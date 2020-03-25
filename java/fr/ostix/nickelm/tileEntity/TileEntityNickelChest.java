package fr.ostix.nickelm.tileEntity;

import fr.ostix.nickelm.container.ContainerNickelChest;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.FMLLog;

public class TileEntityNickelChest extends TileEntityLockableLoot implements ITickable {

    private NonNullList<ItemStack> contents = NonNullList.<ItemStack>withSize(27, ItemStack.EMPTY);
    public int numPlayersUsing, ticksSinceSync;
    public float lidAngle, prevLidAngle;
    //private String cunstomName;
    //private NickelChestType type;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.contents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = compound.getString("CustomName");
        }

        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, contents);
        }
    }

    public TileEntityNickelChest() {
        FMLLog.log.debug("Constructeur du tileEntity", Refs.MODID);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (compound.hasKey("CustomName",Constants.NBT.TAG_STRING)) {
            compound.setString("CustomName", this.customName);
        }

        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, contents);
        }

        return compound;

    }

    @Override
    public int getSizeInventory() {
        return this.contents.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.contents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }


    @Override
    public int getInventoryStackLimit() {
        return 64;
    }


    @Override
    public void openInventory(EntityPlayer player)
    {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(pos,this.getBlockType(),1,this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos,this.getBlockType(),false);

    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        --this.numPlayersUsing;
        this.world.addBlockEvent(pos,this.getBlockType(),1,this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos,this.getBlockType(),false);
    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.contents;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "title.nickel_chest";
    }


    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerNickelChest(this, playerInventory, playerIn);
    }

    @Override
    public String getGuiID() {
        return Refs.MODID + ":nickel_chest";
    }


    @Override
    public void update() {

    }


}