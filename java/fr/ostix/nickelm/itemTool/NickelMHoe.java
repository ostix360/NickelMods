package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.item.ItemHoe;

public class NickelMHoe extends ItemHoe
{
    public NickelMHoe(String name, ToolMaterial materialIn)
    {
        super(materialIn);
        setUnlocalizedName(name).setRegistryName(name);
        setCreativeTab(NickelMod.nickeltab);
        ModItem.INSTANCE.getItems().add(this);
    }
}
