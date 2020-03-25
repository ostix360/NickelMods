package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.item.ItemSword;

public class NickelMSwordLevel extends ItemSword
{
    public NickelMSwordLevel( ToolMaterial material)
    {
        super(material);
        setCreativeTab(NickelMod.nickeltab);

        ModItem.INSTANCE.getItems().add(this);
    }


}
