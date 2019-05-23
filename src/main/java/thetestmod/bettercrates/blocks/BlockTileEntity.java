package thetestmod.bettercrates.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockTileEntity extends BlockBase {

	public BlockTileEntity(String name, Material material) {
		super(name, material);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public abstract TileEntity createTileEntity(World world, IBlockState state);
}
