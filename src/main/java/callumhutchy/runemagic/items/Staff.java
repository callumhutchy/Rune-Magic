package callumhutchy.runemagic.items;

import java.util.ArrayList;

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
	
	public static Rune[] addToArrayRune(Rune[] uniqueValues, Rune rune){
		Rune[] returnArray = new Rune[uniqueValues.length+1];
		for(int i = 0; i < uniqueValues.length; i++){
			returnArray[i] = uniqueValues[i];
		}
		returnArray[returnArray.length - 1] = rune;
		return returnArray;		
	}
	
	public static int[] addToArrayInt(int[] countValues, int newValue){
		int[] returnArray = new int[countValues.length+1];
		for(int i = 0; i < countValues.length; i++){
			returnArray[i] = countValues[i];
		}
		returnArray[returnArray.length-1] = newValue;
		return returnArray;
	}
	
	public void consumeAllSpellRunes(EntityPlayer entity, Spell spell) {
		ArrayList<Boolean> contain = new ArrayList<Boolean>();
		boolean elementRetrieval = false;
		boolean wasElementCasting = false;
		
		wasElementCasting = elementCasting(spell);
		System.out.println(wasElementCasting);
		for (Rune i : spell.getRuneCost()) {
			System.out.println(i.getUnlocalizedName());
			System.out.println(spell.getSpellElement().getRune().getUnlocalizedName());
			
			/*if(!elementCasting(spell)){
				entity.inventory.consumeInventoryItem(i);
			}else if(elementCasting(spell) && i != staffElement.getRune()){
				entity.inventory.consumeInventoryItem(i);
			}*/
			
			
			

		}
	}

}
