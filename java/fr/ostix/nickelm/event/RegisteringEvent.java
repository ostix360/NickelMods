package fr.ostix.nickelm.event;

import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.init.ModItem;
import fr.ostix.nickelm.tileEntity.TileEntityRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegisteringEvent
{
    @SubscribeEvent
    public void RegisterItem(RegistryEvent.Register<Item> e){
        ModItem.INSTANCE.init();
        e.getRegistry().registerAll(ModItem.INSTANCE.getItems().toArray(new Item[0]));
    }
    @SubscribeEvent
    public void RegisterBlock(RegistryEvent.Register<Block> e ){

        ModBlock.INSTANCE.init();
        e.getRegistry().registerAll(ModBlock.INSTANCE.getBlocks().toArray(new Block[0]));
        TileEntityRegister.TileEntityRegister();
    }

}
