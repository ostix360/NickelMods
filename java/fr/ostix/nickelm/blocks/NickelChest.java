package fr.ostix.nickelm.blocks;


import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.tileEntity.TileEntityNickelChest;
import fr.ostix.nickelm.utils.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import javax.annotation.Nullable;

public class NickelChest extends Block {



    public NickelChest(String name,Material materialIn) {
        super(materialIn);

        setRegistryName(name).setUnlocalizedName(name);


        FMLLog.log.debug(Refs.MODID+" Creation Du Block");
        setCreativeTab(NickelMod.nickeltab);


        ModBlock.INSTANCE.getBlocks().add(this);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            playerIn.openGui(NickelMod.instance,0,worldIn,pos.getX(),pos.getY(),pos.getZ());
            return true;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

        TileEntityNickelChest tileEntity = (TileEntityNickelChest) worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn,pos,tileEntity);
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityNickelChest)
        {
            ((TileEntityNickelChest)tile).setCustomName(stack.getDisplayName());
        }

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {

        FMLLog.log.debug(Refs.MODID+" Creation De La TileEntity via la Fonction de la class Block");
        return new TileEntityNickelChest();
    }



    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}



