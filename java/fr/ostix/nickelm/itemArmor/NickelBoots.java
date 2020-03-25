package fr.ostix.nickelm.itemArmor;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class NickelBoots extends ItemArmor {
    public NickelBoots(String name ,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        setRegistryName(name).setUnlocalizedName(name);
        setCreativeTab(NickelMod.nickeltab);

        ModItem.INSTANCE.getItems().add(this);
    }
}
