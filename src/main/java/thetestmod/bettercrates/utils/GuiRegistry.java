package thetestmod.bettercrates.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thetestmod.bettercrates.container.ContainerCopperCrate;
import thetestmod.bettercrates.container.ContainerDiamondCrate;
import thetestmod.bettercrates.container.ContainerGoldenCrate;
import thetestmod.bettercrates.container.ContainerIronCrate;
import thetestmod.bettercrates.container.ContainerObsidianCrate;
import thetestmod.bettercrates.container.ContainerTinCrate;
import thetestmod.bettercrates.container.ContainerWoodenCrate;
import thetestmod.bettercrates.gui.GuiCopperCrate;
import thetestmod.bettercrates.gui.GuiDiamondCrate;
import thetestmod.bettercrates.gui.GuiGoldenCrate;
import thetestmod.bettercrates.gui.GuiIronCrate;
import thetestmod.bettercrates.gui.GuiObsidianCrate;
import thetestmod.bettercrates.gui.GuiTinCrate;
import thetestmod.bettercrates.gui.GuiWoodenCrate;
import thetestmod.bettercrates.tile.TileEntityCopperCrate;
import thetestmod.bettercrates.tile.TileEntityDiamondCrate;
import thetestmod.bettercrates.tile.TileEntityGoldenCrate;
import thetestmod.bettercrates.tile.TileEntityIronCrate;
import thetestmod.bettercrates.tile.TileEntityObsidianCrate;
import thetestmod.bettercrates.tile.TileEntityTinCrate;
import thetestmod.bettercrates.tile.TileEntityWoodenCrate;

public class GuiRegistry implements IGuiHandler {

	public static final int WOODEN_CRATE = 0;
	public static final int IRON_CRATE = 1;
	public static final int GOLDEN_CRATE = 2;
	public static final int DIAMOND_CRATE = 3;
	public static final int COPPER_CRATE = 4;
	public static final int TIN_CRATE = 5;
	public static final int OBSIDIAN_CRATE = 6;

	private Container Container;

	// stand
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {

		case WOODEN_CRATE:
			return new ContainerWoodenCrate(player.inventory,
					(TileEntityWoodenCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case IRON_CRATE:
			return new ContainerIronCrate(player.inventory,
					(TileEntityIronCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case GOLDEN_CRATE:
			return new ContainerGoldenCrate(player.inventory,
					(TileEntityGoldenCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case DIAMOND_CRATE:
			return new ContainerDiamondCrate(player.inventory,
					(TileEntityDiamondCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case COPPER_CRATE:
			return new ContainerCopperCrate(player.inventory,
					(TileEntityCopperCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case TIN_CRATE:
			return new ContainerTinCrate(player.inventory,
					(TileEntityTinCrate) world.getTileEntity(new BlockPos(x, y, z)));
		case OBSIDIAN_CRATE:
			return new ContainerObsidianCrate(player.inventory,
					(TileEntityObsidianCrate) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {

		case WOODEN_CRATE:
			return new GuiWoodenCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case IRON_CRATE:
			return new GuiIronCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case GOLDEN_CRATE:
			return new GuiGoldenCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case DIAMOND_CRATE:
			return new GuiDiamondCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case COPPER_CRATE:
			return new GuiCopperCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case TIN_CRATE:
			return new GuiTinCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		case OBSIDIAN_CRATE:
			return new GuiObsidianCrate(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
		default:
			return null;
		}
	}

}
