package callumhutchy.runemagic.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.NameConstants;
import callumhutchy.runemagic.utils.capability.ExtendedPlayer;
import callumhutchy.runemagic.utils.capability.interfaces.IExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
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
	
	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currentPage = 0;
	private static int bookTotalPages = 0;
	
	private static ResourceLocation bookPageTexture = new ResourceLocation(rsrcLocStr + "blankpage.png");
	
	
	
	private static ResourceLocation blankPage = new ResourceLocation(rsrcLocStr + "blankpage.png");
	private static ResourceLocation nextPageButtonSelected = new ResourceLocation(rsrcLocStr + "nextpageselected.png");
	private static ResourceLocation nextPageButtonUnselected = new ResourceLocation(rsrcLocStr + "nextpageunselected.png");
	private static ResourceLocation previousPageButtonSelected = new ResourceLocation(rsrcLocStr + "previouspageselected.png");
	private static ResourceLocation previousPageButtonUnselected = new ResourceLocation(rsrcLocStr + "previouspageunselected.png");
	
	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;
	
	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;
	
	public GuiSpellbook(){
		
	}
	
	private static final ResourceLocation resourceLocation = new ResourceLocation(rsrcLocStr + "spellbook.png");
	
	private static final ResourceLocation earthPillarLocation =new ResourceLocation(rsrcLocStr + "earthpillar.png");
	
	private ArrayList<SpellResource> spells = new ArrayList<SpellResource>();
	
	public static final int GUI_ID = 20;
	
	private static final int SPELL_ICON_SIZE = 32;
	private static final int SPELL_ICON_SPACING = SPELL_ICON_SIZE + 4;
	
	private GuiButton doneBtn;
	
	private void setSelectedSpell(String spellName){
		System.out.println(spellName);
		for(SpellResource spell : spells){
			if(spellName.equals(spell.spellName)){
				spell.isSelected = true;
				System.out.println("Enabling " + spell.spellName);
			}else{
				spell.isSelected = false;
			}
		}
	}
	
	//Add spells in here
	private void addSpellToArray(){
		
		spells.add(new SpellResource(NameConstants.SPELL_EARTHPILLAR, new ResourceLocation(spellLocStr + "earthpillar.png"),new ResourceLocation(spellLocStr + "earthpillardisabled.png"),new ResourceLocation(spellLocStr + "earthpillarselected.png"),2, "2x Earth Rune _pCreates a pillar of earth from the ground."));
		spells.add(new SpellResource(NameConstants.SPELL_HEAL, new ResourceLocation(spellLocStr + "heal.png"),new ResourceLocation(spellLocStr + "healdisabled.png"),new ResourceLocation(spellLocStr + "healselected.png"),1, "1x Air Rune _p1x Earth Rune_pHeals the user for a small amount."));
		spells.add(new SpellResource(NameConstants.SPELL_ICEPILLAR, new ResourceLocation(spellLocStr + "icepillar.png"),new ResourceLocation(spellLocStr + "icepillardisabled.png"),new ResourceLocation(spellLocStr + "icepillarselected.png"),3, "2x Water Rune_pCreates a pillar of ice from the ground."));
		
		
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
	
	@Override
	public void initGui(){
		EntityPlayer player =(EntityPlayer) RuneMagic.instance.players.get(Minecraft.getMinecraft().player.getUniqueID()) ;
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		
		spells.clear();
		addSpellToArray();
		setSelectedSpell(props.getSpell());
		bookTotalPages = (int) Math.ceil(spells.size() / 9.0);
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		buttonList.add(buttonNextPage = new NextPageButton(1, offsetFromScreenLeft + 120, 156, true));
		buttonList.add(buttonPreviousPage = new NextPageButton(2, offsetFromScreenLeft + 38, 156, false));
		
	}
	
	@Override
	public void updateScreen(){
		buttonNextPage.visible = (currentPage < bookTotalPages -1);
		buttonPreviousPage.visible = currentPage > 0;
	}
	
	@Override
	public void drawScreen(int var1, int var2, float var3){
				
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players.get(Minecraft.getMinecraft().player.getUniqueID()) ;
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bookPageTexture);
		
		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		drawTexturedModalRect(offsetFromScreenLeft, 2,0,0,bookImageWidth, bookImageHeight);
		int widthOfString;
		String stringPageIndicator = I18n.format("book.pageIndicator", new Object[] {Integer.valueOf(currentPage + 1), bookTotalPages});
		widthOfString = fontRendererObj.getStringWidth(stringPageIndicator);
		fontRendererObj.drawString(stringPageIndicator, offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 22, 0);
		widthOfString = fontRendererObj.getStringWidth("Spellbook");
		fontRendererObj.drawString("Spellbook", offsetFromScreenLeft - widthOfString + bookImageWidth -70, 14, 0);
		drawSkills();
		mouseOver(var1,var2);
		super.drawScreen(var1, var2, var3);
		
	}

	@Override
	protected void mouseClickMove(int varx, int vary, int lastButtonClicked, long timeSinceMouseClick){
		
	}
	
	@Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void drawBackground(){
		int xSize = 256;
		int ySize = 256;
		int xStart = (width/2) - (xSize/2);
		int yStart = (height/2) - (ySize/2);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resourceLocation);
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	protected void mouseOver(int varx, int vary){
		
		for(int i = ((currentPage + 1) * 9) - 9; i < ((currentPage + 1) * 9); i++){
			if(i >= spells.size()){
				return;
			}
			
			SpellResource spell = spells.get(i);
			
			if(spell.minx < varx && varx < spell.maxx && spell.miny < vary && spell.maxy > vary){
				this.RenderTooltip(varx, vary, spell.tooltip);
			}
			
		}
		return;
	}
		
	@Override
	protected void mouseClicked(int varx, int vary, int mouseButton){
		if(mouseButton == 0){
			
			EntityPlayer player = (EntityPlayer) RuneMagic.instance.players.get(Minecraft.getMinecraft().player.getUniqueID()) ;
			ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
			
			for(int i = ((currentPage + 1) * 9) - 9; i < ((currentPage + 1) * 9); i++){
				if(i >= spells.size()){
					return;
				}
				
				SpellResource spell = spells.get(i);
				
				if(spell.minx < varx && varx < spell.maxx && spell.miny < vary && spell.maxy > vary && spell.levelReq <= props.getLevel()){
					System.out.println("Spell Selected");
					
					spells.get(i).isSelected = !spells.get(i).isSelected;
					
					if(spells.get(i).isSelected){
						props.setSpell(spells.get(i).spellName);
					}else{
						props.setSpell("");
					}
					
				}else{
					spells.get(i).isSelected = false;
				}
			}
		}
	}
	
	public void drawSkills(){
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players.get(Minecraft.getMinecraft().player.getUniqueID()) ;
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);
		
		int currentLevel = props.getLevel();
		
		int offsetFromScreenLeft = (width - bookImageWidth) / 2 + 40;
		int offsetFromScreenTop = 35;
		int spellNumber = 0;
		int totalRows = 0;
		
		for(int i = ((currentPage + 1) * 9) - 9; i < ((currentPage + 1) * 9); i++){
			if(i >= spells.size()){
				return;
			}
			
			SpellResource spell = spells.get(i);
			System.out.println(spell.spellName + "//"+props.getSpell());
			
			
			if(spellNumber == 3){
				offsetFromScreenTop += SPELL_ICON_SIZE + 6;
				spellNumber = 0;
				totalRows++;
			}
			
			if(totalRows == 3){
				
			}
			if(currentLevel < spell.levelReq){
				System.out.println("Enabled disabled version");
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				this.mc.getTextureManager().bindTexture(spell.disabledRsrc);
				this.drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenTop, 0, 0, 32, 32);
				
			}else if(spell.isSelected){
				System.out.println("Enabled selected version");
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				this.mc.getTextureManager().bindTexture(spell.selectedRsrc);
				this.drawTexturedModalRect(offsetFromScreenLeft,offsetFromScreenTop, 0, 0, 32, 32);
				
			}else{
				System.out.println("Enabled normal version");
				GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
				this.mc.getTextureManager().bindTexture(spell.normalRsrc);
				this.drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenTop, 0, 0, 32, 32);
				
			}
			spellNumber ++;
			
			spell.minx = offsetFromScreenLeft;
			spell.maxx = offsetFromScreenLeft + SPELL_ICON_SPACING;
			spell.miny = offsetFromScreenTop;
			spell.maxy = offsetFromScreenTop + SPELL_ICON_SPACING;
			
			offsetFromScreenLeft +=  SPELL_ICON_SPACING;
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton{
		private final boolean isNextButton;
		
		public NextPageButton(int buttonID, int posX, int posY, boolean isNextButton){
			super(buttonID,posX, posY, 23,13,"");
			this.isNextButton = isNextButton;
		}
		
		@Override
		public void drawButton(Minecraft mc, int parX, int parY){
			if(visible){
				boolean isButtonPressed = (parX >= xPosition && parY >= yPosition && parX < xPosition + width && parY < yPosition + height);
				GL11.glColor4f(1.0F,1.0F,1.0F,1.0F);
				mc.getTextureManager().bindTexture(bookPageTexture);
				int textureX = 0;
				int textureY = 192;
				
				if(isButtonPressed){
					textureX += 23;
				}
				
				if(!isNextButton){
					textureY += 13;
				}
				
				drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 23,13);
				
			}
		}
		
		
		
	}
	
	@Override
	protected void actionPerformed(GuiButton btn){
		if(btn == buttonNextPage){
			if(currentPage < bookTotalPages -1){
				++currentPage;
			}
		}else if(btn == buttonPreviousPage){
			if(currentPage > 0){
				--currentPage;
			}
		}
	}
	
	@Override
    public void onGuiClosed() 
    {
     
    }
	
}
class SpellResource{

	boolean isSelected = false;
	ResourceLocation normalRsrc;
	ResourceLocation disabledRsrc;
	ResourceLocation selectedRsrc;
	String spellName;
	String tooltip = "Needs a tooltip";
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
	SpellResource(String spellName, ResourceLocation normalRsrc,ResourceLocation disabledRsrc, ResourceLocation selectedRsrc, int levelReq, String tooltip){
		this.spellName = spellName;
		this.normalRsrc = normalRsrc;
		this.disabledRsrc = disabledRsrc;
		this.selectedRsrc = selectedRsrc;
		this.levelReq = levelReq;
		this.tooltip = "_l"+this.spellName.substring(0,1).toUpperCase()+this.spellName.substring(1)+":_r _p" + tooltip;
	}

	
	
	
}