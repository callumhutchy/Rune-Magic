package callumhutchy.runemagic.utils.capability.interfaces;

public interface IExtendedPlayer {

	//Level functions
	public void increaseExperience(float points);
	
	public void setExperience(float exp);
	
	public float getExperience();
	
	public int getLevel();
		
	public void setLevel(int level);
	
	//Current spell functions
	public void setSpell(String spell);
	
	public String getSpell();
	
	
}
