package callumhutchy.runemagic.utils.commands;

import java.util.ArrayList;
import java.util.List;

import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CMDCurrentSpell extends CommandBase{
private List<String> aliases;
	
	public CMDCurrentSpell(){
		this.aliases = new ArrayList<String>();
		this.aliases.add("spell");
	}
	
	@Override
	public String getName() {

		return "currentspell";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "Displays your current spell";
	}

	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer){
			
				System.out.println(((EntityPlayer) sender).getUniqueID() + " / / " + sender.getName());
			
				EntityPlayer player = (EntityPlayer) sender;
				ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
				
				player.sendMessage(new TextComponentString("Your current spell is " + props.getSpell()));
				
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
