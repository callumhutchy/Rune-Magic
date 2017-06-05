package callumhutchy.runemagic;

import callumhutchy.runemagic.proxies.CommonProxy;
import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, name = ModInfo.MODNAME)
public class RuneMagic {

	@SidedProxy(clientSide=ModInfo.CLIENT_PROXY, serverSide=ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	public static CreativeTabs tabRuneMagic;
	
	@Mod.Instance(ModInfo.MODID)
	public static RuneMagic instance = new RuneMagic();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		tabRuneMagic = new CreativeTabs("tabRuneMagic"){
			@Override
			public ItemStack getTabIconItem(){
				return new ItemStack(Item.getByNameOrId(ModInfo.MODID+":airrune"));
			}
		};
		proxy.onPreInit(e);
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		proxy.onInit(e);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		proxy.onPostInit(e);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		proxy.onServerStarting(event);
	}
	
	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event){
		proxy.onServerStopping(event);
	}
	
	
}
