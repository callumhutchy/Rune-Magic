package callumhutchy.runemagic.client.gui;

import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;

public class RuneMagicGuiScreen extends GuiScreen {

private static final String tooltipNewlineDelimeter = "_p";
	
	public long tooltipDelay = 900;
	
	/**
	 * The maximum width in pixels a tooltip can occupy before word wrapping
	 * occurs
	 */
	public int tooltipMaxWidth = 150;

	protected int tooltipXOffset = 0;
	protected int tooltipYOffset = 10;

	private final static int LINE_HEIGHT = 11;

	private long mouseoverTime = 0;
	private long prevSystemTime = -1;
	
	
	
	
	protected void RenderTooltip(int x, int y, String tooltip) {
		String[] tooltipArray = ParseTooltipArrayFromString(tooltip);

		int tooltipWidth = GetTooltipWidth(tooltipArray);
		int tooltipHeight = GetTooltipHeight(tooltipArray);

		int tooltipX = x + tooltipXOffset;
		int tooltipY = y + tooltipYOffset;

		if (tooltipX > width - tooltipWidth - 7)
			tooltipX = width - tooltipWidth - 7;
		if (tooltipY > height - tooltipHeight - 8)
			tooltipY = height - tooltipHeight - 8;

		// render the background inside box
		int innerAlpha = -0xFEFFFF0; // very very dark purple
		drawGradientRect(tooltipX, tooltipY - 1, tooltipX + tooltipWidth + 6, tooltipY, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX, tooltipY + tooltipHeight + 6, tooltipX + tooltipWidth + 6,
				tooltipY + tooltipHeight + 7, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 6, tooltipY + tooltipHeight + 6, innerAlpha,
				innerAlpha);
		drawGradientRect(tooltipX - 1, tooltipY, tooltipX, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
		drawGradientRect(tooltipX + tooltipWidth + 6, tooltipY, tooltipX + tooltipWidth + 7,
				tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);

		// render the background outside box
		int outerAlpha1 = 0x505000FF;
		int outerAlpha2 = (outerAlpha1 & 0xFEFEFE) >> 1 | outerAlpha1 & -0x1000000;
		drawGradientRect(tooltipX, tooltipY + 1, tooltipX + 1, tooltipY + tooltipHeight + 6 - 1, outerAlpha1,
				outerAlpha2);
		drawGradientRect(tooltipX + tooltipWidth + 5, tooltipY + 1, tooltipX + tooltipWidth + 7,
				tooltipY + tooltipHeight + 6 - 1, outerAlpha1, outerAlpha2);
		drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 3, tooltipY + 1, outerAlpha1, outerAlpha1);
		drawGradientRect(tooltipX, tooltipY + tooltipHeight + 5, tooltipX + tooltipWidth + 7,
				tooltipY + tooltipHeight + 6, outerAlpha2, outerAlpha2);

		// render the foreground text
		int lineCount = 0;
		for (String s : tooltipArray) {
			mc.fontRendererObj.drawString(s, tooltipX + 2, tooltipY + 2 + lineCount * LINE_HEIGHT, 0xFFFFFF);
			lineCount++;
		}
	}
	
	/**
	 * Converts a String representation of a tooltip into a String[], and also
	 * decodes any font codes used.
	 * 
	 * @param s
	 *            Ex: "Hello,_nI am your _ltooltip_r and you love me."
	 * @return An array of Strings such that each String width does not exceed
	 *         tooltipMaxWidth
	 */
	protected String[] ParseTooltipArrayFromString(String s) {
		s = DecodeStringCodes(s);
		String[] tooltipSections = s.split(tooltipNewlineDelimeter);
		ArrayList<String> tooltipArrayList = new ArrayList<String>();

		for (String section : tooltipSections) {
			String tooltip = "";
			String[] tooltipWords = section.split(" ");

			for (int i = 0; i < tooltipWords.length; i++) {
				int lineWidthWithNextWord = mc.fontRendererObj.getStringWidth(tooltip + tooltipWords[i]);
				if (lineWidthWithNextWord > tooltipMaxWidth) {
					tooltipArrayList.add(tooltip.trim());
					tooltip = tooltipWords[i] + " ";
				} else {
					tooltip += tooltipWords[i] + " ";
				}
			}

			tooltipArrayList.add(tooltip.trim());
		}

		String[] tooltipArray = new String[tooltipArrayList.size()];
		tooltipArrayList.toArray(tooltipArray);

		return tooltipArray;
	}

