package callumhutchy.runemagic.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Staff extends BasicMagicItem{

	public Staff(String unlocalisedName) {
		super(unlocalisedName);
		this.setMaxStackSize(1);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D(){
		return true;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		
		
		
		
		
		
		
		
		
        return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
	
	
	boolean creativeCasting(EntityPlayer player){
		return player.capabilities.isCreativeMode;
	}
	
	
	

}
