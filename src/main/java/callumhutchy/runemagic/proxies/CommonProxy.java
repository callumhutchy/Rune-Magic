package callumhutchy.runemagic.proxies;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.blocks.Blocks;
import callumhutchy.runemagic.blocks.models.tileentities.BlockTileEntities;
import callumhutchy.runemagic.client.gui.GuiHandler;
import callumhutchy.runemagic.items.Items;
import callumhutchy.runemagic.spells.Elements;
import callumhutchy.runemagic.spells.Spells;
import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.ExtendedPlayerStorage;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import callumhutchy.runemagic.utils.commands.CMDCurrentLevel;
import callumhutchy.runemagic.utils.commands.CMDCurrentSpell;
import callumhutchy.runemagic.utils.commands.CMDPlayers;
import callumhutchy.runemagic.utils.commands.CMDSetLevel;
import callumhutchy.runemagic.utils.handlers.CapabilityHandler;
import callumhutchy.runemagic.utils.handlers.ConfigurationHandler;
import callumhutchy.runemagic.utils.handlers.RuneMagicEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy implements IProxy{

	@Override
	public void onPreInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		
		
		Items.init();
		Blocks.init();
		
		}

	@Override
	public void onInit(FMLInitializationEvent event) {
		
		CapabilityManager.INSTANCE.register(IExtendedPlayer.class, new ExtendedPlayerStorage(), ExtendedPlayer.class);
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new RuneMagicEventHandler());
		
		BlockTileEntities.init();
		Items.registerCraftingRecipes();
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
		
		NetworkRegistry.INSTANCE.registerGuiHandler(RuneMagic.instance, new GuiHandler());
		
		FMLCommonHandler.instance().bus().register(new GuiHandler());
		
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent event) {
		Elements.init();
		Spells.init();
		
	}

	@Override
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CMDCurrentLevel());
		event.registerServerCommand(new CMDSetLevel());
		event.registerServerCommand(new CMDCurrentSpell());
		event.registerServerCommand(new CMDPlayers());
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
