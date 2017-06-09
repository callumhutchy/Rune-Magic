package callumhutchy.runemagic.items;

import java.awt.TextComponent;
import java.util.ArrayList;
import java.util.Optional;

import com.jcraft.jorbis.Block;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.references.spells.Element;
import callumhutchy.runemagic.references.spells.RuneCost;
import callumhutchy.runemagic.references.spells.Spell;
import callumhutchy.runemagic.spells.Spells;
import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Staff extends BasicMagicItem {

	public Element staffElement;
	public int runesProvided;

	public Staff(String unlocalisedName, Element staffElement) {
		super(unlocalisedName);
		this.setMaxStackSize(1);
		this.staffElement = staffElement;

	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
		
		SpellCast(playerIn, world, handIn, null,null,0,0,0);
		
		return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		SpellCast(player, world,hand,pos,facing,hitX,hitY,hitZ);
		
        return EnumActionResult.PASS;
    }

	public void SpellCast(EntityPlayer playerIn, World world,EnumHand hand, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ){
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
				.get(Minecraft.getMinecraft().player.getUniqueID());
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		if (!world.isRemote) {

			String currentSpell = props.getSpell();
			
			
			switch (currentSpell) {
			case NameConstants.SPELL_FIERYBLAST:

				break;
			case NameConstants.SPELL_HEAL:
				if (playerIn.getHealth() != playerIn.getMaxHealth() && !creativeCasting(playerIn)) {
					if (consumeAllSpellRunes(playerIn, Spells.getSpellByName(currentSpell), world)) {
						playerIn.setHealth(playerIn.getHealth() + Spells.heal.getSpellDamage());
						increaseExperience(Spells.heal.getSpellExp(), player);
					}
				}else if(creativeCasting(playerIn)){
					sendMessage(playerIn, "You are in creative mode, the spell had no effect.");
				} else if (playerIn.getHealth() == playerIn.getMaxHealth()) {
					sendMessage(playerIn,"You already have max health.");
				}
				break;
			case NameConstants.SPELL_EARTHPILLAR:
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == Blocks.AIR.getDefaultState() && !world.isRemote){
					if (consumeAllSpellRunes(playerIn, Spells.getSpellByName(currentSpell), world)) {
						
						for(int i = 0; i < 3; i++){
							if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())) == Blocks.AIR.getDefaultState()){
								world.setBlockState(new BlockPos(pos.getX(),pos.getY()+i, pos.getZ()), Blocks.STONE.getDefaultState());
							}
						}
						increaseExperience(Spells.earthPillar.getSpellExp(), player);
					}
				}
				break;
			case NameConstants.SPELL_ICEPILLAR:
				
				break;
			default:

			}
		}
	}
	
	public static void increaseExperience(float exp, EntityPlayer player){
		player.getCapability(EXT_PLAYER, null).increaseExperience(exp);
	}
	
	boolean creativeCasting(EntityPlayer player) {
		return player.capabilities.isCreativeMode;
	}

	public static Rune[] addToArrayRune(Rune[] uniqueValues, Rune rune) {
		Rune[] returnArray = new Rune[uniqueValues.length + 1];
		for (int i = 0; i < uniqueValues.length; i++) {
			returnArray[i] = uniqueValues[i];
		}
		returnArray[returnArray.length - 1] = rune;
		return returnArray;
	}

	public static int[] addToArrayInt(int[] countValues, int newValue) {
		int[] returnArray = new int[countValues.length + 1];
		for (int i = 0; i < countValues.length; i++) {
			returnArray[i] = countValues[i];
		}
		returnArray[returnArray.length - 1] = newValue;
		return returnArray;
	}

	public boolean consumeAllSpellRunes(EntityPlayer entity, Spell spell, World world) {
		ArrayList<ItemStack> itemsToRemove = new ArrayList<ItemStack>();
		if (!creativeCasting(entity)) {
			for (RuneCost rune : spell.getRuneCost()) {
				if (staffElement == null || rune.getRune() != staffElement.rune) {
					ItemStack runesToRemove = new ItemStack(rune.getRune(), rune.getAmount());
					if (entity.inventory.hasItemStack(runesToRemove)) {
						itemsToRemove.add(runesToRemove);
					} else {
						return false;
					}
				}

			}

			System.out.println(itemsToRemove.size());

			for (ItemStack runes : itemsToRemove) {
				System.out.println(runes.getUnlocalizedName());
				if (!world.isRemote) {
					int i = 0;
					int requiredToRemove = runes.getCount();
					while (requiredToRemove > 0) {
						ItemStack stack = entity.inventory.mainInventory.get(i);
						if (stack != null && stack.getItem() == runes.getItem()) {
							if (requiredToRemove >= stack.getCount()) {
								entity.inventory.mainInventory.set(i, null);
							} else {
								stack.setCount(stack.getCount() - requiredToRemove);
								requiredToRemove -= stack.getCount();
							}
						}
						i++;
					}
				}

			}
			return true;

		} else {
			return true;
		}
	}

	boolean isValid(ArrayList<Boolean> contains) {
		for (Boolean c : contains) {
			if (c = false)
				return false;
		}
		return true;
	}

	public static void sendMessage(EntityPlayer player, String msg){
		player.sendMessage(new TextComponentString(msg));
	}
	
}
