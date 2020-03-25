package fr.ostix.nickelm.tileEntity;

import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpgradeToolResult {
    private static final HashMap<ItemStack[],ItemStack> upgrade =new HashMap<ItemStack[],ItemStack>();
    static {
        addUpgrade(ModItem.nickel_axe,Item.getItemFromBlock(Blocks.GLOWSTONE),ModItem.nickel_ingots,Items.GUNPOWDER,Item.getItemFromBlock(ModBlock.malachite_block),Items.REDSTONE,ModItem.ruby_ingots,ModItem.nickel_axe1);
        addUpgrade(ModItem.nickel_pickaxe,Item.getItemFromBlock(ModBlock.nickel_block),Item.getItemFromBlock(ModBlock.malachite_ore),ModItem.plati_ingots, Items.GLOWSTONE_DUST,Item.getItemFromBlock(Blocks.OBSIDIAN),Items.DIAMOND,ModItem.nickel_pickaxe1);
    }
    private static void addUpgrade(Item ingrediant1, Item ingrediant2,Item ingrediant3, Item ingrediant4,Item ingrediant5, Item ingrediant6,Item ingrediant7, Item resultat1)
    {
        addUpgrade(new ItemStack(ingrediant1),new ItemStack(ingrediant2),new ItemStack(ingrediant3),new ItemStack(ingrediant4),new ItemStack(ingrediant5),new ItemStack(ingrediant6),new ItemStack(ingrediant7),new ItemStack(resultat1));
    }

    private static void addUpgrade(ItemStack ingrediant1, ItemStack ingrediant2,ItemStack ingrediant3, ItemStack ingrediant4, ItemStack ingrediant5, ItemStack ingrediant6, ItemStack ingrediant7, ItemStack resultat1)
    {
        upgrade.put(new ItemStack[]{ingrediant1,ingrediant2,ingrediant3,ingrediant4,ingrediant5,ingrediant6,ingrediant7},resultat1);
    }

    private static boolean areKeysEqual(ItemStack[] key1,ItemStack[] key2)
    {
        if (key1.length!=key2.length)
        {
            return false;
        }
        for (int i = 0; i < key1.length;i++)
        {
            ItemStack s1 = key1[i];
            ItemStack s2= key2[i];
            if (s1.isEmpty()&& !s2.isEmpty())return false;
            if (!s1.isEmpty()&& s2.isEmpty())return false;
            if (s1.getItem()!= s2.getItem())return false;
            if (s1.getItemDamage()!= s2.getItemDamage())return false;

        }
        return true;
    }

    public static ItemStack getUpgradeResult(ItemStack[] ingredients)
    {
        Iterator<Map.Entry<ItemStack[], ItemStack>> it = upgrade.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<ItemStack[], ItemStack> entry = it.next();
            if (areKeysEqual(entry.getKey(),ingredients))
            {
                return entry.getValue();
            }
        }
        return null;
    }


}
