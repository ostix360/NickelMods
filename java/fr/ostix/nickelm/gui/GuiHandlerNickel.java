package fr.ostix.nickelm.gui;

import fr.ostix.nickelm.container.ContainerNickelChest;
import fr.ostix.nickelm.container.ContainerNickelFurnace;
import fr.ostix.nickelm.container.ContainerUpgradeTool;
import fr.ostix.nickelm.tileEntity.TileEntityNickelChest;
import fr.ostix.nickelm.tileEntity.TileEntityNickelFurnace;
import fr.ostix.nickelm.tileEntity.TileEntityUpgradeTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandlerNickel implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        if (tileEntity instanceof TileEntityNickelChest)
        {
            return new ContainerNickelChest((TileEntityNickelChest)world.getTileEntity(new BlockPos(x,y,z)),player.inventory,player);

        }else if (tileEntity instanceof TileEntityNickelFurnace)
        {
            return new ContainerNickelFurnace((TileEntityNickelFurnace)world.getTileEntity(new BlockPos(x,y,z)),player.inventory,player);
        }else if (tileEntity instanceof TileEntityUpgradeTool)
        {
            return new ContainerUpgradeTool((TileEntityUpgradeTool)world.getTileEntity(new BlockPos(x,y,z)),player.inventory,player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));
        if (tileEntity instanceof TileEntityNickelChest)
        {
            return new GuiNickelChest((TileEntityNickelChest)tileEntity,player.inventory,player);
        }else if (tileEntity instanceof TileEntityNickelFurnace)
        {
            return new GuiNickelFurnace((TileEntityNickelFurnace)tileEntity,player.inventory,player);
        }else if (tileEntity instanceof TileEntityUpgradeTool)
        {
            return new GuiUpgradeTool((TileEntityUpgradeTool)tileEntity,player.inventory,player);
        }
        return null;
    }
}
