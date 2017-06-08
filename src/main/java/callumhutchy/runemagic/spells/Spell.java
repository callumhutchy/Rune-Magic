package callumhutchy.runemagic.spells;

import java.util.ArrayList;

import callumhutchy.runemagic.items.Rune;

public class Spell {
	protected int spellExp;
	protected int spellLevelRequirment;
	protected float spellDamage;
	protected ArrayList<Rune> runeCost;
	protected Element spellElement;

	public Spell(int levelReq, int exp, int damage, ArrayList<Rune> rune, Element element) {
		this.setSpellLevelRequirment(levelReq);
		this.setSpellExp(exp);
		this.setSpellDamage(damage);
		this.setRuneCost(rune);
		this.setSpellElement(element);
	}
	
	
	public int getSpellExp() {
		return spellExp;
	}

	public void setSpellExp(int spellExp) {
		this.spellExp = spellExp;
	}

	public int getSpellLevelRequirment() {
		return spellLevelRequirment;
	}

	public void setSpellLevelRequirment(int spellLevelRequirment) {
		this.spellLevelRequirment = spellLevelRequirment;
	}

	public float getSpellDamage() {
		return spellDamage;
	}

	public void setSpellDamage(float spellDamage) {
		this.spellDamage = spellDamage;
	}

	public ArrayList<Rune> getRuneCost() {
		return runeCost;
	}

	public void setRuneCost(ArrayList<Rune> runeCost) {
		this.runeCost = runeCost;
	}

	public Element getSpellElement() {
		return spellElement;
	}

	public void setSpellElement(Element spellElement) {
		this.spellElement = spellElement;
	}

	

}
