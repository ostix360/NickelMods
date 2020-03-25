package fr.ostix.nickelm.items;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.item.Item;

public class NickelItem extends Item
{
    public NickelItem(String name) {

        setRegistryName(Refs.MODID,name);
        setUnlocalizedName(name);
        setCreativeTab(NickelMod.nickeltab);

        ModItem.INSTANCE.getItems().add(this);
    }
}
