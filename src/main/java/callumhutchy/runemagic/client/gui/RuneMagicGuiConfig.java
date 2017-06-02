package callumhutchy.runemagic.client.gui;

import java.util.List;

import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.utils.handlers.ConfigurationHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class RuneMagicGuiConfig extends GuiConfig {

	public RuneMagicGuiConfig(GuiScreen parentScreen, List<IConfigElement> configElements, String modID,
			boolean allRequireWorldRestart, boolean allRequireMcRestart, String title) {
		super(parentScreen, configElements, modID, allRequireWorldRestart, allRequireMcRestart, title);
		// TODO Auto-generated constructor stub
	}
	
	public RuneMagicGuiConfig(GuiScreen parentScreen){
		super(parentScreen, new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),ModInfo.MODID, false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}

}
