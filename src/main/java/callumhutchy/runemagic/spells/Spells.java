package callumhutchy.runemagic.spells;

import java.util.ArrayList;

import callumhutchy.runemagic.items.Rune;
import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.references.spells.RuneCost;
import callumhutchy.runemagic.references.spells.Spell;
import net.minecraft.item.Item;

public class Spells {

	public static Spell fieryBlast, heal, earthPillar, icePillar, meteor, vampiricTouch, regenerate, flameWall,shock,fieryTouch;
	
	private static ArrayList<Spell> spells = new ArrayList<Spell>();
	
	public static void init(){
		fieryBlast = new Spell(NameConstants.SPELL_FIERYBLAST, 2, 2, 3,addRuneToArray(runeCost("firerune",2)), Elements.fire,"Shoot a fiery blast at target.");
		heal = new Spell(NameConstants.SPELL_HEAL,1,2,3,addRuneToArray(runeCost("naturerune",1)), null,"Heals the user for a small amount.");
		earthPillar = new Spell(NameConstants.SPELL_EARTHPILLAR, 4, 3, 0, addRuneToArray(runeCost("earthrune",2)), Elements.earth, "Creates a pillar of earth from the ground.");
		icePillar = new Spell(NameConstants.SPELL_ICEPILLAR, 5,4,0, addRuneToArray(runeCost("waterrune",2)),Elements.water, "Creates a pillar of ice from the ground.");
		meteor = new Spell(NameConstants.SPELL_METEOR, 3, 3, 5, addRuneToArray(runeCost("firerune",3), runeCost("earthrune",1)), Elements.fire,"Summons a meteor to target location.");
		vampiricTouch = new Spell(NameConstants.SPELL_VAMPIRICTOUCH, 7 , 4, 3, addRuneToArray(runeCost("bloodrune",3)), null,"Your attacks steal health from your enemies.");
		regenerate = new Spell(NameConstants.SPELL_REGENERATE, 8, 4, 0, addRuneToArray(runeCost("bloodrune",1), runeCost("naturerune",1)),null,"Slowly regenerate your health over time.");
		flameWall = new Spell(NameConstants.SPELL_FLAMEWALL, 5,3, 0, addRuneToArray(runeCost("firerune",2)), Elements.fire,"Create a wall of flames in front of you.");
		shock = new Spell(NameConstants.SPELL_SHOCK, 6,4, 4, addRuneToArray(runeCost("airrune",2)),Elements.air,"Zap your target with lightning.");
		fieryTouch = new Spell(NameConstants.SPELL_FIERYTOUCH, 10, 6, 0, addRuneToArray(runeCost("firerune",3)), Elements.fire,"Your attacks set your foe on fire.");
	
		spells.add(fieryBlast);
		spells.add(heal);
		spells.add(earthPillar);
		spells.add(icePillar);
		spells.add(meteor);
		spells.add(vampiricTouch);
		spells.add(regenerate);
		spells.add(flameWall);
		spells.add(shock);
		spells.add(fieryTouch);
	
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
	
	public static Spell getSpellByName(String name){
		for(Spell spell : spells){
			if(spell.spellName == name){
				return spell;
			}
		}
		return null;
	}
	
	
}
