package fr.ostix.nickelm.blocks;

import fr.ostix.nickelm.NickelMod;
import fr.ostix.nickelm.init.ModBlock;
import fr.ostix.nickelm.tileEntity.TileEntityNickelFurnace;
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

import javax.annotation.Nullable;

public class NickelFurnace extends Block {
    public NickelFurnace(String name,Material materialIn) {
        super(materialIn);
        setUnlocalizedName(name).setRegistryName(name).setCreativeTab(NickelMod.nickeltab);

        ModBlock.INSTANCE.getBlocks().add(this);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityNickelFurnace();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        if (tileEntity instanceof TileEntityNickelFurnace)
        {
            InventoryHelper.dropInventoryItems(worldIn,pos,(TileEntityNickelFurnace)tileEntity);
        }

        super.breakBlock(worldIn, pos, state);
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
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof TileEntityNickelFurnace)
        {
            ((TileEntityNickelFurnace)tile).setCustomName(stack.getDisplayName());
        }

    }
}
