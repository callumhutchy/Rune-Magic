package callumhutchy.runemagic.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.references.spells.RuneCost;
import callumhutchy.runemagic.references.spells.Spell;
import callumhutchy.runemagic.spells.Spells;
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

	private static final String rsrcLocStr = ModInfo.MODID + ":textures/gui/";
	private static final String spellLocStr = rsrcLocStr + "spells/";

	private final int bookImageHeight = 192;
	private final int bookImageWidth = 192;
	private int currentPage = 1;
	private static int bookTotalPages = 2;

	private static ResourceLocation bookPageTexture = new ResourceLocation(rsrcLocStr + "blankpage.png");

	private NextPageButton buttonNextPage;
	private NextPageButton buttonPreviousPage;

	private boolean nextButton, previousButton = false;
	
	@CapabilityInject(IExtendedPlayer.class)
	static Capability<IExtendedPlayer> EXT_PLAYER = null;

	private static final ResourceLocation spellSheet1Location = new ResourceLocation(spellLocStr + "spell_sheet_1.png");

	private ArrayList<SpellResource> spells = new ArrayList<SpellResource>();

	public static final int GUI_ID = 20;

	private static final int SPELL_ICON_SIZE = 32;
	private static final int SPELL_ICON_SPACING = SPELL_ICON_SIZE + 4;

	private GuiButton doneBtn;

	private void setSelectedSpell(String spellName) {
		System.out.println(spellName);
		for (SpellResource spellRsrc : spells) {
			if (spellName.equals(spellRsrc.spell.spellName)) {
				spellRsrc.isSelected = true;
				System.out.println("Enabling " + spellRsrc.spell.spellName);
			} else {
				spellRsrc.isSelected = false;
			}
		}
	}

	// Add spells in here
	private void addSpellToArray() {

		spells.add(new SpellResource(Spells.earthPillar, spellSheet1Location, 0, 32, 32, 32, 64, 32, 
				"2x Earth Rune _p"));
		spells.add(new SpellResource(Spells.heal, spellSheet1Location, 0, 0, 32, 0, 64, 0, 
				"1x Air Rune _p1x Earth Rune_p"));
		spells.add(new SpellResource(Spells.icePillar, spellSheet1Location, 0, 64, 32, 64, 64, 64, 
				"2x Water Rune_p"));
		spells.add(new SpellResource(Spells.meteor, spellSheet1Location, 0, 96, 32, 96, 64, 96, 
				"3x Fire Rune_p1x Earth Rune_p"));
		spells.add(new SpellResource(Spells.vampiricTouch, spellSheet1Location, 0, 128, 32, 128, 64, 128,
				 "3x Blood Rune_p"));
		spells.add(new SpellResource(Spells.regenerate, spellSheet1Location, 0, 160, 32, 160, 64, 160,
				"1x Blood Rune_p1x Nature Rune_p"));
		spells.add(new SpellResource(Spells.flameWall, spellSheet1Location, 0, 192, 32, 192, 64, 192,
				"2x Fire Rune_p"));
		spells.add(new SpellResource(Spells.shock, spellSheet1Location, 0, 224, 32, 224, 64, 224,
				"2x Air Rune_p"));
		spells.add(new SpellResource(Spells.fieryTouch, spellSheet1Location, 96, 0, 128, 0, 160, 0,
				"3x Fire Rune_p"));
		spells.add(new SpellResource(Spells.fieryBlast, spellSheet1Location, 96, 32, 128, 32, 160, 32,
				"2x Fire Rune_p"));

		Collections.sort(spells, new Comparator<SpellResource>() {
			@Override
			public int compare(SpellResource s1, SpellResource s2) {
				if (s1.levelReq > s2.levelReq)
					return 1;
				if (s1.levelReq < s2.levelReq)
					return -1;
				return 0;
			}
		});

	}

	@Override
	public void initGui() {
		
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
				.get(Minecraft.getMinecraft().player.getUniqueID());
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);

		spells.clear();
		addSpellToArray();
		setSelectedSpell(props.getSpell());
		bookTotalPages = (int) Math.ceil(spells.size() / 9.0);
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		buttonList.add(buttonNextPage = new NextPageButton(4, offsetFromScreenLeft + 120, 156, true));
		buttonList.add(buttonPreviousPage = new NextPageButton(5, offsetFromScreenLeft + 38, 156, false));
		

	}

	@Override
	public void updateScreen() {
		buttonNextPage.visible = (currentPage < bookTotalPages);
		buttonPreviousPage.visible = (currentPage > 1);
	}

	@Override
	public void drawScreen(int var1, int var2, float var3) {

		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
				.get(Minecraft.getMinecraft().player.getUniqueID());
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(bookPageTexture);

		int offsetFromScreenLeft = (width - bookImageWidth) / 2;
		drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, bookImageHeight);
		int widthOfString;
		String stringPageIndicator = I18n.format("book.pageIndicator",
				new Object[] { Integer.valueOf(currentPage), bookTotalPages });
		widthOfString = fontRendererObj.getStringWidth(stringPageIndicator);
		fontRendererObj.drawString(stringPageIndicator, offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 22,
				0);
		widthOfString = fontRendererObj.getStringWidth("Spellbook");
		fontRendererObj.drawString("Spellbook", offsetFromScreenLeft - widthOfString + bookImageWidth - 70, 14, 0);
		drawSkills();
		mouseOver(var1, var2);
		super.drawScreen(var1, var2, var3);

	}

	@Override
	protected void mouseClickMove(int varx, int vary, int lastButtonClicked, long timeSinceMouseClick) {

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void mouseOver(int varx, int vary) {

		for (int i = ((currentPage) * 9) - 9; i < ((currentPage) * 9); i++) {
			if (i >= spells.size()) {
				return;
			}

			SpellResource spell = spells.get(i);

			if (spell.minx < varx && varx < spell.maxx && spell.miny < vary && spell.maxy > vary) {
				this.RenderTooltip(varx, vary, spell.tooltip);
			}

		}
	}

	@Override
	protected void mouseClicked(int varx, int vary, int mouseButton) throws IOException {
		super.mouseClicked(varx, vary, mouseButton);
		if (mouseButton == 0) {

			EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
					.get(Minecraft.getMinecraft().player.getUniqueID());
			ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);

			for (int i = ((currentPage) * 9) - 9; i < ((currentPage) * 9); i++) {
				if (i >= spells.size()) {
					return;
				}

				SpellResource spell = spells.get(i);

				if (spell.minx < varx && varx < spell.maxx && spell.miny < vary && spell.maxy > vary
						&& spell.levelReq <= props.getLevel()) {
					System.out.println("Spell Selected");

					spells.get(i).isSelected = !spells.get(i).isSelected;

					if (spells.get(i).isSelected) {
						props.setSpell(spells.get(i).spell.spellName);
					} else {
						props.setSpell("");
					}

				} else {
					spells.get(i).isSelected = false;
				}
			}
		}
		
	}

	public void drawSkills() {
		EntityPlayer player = (EntityPlayer) RuneMagic.instance.players
				.get(Minecraft.getMinecraft().player.getUniqueID());
		ExtendedPlayer props = (ExtendedPlayer) player.getCapability(EXT_PLAYER, null);

		int currentLevel = props.getLevel();

		int offsetFromScreenLeft = (width - bookImageWidth) / 2 + 40;
		int offsetFromScreenTop = 35;
		int spellNumber = 0;
		int totalRows = 0;

		for (int i = ((currentPage) * 9) - 9; i < ((currentPage) * 9); i++) {
			if (i >= spells.size()) {
				return;
			}

			SpellResource spell = spells.get(i);

			if (spellNumber == 3) {
				offsetFromScreenTop += SPELL_ICON_SIZE + 6;
				spellNumber = 0;
				totalRows++;
				offsetFromScreenLeft = (width - bookImageWidth) / 2 + 40;
			}

			if (totalRows == 3) {

			}

			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			this.mc.getTextureManager().bindTexture(spell.rsrc);

			if (currentLevel < spell.levelReq) {
				this.drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenTop, spell.disx, spell.disy, 32, 32);
			} else if (spell.isSelected) {
				this.drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenTop, spell.selx, spell.sely, 32, 32);
			} else {
				this.drawTexturedModalRect(offsetFromScreenLeft, offsetFromScreenTop, spell.normx, spell.normy, 32, 32);
			}
			spellNumber++;

			spell.minx = offsetFromScreenLeft;
			spell.maxx = offsetFromScreenLeft + SPELL_ICON_SPACING;
			spell.miny = offsetFromScreenTop;
			spell.maxy = offsetFromScreenTop + SPELL_ICON_SPACING;

			offsetFromScreenLeft += SPELL_ICON_SPACING;
		}
		
		if(currentPage < bookTotalPages){
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(bookPageTexture);
			drawTexturedModalRect((width - bookImageWidth) / 2 + 120, 156, 0, 192, 23, 13);
			nextButton = true;
		}else{
			nextButton = false;
		}
		
		if(currentPage > 1){
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			mc.getTextureManager().bindTexture(bookPageTexture);
			drawTexturedModalRect((width - bookImageWidth) / 2 + 38, 156, 0, 205, 23, 13);
			previousButton = true;
		}else{
			previousButton = false;
		}
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton {
		private final boolean isNextButton;

		public NextPageButton(int buttonID, int posX, int posY, boolean isNextButton) {
			super(buttonID, posX, posY, 23, 13, "");
			this.isNextButton = isNextButton;
		}

		@Override
		public void drawButton(Minecraft mc, int parX, int parY) {
			if (visible) {
				boolean isButtonPressed = (parX >= xPosition && parY >= yPosition && parX < xPosition + width
						&& parY < yPosition + height);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				mc.getTextureManager().bindTexture(bookPageTexture);
				int textureX = 0;
				int textureY = 192;

				if (isButtonPressed) {
					textureX += 23;
				}

				if (!isNextButton) {
					textureY += 13;
				}

				drawTexturedModalRect(xPosition, yPosition, textureX, textureY, 23, 13);

			}
		}

	}

	@Override
	protected void actionPerformed(GuiButton btn) {
		if (btn == buttonNextPage) {
			
			if (currentPage < bookTotalPages) {
				currentPage++;
				System.out.println(currentPage + " // " + bookTotalPages);
			}
		} else if (btn == buttonPreviousPage) {
			if (currentPage > 1) {
				currentPage--;
			}
		}
	}

	@Override
	public void onGuiClosed() {

	}

}

