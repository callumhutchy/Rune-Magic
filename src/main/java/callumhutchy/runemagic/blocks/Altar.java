package callumhutchy.runemagic.blocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import callumhutchy.runemagic.RuneMagic;
import callumhutchy.runemagic.items.Items;
import callumhutchy.runemagic.references.AltarTiers;
import callumhutchy.runemagic.references.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Altar extends BasicBlockContainer {

	public Item item;
	public String[] tier;
	String altarName = "";
	int numberOfRunes = 0;
	public static TileEntity te;
	public static int blockTick = 0;
	String altarElement = "";
	
	private String tileEntityPackage = "callumhutchy."+ModInfo.MODID+".blocks.tileentities.altars.";
	
	public Altar(String unlocalisedName) {
		super(Material.ROCK, unlocalisedName);
		if(!unlocalisedName.contains("runealtar")){
			this.setCreativeTab(RuneMagic.tabRuneMagic);
		}
		
		altarName = unlocalisedName;
		if (unlocalisedName.toLowerCase().contains("runealtar")) {
			altarElement = unlocalisedName.replace("runealtar", "");
		} else {
			altarElement = unlocalisedName.replace("altar", "");
		}
		
		switch(altarElement){
		case "fire":
		case "earth":
			tier = AltarTiers.tier1;
			break;
		case "cosmic":
		case "nature":
		case "law":
			tier = AltarTiers.tier2;
			break;
		}
		
	}

	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.SOLID;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.8, 0.75);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		Constructor<TileEntity> constructor = null;

		ArrayList<String> altarNameArray = new ArrayList<String>();
		if (this.getUnlocalizedName().contains("rune")) {
			altarNameArray.add("runealtar");
			altarNameArray.add("RuneAltar");
		} else {
			altarNameArray.add("altar");
			altarNameArray.add("Altar");
		}

		String altarElementName = altarName.replace(altarNameArray.get(0), "");
		altarElementName = altarElementName.substring(0, 1).toUpperCase() + altarElementName.substring(1);
		Class clazz;
		try {
			clazz = Class.forName(tileEntityPackage + "TileEntity" + altarElementName + altarNameArray.get(1));
			constructor = clazz.getConstructor();
			return constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void SetTileEntity(TileEntity te) {
		this.te = te;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entity, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (blockTick == 0) {
			if (!world.isRemote) {
				Item item = entity.inventory.getCurrentItem().getItem();
				if (item == Items.blankRune && !altarName.contains("rune")) {
					int currentNumberOfRunes = entity.inventory.getCurrentItem().getCount();
					world.setBlockToAir(pos);
					Block runeAltar = null;
					Constructor<TileEntity> constructor = null;
					TileEntity customObject = null;
					ArrayList<String> altarNameArray = new ArrayList<String>();
					altarNameArray.add("altar");
					altarNameArray.add("runealtar");
					altarNameArray.add("RuneAltar");
					String altarElementName = altarName.replace(altarNameArray.get(0), "");
					altarElementName = altarElementName.substring(0, 1).toUpperCase() + altarElementName.substring(1);
					runeAltar = Block.getBlockFromName(
							ModInfo.MODID +":" + altarName.replace(altarNameArray.get(0), "") + altarNameArray.get(1));
					Class clazz;
					try {
						clazz = Class
								.forName(tileEntityPackage + "TileEntity" + altarElementName + altarNameArray.get(2));
						constructor = clazz.getConstructor();
						if (runeAltar != null) {
							world.setBlockState(pos, runeAltar.getDefaultState());
							Altar altar = (Altar) world.getBlockState(pos).getBlock();
							customObject = constructor.newInstance();
							altar.SetTileEntity(customObject);
							altar.numberOfRunes = currentNumberOfRunes;
						}
					} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
							| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					entity.inventory.removeStackFromSlot(entity.inventory.currentItem);
					item = null;
				} else if (item.getUnlocalizedName().contains("talisman") && altarName.contains("rune")) {
					Item talisman = item;
					String talismanElement = null;
					if (talisman.getUnlocalizedName().contains("talismanstaff")) {
						talismanElement = talisman.getUnlocalizedName().replace("talismanstaff", "").substring(5).trim(); 
					} else if (talisman.getUnlocalizedName().contains("talisman")) {
						talismanElement = talisman.getUnlocalizedName().replace("talisman", "").substring(5).trim();
					}
					if (altarElement.equals(talismanElement)) {
						Item rune = Item.getByNameOrId(ModInfo.MODID + ":" + talismanElement + "rune");
						world.setBlockToAir(pos);
						Block runeAltar = null;
						Constructor<TileEntity> constructor = null;
						TileEntity customObject = null;
						ArrayList<String> altarNameArray = new ArrayList<String>();
						altarNameArray.add("runealtar");
						altarNameArray.add("altar");
						altarNameArray.add("Altar");
						String altarElementName = altarName.replace(altarNameArray.get(0), "");
						altarElementName = altarElementName.substring(0, 1).toUpperCase()
								+ altarElementName.substring(1);
						runeAltar = Block.getBlockFromName(
								ModInfo.MODID +":" + altarName.replace(altarNameArray.get(0), "") + altarNameArray.get(1));
						
						try {
							Class clazz;
							clazz = Class.forName(
									tileEntityPackage + "TileEntity" + altarElementName + altarNameArray.get(2));
							constructor = clazz.getConstructor();
							if (runeAltar != null) {
								world.setBlockState(pos, runeAltar.getDefaultState());
								Altar altar = (Altar) world.getBlockState(pos).getBlock();
								customObject = constructor.newInstance();
								altar.SetTileEntity(customObject);
								world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(),
										new ItemStack(rune, this.numberOfRunes)));
								if (numberOfRunes >= 5) {
								    int randomint = randInt(0, 2);
									int randomRune = randInt(0, tier.length - 1);
									if(randomint > 0){
										Item bonusRunes = Item.getByNameOrId(ModInfo.MODID + ":" +tier[randomRune]+"rune");
										System.out.println(bonusRunes.getUnlocalizedName());
									    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(bonusRunes, randomint)));
									}
								    item = null;
								}
							}
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
								| SecurityException e) {
							e.printStackTrace();
						}
					}
				} else {
					if (altarName.contains("rune")) {
						world.setBlockToAir(pos);
						Block runeAltar = null;
						Constructor<TileEntity> constructor = null;
						TileEntity customObject = null;
						ArrayList<String> altarNameArray = new ArrayList<String>();
						altarNameArray.add("runealtar");
						altarNameArray.add("altar");
						altarNameArray.add("Altar");
						String altarElementName = altarName.replace(altarNameArray.get(0), "");
						altarElementName = altarElementName.substring(0, 1).toUpperCase()
								+ altarElementName.substring(1);
						runeAltar = Block.getBlockFromName(
								ModInfo.MODID +":" + altarName.replace(altarNameArray.get(0), "") + altarNameArray.get(1));
						Class clazz;
						try {
							clazz = Class.forName(
									tileEntityPackage + "TileEntity" + altarElementName + altarNameArray.get(2));
							constructor = clazz.getConstructor();
							if (runeAltar != null) {
								world.setBlockState(pos, runeAltar.getDefaultState());
								Altar altar = (Altar) world.getBlockState(pos).getBlock();
								customObject = constructor.newInstance();
								altar.SetTileEntity(customObject);
								world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(),
										new ItemStack(Items.blankRune, this.numberOfRunes)));
							}
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException | NoSuchMethodException
								| SecurityException e) {
							e.printStackTrace();
						}
					}
				}
				blockTick = 5;
			}
			return true;
		} else {
			return false;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
}
