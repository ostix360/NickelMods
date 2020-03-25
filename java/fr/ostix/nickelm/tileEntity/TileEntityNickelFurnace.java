package fr.ostix.nickelm.tileEntity;

import fr.ostix.nickelm.container.ContainerNickelFurnace;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityNickelFurnace extends TileEntityLockable implements ITickable
{
    private NonNullList<ItemStack> contents = NonNullList.withSize(5,ItemStack.EMPTY);
    private String customName;
    private int timePassed = 0,burningTimeLeft = 0;



    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.contents = NonNullList.withSize(this.getSizeInventory(),ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound,this.contents);

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("CustomName");
        }
        this.burningTimeLeft = compound.getInteger("burningTimeLeft");
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

        compound.setInteger("burningTimeLeft",this.burningTimeLeft);
        compound.setInteger("timePassed",this.timePassed);
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
    public void setInventorySlotContents(int index, ItemStack stack) {
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
        if (index == 2)
        {
            return OreDictionary.getOres("plankWood").contains(
                    new ItemStack(stack.getItem(),
                    OreDictionary.WILDCARD_VALUE));
        }
        if (index == 3)
        {
            return  stack.getItem() == Items.WHEAT;
        }
        return index != 4;
    }

    @Override
    public int getField(int id) {
        switch (id){
            case 0:
                return this.burningTimeLeft;
            case 1:
                return this.timePassed;
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        switch (id){
            case 0:
                this.burningTimeLeft = value;
            case 1:
                this.timePassed = value;
        }

    }

    @Override
    public int getFieldCount() {
        return 2;
    }

    @Override
    public void clear() {
        for (int i =0;i<this.contents.size();i++)
        {
            this.contents.set(i,ItemStack.EMPTY);
        }
    }

    @Override
    public void update()
    {
        if (!this.world.isRemote)
        {
            if (this.isBurning())
            {
                this.burningTimeLeft--;
            }

            if (!this.isBurning() && this.canSmelt()&&!this.hasFuelEmpty())
            {
                this.burningTimeLeft = this.getFullBurnTime();
                this.decrStackSize(2,1);
                this.decrStackSize(3,1);
            }

            if (this.isBurning()&& this.canSmelt())
            {
                this.timePassed++;
                if (timePassed>=this.getFullBurnTime())
                {
                    timePassed = 0;
                    this.smelt();
                }
            }else{
                timePassed = 0;
            }
            this.markDirty();
        }

    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerNickelFurnace(this, playerInventory, playerIn);
    }

    @Override
    public String getGuiID() {
        return Refs.MODID + ":nickel_furnace";
    }

    @Override
    public String getName() {
        return hasCustomName()?this.customName:"tile.nickel_furnace";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
    public boolean hasFuelEmpty()
    {
        return this.getStackInSlot(2).isEmpty() || this.getStackInSlot(3).isEmpty();
    }

    public ItemStack getRecipeResult()
    {
        return RecipeNickelFurnace.getRecipeResult(new ItemStack[]
                {this.getStackInSlot(0),this.getStackInSlot(1)});
    }

    public boolean canSmelt()
    {
        ItemStack result = getRecipeResult();

        if (result!= null)
        {
            ItemStack slot4 = this.getStackInSlot(4);

            if (slot4.isEmpty())
            {
                return true;
            }

            if (slot4.getItem() == result.getItem()&& slot4.getItemDamage()== result.getItemDamage())
            {
                int newStackeSize = slot4.getCount();
                if (newStackeSize <= this.getInventoryStackLimit()&& newStackeSize<= slot4.getMaxStackSize())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void smelt()
    {
        ItemStack result = this.getRecipeResult();

        this.decrStackSize(0,1);
        this.decrStackSize(1,1);

        ItemStack stack4 = this.getStackInSlot(4);

        if (stack4.isEmpty())
        {
            this.setInventorySlotContents(4,result.copy());
        }else
        {
            stack4.setCount(stack4.getCount()+result.getCount());
        }
    }

    public int getFullRecipeTime()
    {
        return 200;
    }

    public int getFullBurnTime()
    {
        return 300;
    }

    public boolean isBurning()
    {
        return burningTimeLeft > 0;
    }
}
