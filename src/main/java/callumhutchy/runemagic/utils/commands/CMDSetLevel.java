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
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CMDSetLevel extends CommandBase{

	private List<String> aliases;
	
	public CMDSetLevel(){
		this.aliases = new ArrayList<String>();
		this.aliases.add("setlevel");
	}
	
	@Override
	public String getName() {

		return "setlevel";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "Sets your current level";
	}

	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
			ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
			
			props.setLevel(Integer.parseInt(args[0]));
			
			
			player.sendMessage(new TextComponentString("Set your current level to " + args[0]));
			
		}

	}

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAliases() {
		
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
		return false;
	}

}
