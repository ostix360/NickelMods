package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class NickelMPickaxeLevel extends ItemPickaxe
{
    byte level;

    public NickelMPickaxeLevel( ToolMaterial material,boolean level1,boolean level2,boolean level3)
    {
        super(material);
        this.level = 4;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_pickaxe4").setRegistryName("nickel_pickaxe4");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMPickaxeLevel( ToolMaterial material,boolean level1,boolean level2)
    {
        super(material);
        this.level = 3;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_pickaxe3").setRegistryName("nickel_pickaxe3");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMPickaxeLevel( ToolMaterial material,boolean level1)
    {
        super(material);
        this.level = 2;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_pickaxe2").setRegistryName("nickel_pickaxe2");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMPickaxeLevel( ToolMaterial material)
    {
        super(material);
        this.level = 1;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_pickaxe1").setRegistryName("nickel_pickaxe1");

        ModItem.INSTANCE.getItems().add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("§dVotre Pioche est de niveau : " + level);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
