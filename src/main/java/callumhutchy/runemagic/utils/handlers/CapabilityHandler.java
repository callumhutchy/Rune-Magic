package callumhutchy.runemagic.utils.handlers;

import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.utils.capability.ExtendedPlayerProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityHandler {

	public static final ResourceLocation ExtendedPlayer = new ResourceLocation(ModInfo.MODID, "extendedplayer");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event){
		if(!(event.getEntity() instanceof EntityPlayer)) return;
		if(!event.getCapabilities().containsKey(ExtendedPlayer)){
			event.addCapability(ExtendedPlayer, new ExtendedPlayerProvider() );
		}
		
		
		
	}
	
}
