package callumhutchy.runemagic.utils.handlers;

import java.util.HashMap;
import java.util.Map;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.utils.capability.ExtendedPlayerProvider;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class RuneMagicEventHandler {

	
	
	@SubscribeEvent
	public void OnPlayerLogsIn(PlayerLoggedInEvent event){
		RuneMagic.instance.players.put(event.player.getUniqueID(),(EntityPlayer) event.player);
		
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event){
		EntityPlayer player = event.getEntityPlayer();
		IExtendedPlayer extendedPlayer = player.getCapability(ExtendedPlayerProvider.EXT_PLAYER, null);
		IExtendedPlayer oldExtendedPlayer = event.getOriginal().getCapability(ExtendedPlayerProvider.EXT_PLAYER, null);
		extendedPlayer.setExperience(oldExtendedPlayer.getExperience());
		extendedPlayer.setLevel(oldExtendedPlayer.getLevel());
		extendedPlayer.setSpell(oldExtendedPlayer.getSpell());
	}
	
}
