package callumhutchy.runemagic.items;

import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.item.Item;

public class BasicItem extends Item{
	public BasicItem(String unlocalisedName){
		this.setUnlocalizedName(unlocalisedName);
		this.setRegistryName(ModInfo.MODID, unlocalisedName);
	}
}
