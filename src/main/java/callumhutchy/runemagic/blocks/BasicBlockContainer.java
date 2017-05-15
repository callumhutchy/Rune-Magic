package callumhutchy.runemagic.blocks;

import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicBlockContainer extends BlockContainer
{

	protected BasicBlockContainer(Material materialIn, String unlocalisedName) {
		super(materialIn);
		this.setUnlocalizedName(unlocalisedName);
		this.setRegistryName(ModInfo.MODID, unlocalisedName);
		this.setDefaultState(this.blockState.getBaseState());
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return null;
	}
	
}
