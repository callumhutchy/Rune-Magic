package callumhutchy.runemagic.items;

import java.awt.TextComponent;
import java.util.ArrayList;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.references.spells.Element;
import callumhutchy.runemagic.references.spells.RuneCost;
import callumhutchy.runemagic.references.spells.Spell;
import callumhutchy.runemagic.spells.Spells;
import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
				.get(Minecraft.getMinecraft().player.getUniqueID());
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		if (!world.isRemote) {
			System.out.println(props.getSpell());

			switch (props.getSpell()) {
			case NameConstants.SPELL_FIERYBLAST:

				break;
			case NameConstants.SPELL_HEAL:

				if (consumeAllSpellRunes(playerIn, Spells.getSpellByName(props.getSpell()), world)
						&& playerIn.getHealth() != playerIn.getMaxHealth()) {
					playerIn.setHealth(playerIn.getHealth() + Spells.heal.getSpellDamage());
				} else if (playerIn.getHealth() == playerIn.getMaxHealth()) {
					playerIn.sendMessage(new TextComponentString("You already have max health."));
				}
				break;
			default:

			}
		}
		return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
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
				if(!world.isRemote){
					entity.inventory.deleteStack(runes);
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

}
