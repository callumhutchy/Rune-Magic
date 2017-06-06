package callumhutchy.runemagic.utils.capability;

import java.util.concurrent.Callable;

import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;

public class ExtendedPlayer implements IExtendedPlayer {

	//Levels
	
	public float experience = 0;
	public int level = 1;
	
	@Override
	public void increaseExperience(float points) {
		experience += points;
		while(experience >= (level * 10)){
			experience -= level * 10;
			level++;
		}
	}

	@Override
	public float getExperience() {
		return this.experience;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void setExperience(float exp) {
		this.experience = exp;
		
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		
	}
	
	//Current Spell
	
	private String currentSpell = "";
	
	@Override
	public void setSpell(String spell) {
		currentSpell = spell;
	}

	@Override
	public String getSpell() {
		return currentSpell;
	}




	

	

}
