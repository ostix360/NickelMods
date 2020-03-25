package fr.ostix.nickelm.tileEntity;

import fr.ostix.nickelm.utils.Refs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegister {
    public static void TileEntityRegister() {
        GameRegistry.registerTileEntity(TileEntityNickelFurnace.class,new ResourceLocation(Refs.MODID+":nickel_furnace"));
        GameRegistry.registerTileEntity(TileEntityUpgradeTool.class,new ResourceLocation(Refs.MODID+":upgrade_tool"));
        GameRegistry.registerTileEntity(TileEntityNickelChest.class,new ResourceLocation(Refs.MODID+":nickel_chest"));

    }
}
