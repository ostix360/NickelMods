package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.item.ItemSword;

public class NickelMSword extends ItemSword
{
    public NickelMSword(String name,ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(name).setRegistryName(name);
        setCreativeTab(NickelMod.nickeltab);

        ModItem.INSTANCE.getItems().add(this);
    }


}
