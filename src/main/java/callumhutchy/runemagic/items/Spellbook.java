package callumhutchy.runemagic.items;

import callumhutchy.runemagic.client.gui.GuiSpellbook;
import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Spellbook extends BasicMagicItem{

	public Spellbook(String unlocalisedName) {
		super(unlocalisedName);
		
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn)
    {
		player.openGui(ModInfo.MODID, GuiSpellbook.GUI_ID, world, 0, 0, 0);
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(handIn));
    }
	
}
