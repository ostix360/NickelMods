package fr.ostix.nickelm.init;

import fr.ostix.nickelm.itemArmor.*;
import fr.ostix.nickelm.itemTool.*;
import fr.ostix.nickelm.items.NickelItem;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ModItem {

    public static final ModItem INSTANCE = new ModItem();

    public static Item nickel_ingots, ruby_ingots, malachite_ingots, plati_ingots, nickel_hoe, ruby_hoe, malachite_hoe, plati_hoe;
    public static ItemArmor nickel_helmet, nickel_chestplate, nickel_leggings, nickel_boots, ruby_helmet, ruby_chestplate, ruby_leggings, ruby_boots, malachite_helmet, malachite_chestplate, malachite_leggings, malachite_boots, plati_helmet, plati_chestplate, plati_leggings, plati_boots;
    public static ItemTool nickel_axe, nickel_pickaxe, nickel_shovel, ruby_axe, ruby_pickaxe, ruby_shovel, malachite_axe, malachite_pickaxe, malachite_shovel, plati_axe, plati_pickaxe, plati_shovel;
    public static ItemSword nickel_sword, ruby_sword, plati_sword, malachite_sword;
    public static ItemTool nickel_axe1, nickel_pickaxe1, nickel_axe2,nickel_pickaxe2;
    public static ItemSword nickel_sword1;


    private static List<Item> items;

    public static void init() {

        items = new ArrayList<>();
        ruby_ingots = new NickelItem("ruby_ingots");
        malachite_ingots = new NickelItem("malachite_ingots");
        plati_ingots = new NickelItem("plati_ingots");
        nickel_ingots = new NickelItem("nickel_ingots");

        ruby_helmet = new NickelMHelmet("ruby_helmet", ArmorMaterials.ruby_material, 3, EntityEquipmentSlot.HEAD);
        ruby_chestplate = new NickelMChestplate("ruby_chestplate", ArmorMaterials.ruby_material, 3, EntityEquipmentSlot.CHEST);
        ruby_leggings = new NickelMLeggings("ruby_leggings", ArmorMaterials.ruby_material, 3, EntityEquipmentSlot.LEGS);
        ruby_boots = new NickelMBoots("ruby_boots", ArmorMaterials.ruby_material, 3, EntityEquipmentSlot.FEET);

        malachite_helmet = new NickelMHelmet("malachite_helmet", ArmorMaterials.malachite_material, 3, EntityEquipmentSlot.HEAD);
        malachite_chestplate = new NickelMChestplate("malachite_chestplate", ArmorMaterials.malachite_material, 3, EntityEquipmentSlot.CHEST);
        malachite_leggings = new NickelMLeggings("malachite_leggings", ArmorMaterials.malachite_material, 3, EntityEquipmentSlot.LEGS);
        malachite_boots = new NickelMBoots("malachite_boots", ArmorMaterials.malachite_material, 3, EntityEquipmentSlot.FEET);

        plati_helmet = new NickelMHelmet("plati_helmet", ArmorMaterials.plati_material, 3, EntityEquipmentSlot.HEAD);
        plati_chestplate = new NickelMChestplate("plati_chestplate", ArmorMaterials.plati_material, 3, EntityEquipmentSlot.CHEST);
        plati_leggings = new NickelMLeggings("plati_leggings", ArmorMaterials.plati_material, 3, EntityEquipmentSlot.LEGS);
        plati_boots = new NickelMBoots("plati_boots", ArmorMaterials.plati_material, 3, EntityEquipmentSlot.FEET);

        nickel_helmet = new NickelHelmet("nickel_helmet", ArmorMaterials.nickel_material, 3, EntityEquipmentSlot.HEAD);
        nickel_chestplate = new NickelChestplate("nickel_chestplate", ArmorMaterials.nickel_material, 3, EntityEquipmentSlot.CHEST);
        nickel_leggings = new NickelLeggings("nickel_leggings", ArmorMaterials.nickel_material, 3, EntityEquipmentSlot.LEGS);
        nickel_boots = new NickelBoots("nickel_boots", ArmorMaterials.nickel_material, 3, EntityEquipmentSlot.FEET);

        nickel_axe = new NickelMAxeLevel(Tools.nickel);
        nickel_pickaxe = new NickelMPickaxeLevel( Tools.nickel);
        nickel_sword = new NickelMSword("nickel_sword", Tools.nickel);
        nickel_hoe = new NickelMHoe("nickel_hoe", Tools.nickel);
        nickel_shovel = new NickelMShovel("nickel_shovel", Tools.nickel);

        plati_axe = new NickelMAxe("plati_axe", 8.0F, 3.0F, Tools.plati);
        plati_pickaxe = new NickelMPickaxe("plati_pickaxe", Tools.plati);
        plati_sword = new NickelMSword("plati_sword", Tools.plati);
        plati_hoe = new NickelMHoe("plati_hoe", Tools.plati);
        plati_shovel = new NickelMShovel("plati_shovel", Tools.plati);

        ruby_axe = new NickelMAxe("ruby_axe", 5.0F, 3.0F, Tools.ruby);
        ruby_pickaxe = new NickelMPickaxe("ruby_pickaxe", Tools.ruby);
        ruby_sword = new NickelMSword("ruby_sword", Tools.ruby);
        ruby_hoe = new NickelMHoe("ruby_hoe", Tools.ruby);
        ruby_shovel = new NickelMShovel("ruby_shovel", Tools.ruby);

        malachite_axe = new NickelMAxe("malachite_axe", 10.0F, 3.0F, Tools.malachite);
        malachite_pickaxe = new NickelMPickaxe("malachite_pickaxe", Tools.malachite);
        malachite_sword = new NickelMSword("malachite_sword", Tools.malachite);
        malachite_hoe = new NickelMHoe("malachite_hoe", Tools.malachite);
        malachite_shovel = new NickelMShovel("malachite_shovel", Tools.malachite);

        nickel_axe1 = new NickelMAxeLevel(Tools.nickel, true);
        nickel_pickaxe1 = new NickelMPickaxeLevel(Tools.nickel1,true);
        //nickel_sword1 = new NickelMSwordLevel(Tools.nickel1);

        nickel_axe2 = new NickelMAxeLevel(Tools.nickel1, true, true);
        nickel_pickaxe2 = new NickelMPickaxeLevel(Tools.nickel1,true,true);

    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent e) {
        items.forEach(this::registerModel);
    }

    private void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Refs.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
    }

    public static List<Item> getItems() {
        return items;
    }

    public static class ArmorMaterials {
        static final ItemArmor.ArmorMaterial plati_material = EnumHelper.addArmorMaterial("plati_material", Refs.MODID + ":plati_armor", 250, new int[]{5, 12, 8, 6}, 9, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);
        static final ItemArmor.ArmorMaterial malachite_material = EnumHelper.addArmorMaterial("malachite_material", Refs.MODID + ":malachite_armor", 100, new int[]{4, 7, 7, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);
        static final ItemArmor.ArmorMaterial ruby_material = EnumHelper.addArmorMaterial("ruby_material", Refs.MODID + ":ruby_armor", 150, new int[]{3, 8, 6, 5}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);
        static final ItemArmor.ArmorMaterial nickel_material = EnumHelper.addArmorMaterial("nickel_material", Refs.MODID + ":nickel_armor", 500, new int[]{6, 14, 10, 7}, 6, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f);
    }

    public static class Tools {
        static final Item.ToolMaterial nickel1 = EnumHelper.addToolMaterial("nickel1", 3, 2500, 43.75F, 12.5F, 14);
        static final Item.ToolMaterial nickel = EnumHelper.addToolMaterial("nickel", 3, 2000, 35.0F, 10.0F, 11);
        static final Item.ToolMaterial plati = EnumHelper.addToolMaterial("plati", 3, 1500, 20.0F, 8.0F, 5);
        static final Item.ToolMaterial ruby = EnumHelper.addToolMaterial("ruby", 3, 1200, 10.0F, 5.0F, 16);
        static final Item.ToolMaterial malachite = EnumHelper.addToolMaterial("malachite", 3, 1000, 15.0F, 6.5F, 9);
    }
}
