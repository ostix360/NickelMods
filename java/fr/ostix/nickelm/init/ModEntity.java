package fr.ostix.nickelm.init;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.entity.EntityFrog;
import fr.ostix.nickelm.entity.render.RenderFrog;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntity
{
    private static int mobID =  0 ;
    public static void registerEntity(){

        EntityRegistry.registerModEntity(new ResourceLocation(Refs.MODID,"EntityFrog"), EntityFrog.class,"frog",mobID++, NickelMod.instance,60,3,true);
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityReners(){

        RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class,new RenderFrog(Minecraft.getMinecraft().getRenderManager()));
    }
}
