package fr.ostix.nickelm.entity.render;

import fr.ostix.nickelm.entity.EntityFrog;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderFrog extends RenderLiving<EntityFrog> {

    private ResourceLocation texture = new ResourceLocation(Refs.MODID,"textures/entity/frog/frogTexture.png");

    public RenderFrog(RenderManager rendermanagerIn) {
        super(rendermanagerIn,new ModelPig(),0.7f);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFrog entity) {
        return getEntityResourceLocation();
    }

    private ResourceLocation getEntityResourceLocation()
    {
        return texture;
    }


}
