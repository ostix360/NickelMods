package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.item.ItemAxe;

public class NickelMAxe extends ItemAxe
{

    public NickelMAxe(String name,float damage,float attackspeed, ToolMaterial material)
    {
        super(material,damage,attackspeed);
        setUnlocalizedName(name).setRegistryName(name);
        setCreativeTab(NickelMod.nickeltab);

        ModItem.INSTANCE.getItems().add(this);
    }
}
