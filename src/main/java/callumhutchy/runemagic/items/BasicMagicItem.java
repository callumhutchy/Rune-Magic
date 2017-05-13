package callumhutchy.runemagic.items;

import callumhutchy.runemagic.RuneMagic;

public class BasicMagicItem extends BasicItem {
	public BasicMagicItem(String unlocalisedName) {
		super(unlocalisedName);
		this.setCreativeTab(RuneMagic.tabRuneMagic);
	}
}
