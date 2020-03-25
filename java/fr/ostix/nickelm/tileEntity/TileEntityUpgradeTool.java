package fr.ostix.nickelm.tileEntity;

import fr.ostix.nickelm.container.ContainerUpgradeTool;
import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.init.ModItem;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

public class TileEntityUpgradeTool extends TileEntityLockable implements ITickable {
    private NonNullList<ItemStack> contents = NonNullList.withSize(8,ItemStack.EMPTY);
    private String customName;
    private int timePassed = 0 ;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.contents = NonNullList.withSize(this.getSizeInventory(),ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,this.contents);

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("CustomName");
        }
        this.timePassed = compound.getInteger("timePassed");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound,this.contents);

        if (this.hasCustomName())
        {
            compound.setString("CustomName",this.customName);
        }
        compound.setInteger("timePassed",this.timePassed);
        return compound;
    }


    @Override
    public int getSizeInventory() {
        return contents.size();
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
    public ItemStack getStackInSlot(int index) {
        return this.contents.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.contents,index,count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.contents,index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.contents.set(index,stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player
                .getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D
                        , (double) this.pos.getZ() + 0.5D) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == 0)
        {
            return  stack.getItem() == ModItem.nickel_pickaxe ||
                    stack.getItem() == ModItem.nickel_axe ||
                    stack.getItem() == ModItem.nickel_sword ||
                    stack.getItem() == Item.getItemFromBlock(ModBlock.nickel_furnace);

        }
        if (index == 7)
        {
            return false;
        }
        return true;
    }

    @Override
    public int getField(int id) {
        switch (id)
        {
            case 0:
                return this.timePassed;
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                this.timePassed = value;
        }

    }

    @Override
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void clear() {
        for (int i =0;i<this.contents.size();i++)
        {
            this.contents.set(i,ItemStack.EMPTY);
        }

    }

    @Override
    public void update() {
        if (!this.world.isRemote)
        {

            if (!this.isUpgrading()&& this.canUpgrade())
            {
                this.timePassed++;

            }

             if (this.isUpgrading()&& this.canUpgrade())
            {

                this.timePassed++;
                if (timePassed>=this.getFullRecipeTime())
                {
                    timePassed = 0;
                    this.upgrade();
                }
            }else{
                timePassed = 0;
            }
            this.markDirty();
        }

    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerUpgradeTool(this,playerInventory,playerIn);
    }

    @Override
    public String getGuiID() {
        return Refs.MODID+":upgrade_tool";
    }

    @Override
    public String getName() {
        return hasCustomName()?this.customName:"tile.upgrade_tool";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String displayName) {
        this.customName = displayName;
    }

    public boolean hasComponentEmpty()
    {
        return this.getStackInSlot(1).isEmpty() || this.getStackInSlot(2).isEmpty() || this.getStackInSlot(3).isEmpty() || this.getStackInSlot(4).isEmpty() || this.getStackInSlot(5).isEmpty() || this.getStackInSlot(6).isEmpty();
    }

    public ItemStack getUpgradeResult()
    {
        return UpgradeToolResult.getUpgradeResult(new ItemStack[]{
                this.getStackInSlot(0),this.getStackInSlot(1),this.getStackInSlot(2),this.getStackInSlot(3),this.getStackInSlot(4),
                this.getStackInSlot(5),this.getStackInSlot(6)});
    }

    public boolean canUpgrade()
    {
        ItemStack result = getUpgradeResult();

        if (result!= null)
        {
            ItemStack slot8 = this.getStackInSlot(7);

            if (slot8.isEmpty())
            {
                return true;
            }

            if (slot8.getItem() == result.getItem()&& slot8.getItemDamage()== result.getItemDamage())
            {
                int newStackeSize = slot8.getCount();
                if (newStackeSize <= this.getInventoryStackLimit()&& newStackeSize<= slot8.getMaxStackSize())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void upgrade()
    {
        ItemStack result = this.getUpgradeResult();

        this.decrStackSize(0,1);
        this.decrStackSize(1,1);
        this.decrStackSize(2,1);
        this.decrStackSize(3,1);
        this.decrStackSize(4,1);
        this.decrStackSize(5,1);
        this.decrStackSize(6,1);



        ItemStack stack4 = this.getStackInSlot(7);

        if (stack4.isEmpty())
        {
            this.setInventorySlotContents(7,result.copy());
        }else
        {
            stack4.setCount(stack4.getCount()+result.getCount());
        }
    }

    public int getFullRecipeTime()
    {
        return 150;
    }

    public boolean isUpgrading()
    {
        return timePassed >0;
    }

}
