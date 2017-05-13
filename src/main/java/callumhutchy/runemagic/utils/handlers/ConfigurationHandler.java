package callumhutchy.runemagic.utils.handlers;

import java.io.File;

import callumhutchy.runemagic.references.ModInfo;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration configuration;
	
	
	public static void init(File configFile){
		if(configuration == null){
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void OnConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.getModID().equals(ModInfo.MODID)){
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration(){
		
		
		if(configuration.hasChanged()){
			configuration.save();
		}
	}
	
}
