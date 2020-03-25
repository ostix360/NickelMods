package fr.ostix.nickelm.init;

import com.google.common.collect.Lists;
import fr.ostix.nickelm.blocks.NickelBlock;
import fr.ostix.nickelm.blocks.NickelChest;
import fr.ostix.nickelm.blocks.NickelFurnace;
import fr.ostix.nickelm.blocks.UpgradeTool;
import fr.ostix.nickelm.blocks.chest.NickelChestType;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class ModBlock {
    public static final ModBlock INSTANCE = new ModBlock();
    public static final PropertyEnum<NickelChestType> VARIANT_PROP = PropertyEnum.create("variant",NickelChestType.class);


    public static Block nickel_ore,ruby_ore,malachite_ore,plati_ore,nickel_block,ruby_block,malachite_block,plati_block, nickel_chest;
    public static Block nickel_furnace,upgrade_tool;
    private List<Block> blocks;

    public void init() {

        blocks = Lists.newArrayList();

        nickel_block = new NickelBlock("nickel_block",Material.IRON,8.0F,22.0F,3,"pickaxe");
        ruby_block = new NickelBlock("ruby_block",Material.IRON,4.0F,12.0F,3,"pickaxe");
        malachite_block = new NickelBlock("malachite_block",Material.IRON,2.0F,10.0F,3,"pickaxe");
        plati_block = new NickelBlock("plati_block",Material.IRON,7.0F,15.0F,3,"pickaxe");

        nickel_ore = new NickelBlock("nickel_ore", Material.IRON, 15.0f, 19.0f, 3, "pickaxe");
        ruby_ore = new NickelBlock("ruby_ore", Material.IRON, 8.0f, 10.0f, 3, "pickaxe");
        malachite_ore = new NickelBlock("malachite_ore", Material.IRON, 5.0f, 8.0f, 3, "pickaxe");
        plati_ore= new NickelBlock("plati_ore", Material.IRON, 12.0f, 13.0f, 3, "pickaxe");
        nickel_chest = new NickelChest("nickel_chest",Material.IRON);
        nickel_furnace = new NickelFurnace("nickel_furnace",Material.ROCK);
        upgrade_tool = new UpgradeTool("upgrade_tool",Material.ANVIL);

        for (Block block : blocks) {
            ItemBlock ib = new ItemBlock(block);
            ib.setRegistryName(block.getRegistryName());
            GameRegistry.findRegistry(Item.class).register(ib);
        }
    }

    @SubscribeEvent
    public void RegisterModels (ModelRegistryEvent e)
    {
        blocks.forEach(this::registerModel);
    }

    private void registerModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),0,new ModelResourceLocation(new ResourceLocation(Refs.MODID,block.getUnlocalizedName().substring(5)),"inventory"));
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
