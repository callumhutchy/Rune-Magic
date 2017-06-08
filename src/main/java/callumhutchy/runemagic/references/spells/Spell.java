package callumhutchy.runemagic.references.spells;

import java.util.ArrayList;

import callumhutchy.runemagic.items.Rune;

public class Spell {
	public String spellName;
	

	protected int spellExp;
	protected int spellLevelRequirment;
	protected float spellDamage;
	protected ArrayList<RuneCost> runeCost;
	protected Element spellElement;

	
	/**
	 * 
	 * @param spellName
	 * @param levelReq
	 * @param exp
	 * @param damage
	 * @param runes
	 * @param element
	 */
	public Spell(String spellName, int levelReq, int exp, int damage, ArrayList<RuneCost> runes, Element element) {
		this.setSpellName(spellName);
		this.setSpellLevelRequirment(levelReq);
		this.setSpellExp(exp);
		this.setSpellDamage(damage);
		this.setRuneCost(runes);
		this.setSpellElement(element);
	}
	
	public String getSpellName() {
		return spellName;
	}


	private void setSpellName(String spellName) {
		this.spellName = spellName;
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

	public ArrayList<RuneCost> getRuneCost() {
		return runeCost;
	}

	public void setRuneCost(ArrayList<RuneCost> runeCost) {
		this.runeCost = runeCost;
	}

	public Element getSpellElement() {
		return spellElement;
	}

	public void setSpellElement(Element spellElement) {
		this.spellElement = spellElement;
	}

	

}

