package callumhutchy.runemagic.client.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpellbook extends RuneMagicGuiScreen {

	private static final String rsrcLocStr= ModInfo.MODID + ":textures/gui/";
	private static final String spellLocStr = rsrcLocStr + "spells/";
	
	private static final ResourceLocation resourceLocation = new ResourceLocation(rsrcLocStr + "spellbook.png");
	
	
	
	private ArrayList<SpellResource> spells = new ArrayList<SpellResource>();
	
	public static final int GUI_ID = 20;
	
	private static final int SPELL_ICON_SIZE = 32;
	private static final int SPELL_ICON_SPACING = SPELL_ICON_SIZE + 4;
	
	private GuiButton doneBtn;
	
	private void addSpellIconsToArray(){
		spells.add(new SpellResource(new ResourceLocation(spellLocStr + "earthpillar.png"),new ResourceLocation(spellLocStr + "earthpillardisabled.png"),new ResourceLocation(spellLocStr + "earthpillarselected.png")));
		spells.add(new SpellResource(new ResourceLocation(spellLocStr + "heal.png"),new ResourceLocation(spellLocStr + "healdisabled.png"),new ResourceLocation(spellLocStr + "healselected.png")));
		spells.add(new SpellResource(new ResourceLocation(spellLocStr + "icepillar.png"),new ResourceLocation(spellLocStr + "icepillardisabled.png"),new ResourceLocation(spellLocStr + "icepillarselected.png")));
		
	
	}
	
	public void initGui(){
		addSpellIconsToArray();
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(this.doneBtn = new GuiButton(1,this.width / 2 - 100, this.height / 4 + 108, "Done"));
	}
	
	public void drawScreen(int var1, int var2, float var3){
		int xSize = 256;
		int ySize = 256;
		int xStart = (width/2) - (xSize/2);
		int yStart = (height /2) - (ySize / 2);
		
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;
		
		
		
	}
	
	
	protected void actionPerformed(GuiButton btn){
		switch(btn.id){
		case 1:
			//Close the gui
			break;
		}
	}
	
	public boolean doesGuiPauseGame(boolean var1) {
		return false;

	}
	
	
	
}
class SpellResource{

	boolean isSelected = false;
	ResourceLocation normalRsrc;
	ResourceLocation disabledRsrc;
	ResourceLocation selectedRsrc;
	int minx,miny,maxx,maxy;
	
	SpellResource(ResourceLocation normalRsrc,ResourceLocation disabledRsrc, ResourceLocation selectedRsrc){
		this.normalRsrc = normalRsrc;
		this.disabledRsrc = disabledRsrc;
		this.selectedRsrc = selectedRsrc;
	}
	
}