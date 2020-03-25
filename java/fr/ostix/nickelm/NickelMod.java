package fr.ostix.nickelm;


import fr.ostix.nickelm.ct.Nickeltab;
import fr.ostix.nickelm.event.RegisteringEvent;
import fr.ostix.nickelm.gen.OreGeneration;
import fr.ostix.nickelm.gui.GuiHandlerNickel;
import fr.ostix.nickelm.proxy.CommonProxy;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Refs.MODID,name = Refs.NAME,version = Refs.VERSION/*,acceptedMinecraftVersions = Refs.MCVERSION*/)
public class NickelMod
{
    @Mod.Instance(Refs.MODID)
    public static NickelMod instance;

    public static final CreativeTabs nickeltab = new Nickeltab("nickeltab");

    @SidedProxy(clientSide = Refs.CLIENT_PROXY ,serverSide = Refs.SERVER_PROXY )
    public static CommonProxy PROXY;

    public NickelMod()
    {
        MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        PROXY.preInit();
    }

    @Mod.EventHandler
    public void  init(FMLInitializationEvent e)
    {
        PROXY.init();
        GameRegistry.registerWorldGenerator(new OreGeneration(),0);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance,new GuiHandlerNickel());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){

        PROXY.postInit();
    }
}
