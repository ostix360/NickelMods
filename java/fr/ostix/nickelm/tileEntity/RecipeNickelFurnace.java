package fr.ostix.nickelm.tileEntity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class RecipeNickelFurnace
{
    private static final HashMap<ItemStack[],ItemStack>recipes =new HashMap<ItemStack[],ItemStack>();
    static {
        addRecipe(Items.APPLE,Items.ARROW,Items.BAKED_POTATO);
    }

    private static void addRecipe(Item ingrediant1, Item ingrediant2, Item resultat1)
    {
        addRecipes(new ItemStack(ingrediant1),new ItemStack(ingrediant2),new ItemStack(resultat1));
    }

    private static void addRecipes(ItemStack ingrediant1,ItemStack ingrediant2,ItemStack resultat1)
    {
        recipes.put(new ItemStack[]{ingrediant1,ingrediant2},resultat1);
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

    public static ItemStack getRecipeResult(ItemStack[] ingredients)
    {
        Iterator<Map.Entry<ItemStack[], ItemStack>> it = recipes.entrySet().iterator();
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
