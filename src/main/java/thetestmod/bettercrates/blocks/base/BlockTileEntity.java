package thetestmod.bettercrates.blocks.base;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {

	public BlockTileEntity(String name, Material material, SoundType sound) {

		super(name, material, sound);

	}

	public abstract Class<TE> getTileEntityClass();

	public <T> T getTileEntity(IBlockAccess world, BlockPos pos) {

		return (T) world.getTileEntity(pos);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {

		return true;

	}

	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);

}
