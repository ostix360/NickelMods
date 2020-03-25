package fr.ostix.nickelm.proxy;

import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.init.ModEntity;
import fr.ostix.nickelm.init.ModItem;
import fr.ostix.nickelm.init.ModRecipes;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{
    public void preInit(){

        MinecraftForge.EVENT_BUS.register(ModItem.INSTANCE);
        MinecraftForge.EVENT_BUS.register(ModBlock.INSTANCE);
        ModEntity.registerEntity();
    }

    public void init(){

        ModRecipes.instance.initRecipes();

    }

    public void postInit(){


    }
}
