package thetestmod.bettercrates.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thetestmod.bettercrates.container.ContainerBase13Rows;
import thetestmod.bettercrates.container.ContainerBase9Rows;
import thetestmod.bettercrates.enums.EnumCrate;
import thetestmod.bettercrates.gui.GuiBase13Rows;
import thetestmod.bettercrates.gui.GuiBase9Rows;
import thetestmod.bettercrates.tile.TileEntityBase;

public class GuiHandler implements IGuiHandler {

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if (tile instanceof TileEntityBase) {
			if (ID >= 5) {
				return new ContainerBase13Rows(player.inventory, (TileEntityBase) tile, EnumCrate.VALUES[ID]);
			} else {
				return new ContainerBase9Rows(player.inventory, (TileEntityBase) tile, EnumCrate.VALUES[ID]);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		if (tile instanceof TileEntityBase) {
			if (ID >= 5) {
				return new GuiBase13Rows(player.inventory, (TileEntityBase) tile, EnumCrate.VALUES[ID]);
			} else {
				return new GuiBase9Rows(player.inventory, (TileEntityBase) tile, EnumCrate.VALUES[ID]);
			}
		}
		return null;
	}

}
