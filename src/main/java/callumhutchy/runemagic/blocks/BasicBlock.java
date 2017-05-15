package callumhutchy.runemagic.blocks;

import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block
{

	public BasicBlock(Material materialIn, String unlocalisedName) {
		super(materialIn);
		this.setUnlocalizedName(unlocalisedName);
		this.setRegistryName(ModInfo.MODID, unlocalisedName);
		this.setDefaultState(this.blockState.getBaseState());
	}

}
