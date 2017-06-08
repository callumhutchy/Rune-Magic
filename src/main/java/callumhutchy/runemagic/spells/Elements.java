package callumhutchy.runemagic.spells;

import callumhutchy.runemagic.items.Rune;
import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.spells.Element;
import net.minecraft.item.Item;

public class Elements {

	public static Element air,water,earth,fire;
	
	public static void init(){
		air = new Element("air", (Rune) Item.getByNameOrId(ModInfo.MODID+":"+"airrune"));
		water = new Element("water", (Rune) Item.getByNameOrId(ModInfo.MODID+":"+"waterrune"));
		earth = new Element("earth", (Rune) Item.getByNameOrId(ModInfo.MODID+":"+"earthrune"));
		fire = new Element("fire", (Rune) Item.getByNameOrId(ModInfo.MODID+":"+"firerune"));
	}
	
}
