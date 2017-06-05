package callumhutchy.runemagic.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
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
		spells.add(new SpellResource(NameConstants.SPELL_EARTHPILLAR, new ResourceLocation(spellLocStr + "earthpillar.png"),new ResourceLocation(spellLocStr + "earthpillardisabled.png"),new ResourceLocation(spellLocStr + "earthpillarselected.png"),2));
		spells.add(new SpellResource(NameConstants.SPELL_HEAL, new ResourceLocation(spellLocStr + "heal.png"),new ResourceLocation(spellLocStr + "healdisabled.png"),new ResourceLocation(spellLocStr + "healselected.png"),1));
		spells.add(new SpellResource(NameConstants.SPELL_ICEPILLAR, new ResourceLocation(spellLocStr + "icepillar.png"),new ResourceLocation(spellLocStr + "icepillardisabled.png"),new ResourceLocation(spellLocStr + "icepillarselected.png"),3));
		
		Collections.sort(spells, new Comparator<SpellResource>(){
			@Override
			public int compare(SpellResource s1, SpellResource s2){
				if(s1.levelReq > s2.levelReq)
					return 1;
				if(s1.levelReq < s2.levelReq)
					return -1;
				return 0;
			}
		});
	
	}
	
	public void initGui(){
		addSpellIconsToArray();
		Keyboard.enableRepeatEvents(true);
		//this.buttonList.add(this.doneBtn = new GuiButton(1,this.width / 2 - 100, this.height / 4 + 108, "Done"));
	}
	
	public void drawScreen(int var1, int var2, float var3){
		int xSize = 256;
		int ySize = 256;
		int xStart = (width/2) - (xSize/2);
		int yStart = (height /2) - (ySize / 2);
		
		int iconyStart = yStart + 22;
		int iconxStart = xStart + 17;
		
		this.drawCenteredString(this.fontRendererObj, "Spellbook:", this.width/2, (this.height /2) - (ySize /2),16777215);
		this.drawBackground(0);
		this.doesGuiPauseGame();
		this.IsButtonMouseovered(var1, var2,null);
		this.drawSkills();
		super.drawScreen(var1, var2, var3);
		
		
	}
	
	public void drawBackground(int var1){
		int xSize = 256;
		int ySize = 256;
		int xStart = (width/2) - (xSize/2);
		int yStart = (height/2) - (ySize/2);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resourceLocation);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	protected boolean IsButtonMouseovered(int varx, int vary, GuiButton button){
		 return true;
	}
	
	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;
	
	public void drawSkills(){
		int xSize = 256;
		int ySize = 256;
		int xStart = (width / 2) - (xSize / 2);
		int yStart = (height / 2) - (ySize / 2);
		float iconyStart = yStart + 22;
		float iconxStart = xStart + 17;
		EntityPlayer player = (EntityPlayer) this.mc.player;
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		
		int currentLevel = props.getLevel();
		String currentSpell = props.getSpell();
		
		for(SpellResource spell : spells){
			
			if(currentLevel < spell.levelReq){
				mc.getTextureManager().bindTexture(spell.disabledRsrc);
				this.drawTexturedModalRect(0f, 0f, 0, 0, 32, 32);
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				
			}else if(spell.isSelected){
				mc.getTextureManager().bindTexture(spell.selectedRsrc);
				this.drawTexturedModalRect(0f,0f, 0, 0, 32, 32);
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			}else{
				System.out.println("Hello its me");
				mc.getTextureManager().bindTexture(spell.normalRsrc);
				this.drawTexturedModalRect(0f, 0f, 0, 0, 32, 32);
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			}
		}
		
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
	String spellName;
	int minx,miny,maxx,maxy;
	int levelReq;
	
	/**
	 * 
	 * @param spellName - internal name of the spell
	 * @param normalRsrc - location of normal spell icon
	 * @param disabledRsrc - location of disabled spell icon
	 * @param selectedRsrc - location of selected spell icon
	 * @param levelReq - level requirment of the spell
	 */
	SpellResource(String spellName, ResourceLocation normalRsrc,ResourceLocation disabledRsrc, ResourceLocation selectedRsrc, int levelReq){
		this.normalRsrc = normalRsrc;
		this.disabledRsrc = disabledRsrc;
		this.selectedRsrc = selectedRsrc;
		this.levelReq = levelReq;
	}
	
}