	/**
	 * Decodes any font codes into something useable by the FontRenderer.
	 * 
	 * @param s
	 *            E.x.: "Hello,_nI am your _ltooltip_r and you love me."
	 * @return E.x. output (html not included): <br>
	 *         "Hello,<br>
	 *         I am your <b>tooltip</b> and you love me."
	 */
	private String DecodeStringCodes(String s) {
		return s.replace("_0", FontCodes.BLACK).replace("_1", FontCodes.DARK_BLUE).replace("_2", FontCodes.DARK_GREEN)
				.replace("_3", FontCodes.DARK_AQUA).replace("_4", FontCodes.DARK_RED)
				.replace("_5", FontCodes.DARK_PURPLE).replace("_6", FontCodes.GOLD).replace("_7", FontCodes.GRAY)
				.replace("_8", FontCodes.DARK_GREY).replace("_9", FontCodes.BLUE).replace("_a", FontCodes.GREEN)
				.replace("_b", FontCodes.AQUA).replace("_c", FontCodes.RED).replace("_d", FontCodes.LIGHT_PURPLE)
				.replace("_e", FontCodes.YELLOW).replace("_f", FontCodes.WHITE).replace("_k", FontCodes.OBFUSCATED)
				.replace("_l", FontCodes.BOLD).replace("_m", FontCodes.STRIKETHROUGH).replace("_n", FontCodes.UNDERLINE)
				.replace("_o", FontCodes.ITALICS).replace("_r", FontCodes.RESET);
	}

	/***
	 * Gets the width of the tooltip in pixels.
	 * 
	 * @param tooltipArray
	 * @return
	 */
	private int GetTooltipWidth(String[] tooltipArray) {
		int longestWidth = 0;
		for (String s : tooltipArray) {
			int width = mc.fontRendererObj.getStringWidth(s);
			if (width > longestWidth)
				longestWidth = width;
		}
		return longestWidth;
	}

	/**
	 * Gets the height of the tooltip in pixels.
	 * 
	 * @param tooltipArray
	 * @return
	 */
	private int GetTooltipHeight(String[] tooltipArray) {
		int tooltipHeight = mc.fontRendererObj.FONT_HEIGHT - 2;
		if (tooltipArray.length > 1) {
			tooltipHeight += (tooltipArray.length - 1) * LINE_HEIGHT;
		}
		return tooltipHeight;
	}

	/**
	 * Gets a protected/private field from a class using reflection.
	 * 
	 * @param <T>
	 *            The return type of the field you are getting
	 * @param <E>
	 *            The class the field is in
	 * @param classToAccess
	 *            The ".class" of the class the field is in
	 * @param instance
	 *            The instance of the class
	 * @param fieldNames
	 *            comma seperated names the field may have (i.e. obfuscated, non
	 *            obfuscated). Obfustated field names can be found in
	 *            fml/conf/fields.csv
	 * @return
	 */
	public static <T, E> T GetFieldByReflection(Class<? super E> classToAccess, E instance, String... fieldNames) {
		Field field = null;
		for (String fieldName : fieldNames) {
			try {
				field = classToAccess.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}

			if (field != null)
				break;
		}

		if (field != null) {
			field.setAccessible(true);
			T fieldT = null;
			try {
				fieldT = (T) field.get(instance);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}

			return fieldT;
		}

		return null;
	}

	public class FontCodes {
		// color codes for rendered strings
		public static final String BLACK = "\2470";
		public static final String DARK_BLUE = "\2471";
		public static final String DARK_GREEN = "\2472";
		public static final String DARK_AQUA = "\2473";
		public static final String DARK_RED = "\2474";
		public static final String DARK_PURPLE = "\2475";
		public static final String GOLD = "\2476";
		public static final String GRAY = "\2477";
		public static final String DARK_GREY = "\2478";
		public static final String BLUE = "\2479";
		public static final String GREEN = "\247a";
		public static final String AQUA = "\247b";
		public static final String RED = "\247c";
		public static final String LIGHT_PURPLE = "\247d";
		public static final String YELLOW = "\247e";
		public static final String WHITE = "\247f";

		// font styles
		public static final String OBFUSCATED = "\247k";
		public static final String BOLD = "\247l";
		public static final String STRIKETHROUGH = "\247m";
		public static final String UNDERLINE = "\247n";
		public static final String ITALICS = "\247o";

		public static final String RESET = "\247r";
	}
	
	
}
