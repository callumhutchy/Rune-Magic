package callumhutchy.runemagic.utils.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CMDPlayers extends CommandBase{
private List<String> aliases;
	
	public CMDPlayers(){
		this.aliases = new ArrayList<String>();
		this.aliases.add("players");
	}
	
	@Override
	public String getName() {

		return "currentplayers";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "Displays current players";
	}

	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		Iterator<Map.Entry<String, EntityPlayer>> entries = RuneMagic.instance.players.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<String, EntityPlayer> entry = entries.next();
			System.out.println(entry.getValue().getCapability(EXT_PLAYER, null).getSpell());
		}
		
	}

	@Override
	public int compareTo(ICommand arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return aliases;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		// TODO Auto-generated method stub
		return true;
	}
}
