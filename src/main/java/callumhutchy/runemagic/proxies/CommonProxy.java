package callumhutchy.runemagic.proxies;

import callumhutchy.runemagic.blocks.Blocks;
import callumhutchy.runemagic.blocks.models.tileentities.BlockTileEntities;
import callumhutchy.runemagic.items.Items;
import callumhutchy.runemagic.utils.handlers.ConfigurationHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

public class CommonProxy implements IProxy{

	@Override
	public void onPreInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		BlockTileEntities.init();
		Items.init();
		Blocks.init();
	}

	@Override
	public void onInit(FMLInitializationEvent event) {
		
		
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		Items.registerCraftingRecipes();
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent event) {
		
		
	}

	@Override
	public void onServerStarting(FMLServerStartingEvent event) {
		
		
	}

	@Override
	public void onServerStopping(FMLServerStoppingEvent event) {
		
		
	}

	public void registerEventHandlers(){
		
	}
	
	@Override
	public void openMyGui() {
		
		
	}
	
}
