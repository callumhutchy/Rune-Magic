package callumhutchy.runemagic.spells;

import java.util.ArrayList;

import callumhutchy.runemagic.items.Rune;
import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.references.spells.RuneCost;
import callumhutchy.runemagic.references.spells.Spell;
import net.minecraft.item.Item;

public class Spells {

	public static Spell fieryBlast;
	
	public static void init(){
		fieryBlast = new Spell(NameConstants.SPELL_FIERYBLAST, 2, 2, 3,addRuneToArray(runeCost("firerune",2)), null);
	}
	
	private static RuneCost runeCost(String runeName, int amount){
		RuneCost rune = new RuneCost((Rune) Item.getByNameOrId(ModInfo.MODID+":"+runeName),amount);
		return rune;
	}
	
	private static ArrayList<RuneCost> addRuneToArray(RuneCost... runeCost) {
		//System.out.println("Adding " + rune.toString());
		ArrayList<RuneCost> returnArray = new ArrayList<RuneCost>();
		for(RuneCost rune : runeCost){
			returnArray.add(rune);
		}
		return returnArray;
	}
	
}
