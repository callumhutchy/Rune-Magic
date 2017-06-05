package callumhutchy.runemagic.utils.capability;

import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ExtendedPlayerProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IExtendedPlayer.class)
	public static final Capability<IExtendedPlayer> EXT_PLAYER = null;
	
	private IExtendedPlayer instance = EXT_PLAYER.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		return capability == EXT_PLAYER;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		return capability == EXT_PLAYER ? EXT_PLAYER.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		
		return EXT_PLAYER.getStorage().writeNBT(EXT_PLAYER, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		EXT_PLAYER.getStorage().readNBT(EXT_PLAYER, this.instance, null, nbt);
		
	}

}
