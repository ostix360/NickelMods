package fr.ostix.nickelm.ct;

import fr.ostix.nickelm.init.ModItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class Nickeltab extends CreativeTabs {

    public Nickeltab(String label) {
        super(label);
    }



    @Override
    public boolean hasSearchBar() {
        return true;
    }
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItem.nickel_ingots);
    }
}
