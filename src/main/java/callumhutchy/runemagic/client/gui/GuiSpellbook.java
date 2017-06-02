package callumhutchy.runemagic.client.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellbook extends RuneMagicGuiScreen {

	private static final ResourceLocation resourceLocation = new ResourceLocation("runemagic:textures/gui/spellbook.png");
	
	public static final int GUI_ID = 20;
	
	private static final int SPELL_ICON_SIZE = 32;
	private static final int SPELL_ICON_SPACING = SPELL_ICON_SIZE + 4;
	
	public boolean doesGuiPauseGame(boolean var1) {
		return false;

	}
	
}
