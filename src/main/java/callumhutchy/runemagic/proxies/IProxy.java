package callumhutchy.runemagic.proxies;

import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

public interface IProxy {

	void onPreInit(FMLPreInitializationEvent event);
	
	void onInit(FMLInitializationEvent event);
	
	void onPostInit(FMLPostInitializationEvent event);
	
	void onServerStarting(FMLServerStartingEvent event);
	
	void onServerStopping(FMLServerStoppingEvent event);
	
	default ClientProxy getClientProxy(){
		return null;
	}
	
	void openMyGui();
	
	default void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity){
		
	}
	
}
