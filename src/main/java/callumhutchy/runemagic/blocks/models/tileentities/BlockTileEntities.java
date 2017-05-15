package callumhutchy.runemagic.blocks.models.tileentities;

import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityAirAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityAirRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityBloodAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityBloodRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityChaosAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityChaosRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityCosmicAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityCosmicRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityDeathAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityDeathRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityEarthAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityEarthRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityFireAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityFireRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityLawAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityLawRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityNatureAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityNatureRuneAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityWaterAltar;
import callumhutchy.runemagic.blocks.models.tileentities.altars.TileEntityWaterRuneAltar;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockTileEntities {

	public static void init(){
		//Altars (base)
		GameRegistry.registerTileEntity(TileEntityAirAltar.class, "airaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityBloodAltar.class, "bloodaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityChaosAltar.class, "chaosaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityCosmicAltar.class, "cosmicaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityDeathAltar.class, "deathaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityEarthAltar.class, "earthaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityFireAltar.class, "firealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityLawAltar.class, "lawaltar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityNatureAltar.class, "naturealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityWaterAltar.class, "wateraltar_tile_entity");
		
		//Altars (rune)
		GameRegistry.registerTileEntity(TileEntityAirRuneAltar.class, "airrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityBloodRuneAltar.class, "bloodrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityChaosRuneAltar.class, "chaosrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityCosmicRuneAltar.class, "cosmicrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityDeathRuneAltar.class, "deathrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityEarthRuneAltar.class, "earthrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityFireRuneAltar.class, "firerunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityLawRuneAltar.class, "lawrunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityNatureRuneAltar.class, "naturerunealtar_tile_entity");
		GameRegistry.registerTileEntity(TileEntityWaterRuneAltar.class, "waterrunealtar_tile_entity");
		
		
	}
	
}
