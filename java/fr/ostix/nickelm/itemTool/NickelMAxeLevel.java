package fr.ostix.nickelm.itemTool;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class NickelMAxeLevel extends ItemAxe {
    float damage;
    float attackspeed;
    byte level;
    public NickelMAxeLevel(ToolMaterial material,boolean level1,boolean level2,boolean level3) {
        super(material,15.0F,6.0F);
        this.level = 4;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_axe4").setRegistryName("nickel_axe4");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMAxeLevel(ToolMaterial material,boolean level1,boolean level2) {
        super(material,12.0F,4.0F);
        this.level = 3;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_axe3").setRegistryName("nickel_axe3");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMAxeLevel(ToolMaterial material, boolean level) {
        super(material,11.0F,3.3f);
        this.level = 2;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_axe2").setRegistryName("nickel_axe2");

        ModItem.INSTANCE.getItems().add(this);
    }

    public NickelMAxeLevel(ToolMaterial material) {
        super(material,10.0F,3.0F);
        this.level = 1;
        setCreativeTab(NickelMod.nickeltab);
        setUnlocalizedName("nickel_axe1").setRegistryName("nickel_axe1");

        ModItem.INSTANCE.getItems().add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("§bVotre Hach est de niveau : " + level);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
