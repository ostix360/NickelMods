package fr.ostix.nickelm.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

    public static ModRecipes instance = new ModRecipes();


    public void initRecipes(){

        GameRegistry.addSmelting(ModBlock.ruby_ore,new ItemStack(ModItem.ruby_ingots,1),10.0f);
        GameRegistry.addSmelting(ModBlock.malachite_ore,new ItemStack(ModItem.malachite_ingots,1),10.0f);
        GameRegistry.addSmelting(ModBlock.plati_ore,new ItemStack(ModItem.plati_ingots,1),10.0f);
        GameRegistry.addSmelting(ModBlock.nickel_ore,new ItemStack(ModItem.nickel_ingots,1),10.0f);

    }
}
