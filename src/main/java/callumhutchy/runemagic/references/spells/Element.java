package callumhutchy.runemagic.references.spells;

import callumhutchy.runemagic.items.Rune;

public class Element {
	public String element;
	public Rune rune;
	
	public Element(String element, Rune rune){
		this.element = element;
		this.rune = rune;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Rune getRune() {
		return rune;
	}

	public void setRune(Rune rune) {
		this.rune = rune;
	}
	
	
}
