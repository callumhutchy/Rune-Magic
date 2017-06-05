package callumhutchy.runemagic.utils.capability;

import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import scala.collection.generic.BitOperations.Int;

public class ExtendedPlayerStorage implements IStorage<IExtendedPlayer>{

	@Override
	public NBTBase writeNBT(Capability<IExtendedPlayer> capability, IExtendedPlayer instance, EnumFacing side) {
		
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("level", instance.getLevel());
		compound.setFloat("experience", instance.getExperience());
		compound.setString("currentspell", instance.getSpell());
		
		
		return compound;
	}

	@Override
	public void readNBT(Capability<IExtendedPlayer> capability, IExtendedPlayer instance, EnumFacing side,
			NBTBase nbt) {
		if(nbt instanceof NBTTagCompound){
			instance.setLevel(((NBTTagCompound) nbt).getInteger("level"));
			instance.setExperience(((NBTTagCompound) nbt).getFloat("experience"));
			instance.setSpell(((NBTTagCompound) nbt).getString("currentspell"));
		}
		
		
		
	}

}
