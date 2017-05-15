package callumhutchy.runemagic.blocks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import callumhutchy.runemagic.references.ModInfo;
import callumhutchy.runemagic.utils.enums.Runes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blocks {

	public static Map<String, Altar> altarMap = new HashMap<String, Altar>();
	
	public static void init(){
		
		Runes[] runes = Runes.values();
		for(Runes rune : runes){
			String runeName = rune.toString().toLowerCase();
			altarMap.put(runeName+"altar", new Altar(runeName+"altar"));
			altarMap.put(runeName+"runealtar", new Altar(runeName+"runealtar"));
		}
		
		
		registerMap(altarMap);
		
	}
	
	private static void register(Block block){
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getUnlocalizedName().substring(5)));
		Blocks.registerRenderers(block, 0);
	}
	
	private static void registerMap(Map t){
		Iterator<Map.Entry<String, Block>> entries = t.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<String, Block> entry = entries.next();
			register(entry.getValue());
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers(Block block, int meta){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(ModInfo.MODID + ":"+ block.getUnlocalizedName().substring(5),"inventory"));
	}
	
}
