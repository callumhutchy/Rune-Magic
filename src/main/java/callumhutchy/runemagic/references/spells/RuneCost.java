package callumhutchy.runemagic.references.spells;

import callumhutchy.runemagic.items.Rune;

public class RuneCost {
	Rune rune;
	int amount;
	
	public RuneCost(Rune rune, int amount){
		this.rune = rune;
		this.amount = amount;
	}

	public Rune getRune() {
		return rune;
	}

	public void setRune(Rune rune) {
		this.rune = rune;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
