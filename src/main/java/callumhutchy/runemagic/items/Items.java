package callumhutchy.runemagic.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import callumhutchy.runemagic.utils.enums.Runes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Items {

	public static Map<String, Rune> runeMap = new HashMap();
	public static Map<String, Talisman> talismanMap = new HashMap();

	public static BasicMagicItem blankRune;

	public static Spellbook spellbook;
	public static TransplanarLexicon transplanarLexicon;
	
	public static void init() {
		blankRune = new BasicMagicItem("blankrune");
		
		spellbook = new Spellbook("spellbook");
		
		transplanarLexicon = new TransplanarLexicon("transplanarlexicon");

		Runes[] runes = Runes.values();
		for (Runes rune : runes) {
			String runeName = rune.toString().toLowerCase();
			talismanMap.put(runeName + "talisman", new Talisman(runeName + "talisman"));
			runeMap.put(runeName + "rune", new Rune(runeName + "rune"));
		}
		
		ArrayList<Map> maps = new ArrayList<Map>();
		
		maps.add(talismanMap);
		maps.add(runeMap);
		
		registerMaps(maps);
		
		register(blankRune);
		register(spellbook);
		register(transplanarLexicon);
	}
	
	public static void registerMaps(ArrayList<Map> m){
		for(Map t : m){
			Iterator<Map.Entry<String, Item>> entries = t.entrySet().iterator();
			while(entries.hasNext()){
				Map.Entry<String, Item> entry = entries.next();
				register(entry.getValue());
			}
		}
	}

	public static void register(Item item) {
		GameRegistry.register(item);
		Items.registerRenderers(item, 0);
	}

	public static void registerRenderers(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerCraftingRecipes() {

	}

}
