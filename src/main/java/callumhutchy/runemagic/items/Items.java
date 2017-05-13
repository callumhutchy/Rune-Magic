package callumhutchy.runemagic.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Items {

	public static void init(){
		
	}
	
	
	public static void register(Item item){
		GameRegistry.register(item);
		Items.registerRenderers(item, 0);
	}
	
	public static void registerRenderers(Item item, int meta){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void registerCraftingRecipes(){
		
	}
	
}