class SpellResource {

	boolean isSelected = false;
	ResourceLocation rsrc;
	Spell spell;
	String tooltip = "Needs a tooltip";

	int normx, normy, disx, disy, selx, sely;

	int minx, miny, maxx, maxy;
	int levelReq;

	/**
	 * 
	 * @param spellName
	 *            - internal name of the spell
	 * @param normalRsrc
	 *            - location of normal spell icon
	 * @param disabledRsrc
	 *            - location of disabled spell icon
	 * @param selectedRsrc
	 *            - location of selected spell icon
	 * @param levelReq
	 *            - level requirment of the spell
	 */
	SpellResource(Spell spell, ResourceLocation rsrc, int normx, int normy, int selx, int sely, int disx, int disy,String tooltip) {
		this.spell = spell;
		this.rsrc = rsrc;
		this.normx = normx;
		this.normy = normy;
		this.disx = disx;
		this.disy = disy;
		this.selx = selx;
		this.sely = sely;
		this.levelReq = spell.getSpellLevelRequirment();
		this.tooltip = "_l" + this.spell.spellName.substring(0, 1).toUpperCase() + this.spell.spellName.substring(1) + ":_r _p"
				+ generateTooltip(spell.getRuneCost()) + spell.getDescription();
	}
	
	private String generateTooltip(ArrayList<RuneCost> runes){
		String runeCostString = "";
		for(RuneCost rune : runes){
			runeCostString += rune.getAmount() +"x " +rune.getRune().getUnlocalizedName() + "_p";
		}
		return runeCostString;
		
		
		
	}
	

}