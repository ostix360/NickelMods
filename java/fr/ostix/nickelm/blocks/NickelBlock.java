package fr.ostix.nickelm.blocks;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NickelBlock extends Block {


    public NickelBlock(String name,Material materialIn,float hardness,float resistance,int harvesLevel,String harvestType)
    {
        super(materialIn);

        setRegistryName(name).setUnlocalizedName(name);

        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(harvestType,harvesLevel);
        setCreativeTab(NickelMod.nickeltab);


        ModBlock.INSTANCE.getBlocks().add(this);
    }


}
