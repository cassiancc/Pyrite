package cc.cassian.pyrite;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.ArrayList;
import java.util.Objects;

import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.ModLists.*;
import cc.cassian.pyrite.blocks.*;

public class Pyrite
{
	public static final String MOD_ID = "pyrite";
	//List of Blocks and Block IDS.
	public static ArrayList<Block> pyriteBlocks = new ArrayList<>();
	public static ArrayList<Item> pyriteItems = new ArrayList<>();
	public static ArrayList<String> pyriteBlockIDs = new ArrayList<>();
	public static ArrayList<String> pyriteItemIDs = new ArrayList<>();
	//Lists of generated material.
	static String[] dyes = getDyes();
	static Block[] vanillaWood = getVanillaWood();
	static Block[] walls_blocks = getVanillaWalls();
	static Block[] resource_blocks = getVanillaResourceBlocks();
	public static void generateResourceBlocks() {
		for (Block resourceBlock : resource_blocks) {
			String block = findVanillaBlockID(resourceBlock);
			//If the block provided isn't a wall block, add the wall tag.
			if (block.contains("block")) {
				block = block.substring(0, block.indexOf("_block"));
			}
			createResourceBlockSet(block, resourceBlock);
		}
	}
	public static void generateVanillaCraftingTables() {
		//Autogenerate Vanilla Crafting Tables
		for (Block plankBlock : vanillaWood) {
			//Find block ID
			String block = findVanillaBlockID(plankBlock);
			//If the block provided isn't a wall block, add the wall tag.
			if (block.contains("planks")) {
				block = block.substring(0, block.indexOf("_planks"));
			}
			//Create block.
			createPyriteBlock(block + "_crafting_table","crafting", plankBlock);
		}
	}

	//Primarily used for Framed Glass, Glowstone/Dyed Lamps, Glowing Obsidian
	public static void createPyriteBlock(String blockID, String blockType, Float strength, MapColor color, int lightLevel) {
		BlockBehaviour.Properties settings = BlockBehaviour.Properties.of().strength(strength).lightLevel(state -> lightLevel).mapColor(color);
		if (Objects.equals(blockType, "obsidian")) {
			addPyriteBlock(blockID, "block", settings.strength(strength, 1200f).pushReaction(PushReaction.BLOCK));
		}
		else {
			addPyriteBlock(blockID, blockType, settings);
		}
	}

	//Create and then add carpets
	private static void createCarpet(String blockID) {
		BlockBehaviour.Properties blockSettings = copyBlock(Blocks.MOSS_CARPET);
		addPyriteBlock(blockID, "carpet", blockSettings);
	}

	//Create and then add most of the manually generated blocks.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock) {
		BlockBehaviour.Properties blockSettings = copyBlock(copyBlock);
		switch (blockType) {
			case "fence_gate":
				addPyriteBlock(blockID, blockSettings, WoodType.CRIMSON);
				break;
			case "door", "trapdoor":
				addPyriteBlock(blockID, blockType, blockSettings, BlockSetType.IRON);
				break;
			default:
				addPyriteBlock(blockID, blockType, blockSettings);
				break;
		}
	}

	//Create a slab from the last block added.
	public static void createStair(String blockID) {
		BlockBehaviour.Properties blockSettings = copyBlock(getLastBlock());
		addPyriteBlock(blockID+"_stairs", getLastBlock(), blockSettings);
	}

	//Create a slab from the last block added.
	public static void createSlab(String blockID) {
		BlockBehaviour.Properties blockSettings = copyBlock(getLastBlock());
		addPyriteBlock(blockID+"_slab", "slab", blockSettings);
	}

	//Create blocks that require a change in light level, e.g. Locked Chests
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux) {
		BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).lightLevel(parseLux(lux));
		addPyriteBlock(blockID, blockType, blockSettings);
	}

	//Create blocks that require a Block Set.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set) {
		addPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
	}

	//Create most of the generic Stained Blocks, then add them.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux) {
		BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).mapColor(color).lightLevel(parseLux(lux));
		if (Objects.equals(blockType, "stairs")) {
			addPyriteBlock(blockID, copyBlock, blockSettings);
		}
		else {
			addPyriteBlock(blockID, blockType, blockSettings);
		}
	}

	//Create basic blocks.
	public static void createPyriteBlock(String blockID, Block copyBlock) {
		BlockBehaviour.Properties blockSettings = copyBlock(copyBlock);
		addPyriteBlock(blockID, "block", blockSettings);
	}

	//Add most Pyrite blocks.
	public static void addPyriteBlock(String blockID, String blockType, BlockBehaviour.Properties blockSettings) {
		pyriteBlockIDs.add(blockID);
		int power;
		if (blockID.contains("redstone")) {
			power = 15;
		}
		else {
			power = 0;
		}
		switch (blockType.toLowerCase()) {
			case "block":
				pyriteBlocks.add(new ModBlock(blockSettings, power));
				break;
			case "crafting":
				pyriteBlocks.add(new ModCraftingTable(blockSettings));
//				FuelRegistry.INSTANCE.add(getLastBlock(), 300);
				break;
			case "carpet":
				pyriteBlocks.add(new ModCarpet(blockSettings));
				break;
			case "slab":
				pyriteBlocks.add(new ModSlab(blockSettings, power));
				break;
			case "wall":
				pyriteBlocks.add(new ModWall(blockSettings, power));
				break;
			case "fence":
				pyriteBlocks.add(new FenceBlock(blockSettings));
				break;
			case "log":
				pyriteBlocks.add(new ModPillar(blockSettings, power));
				break;
			case "facing":
				pyriteBlocks.add(new ModFacingBlock(blockSettings, power));
				break;
			case "bars", "glass_pane":
				pyriteBlocks.add(new ModPane(blockSettings, power));
				addTransparentBlock();
				break;
			case "glass":
				pyriteBlocks.add(new ModGlass(blockSettings));
				addTransparentBlock();
				break;
			case "gravel":
				pyriteBlocks.add(new FallingBlock(blockSettings));
				break;
			case "flower":
				pyriteBlocks.add(new FlowerBlock(MobEffects.NIGHT_VISION, 5, blockSettings));
				addTransparentBlock();
				break;
			default:
				System.out.println(blockID + "created as a generic block, block provided" + blockType);
				pyriteBlocks.add(new Block(blockSettings));
				break;
		}
	}

	//Add Pyrite blocks that require Wood Types - Fence gates.
	public static void addPyriteBlock(String blockID, BlockBehaviour.Properties blockSettings, WoodType type) {
		pyriteBlockIDs.add(blockID);
		pyriteBlocks.add(new FenceGateBlock(blockSettings, type));
	}

	//Add Pyrite blocks that require Block Sets.
	public static void addPyriteBlock(String blockID, String blockType, BlockBehaviour.Properties blockSettings, BlockSetType type) {
		pyriteBlockIDs.add(blockID);
		switch (blockType) {
			case "door":
				pyriteBlocks.add(new ModDoor(blockSettings.noOcclusion(), type));
				addTransparentBlock();
				break;
			case "trapdoor":
				pyriteBlocks.add(new ModTrapDoor(blockSettings.noOcclusion(), type));
				addTransparentBlock();
				break;
			case "button":
				pyriteBlocks.add(new ModWoodenButton(blockSettings, type));
				break;
			case "pressure_plate":
				pyriteBlocks.add(new ModPressurePlate(blockSettings, type));
				break;
			default:
				System.out.println(blockID + "created as a generic block.");
				pyriteBlocks.add(new Block(blockSettings));
				break;
		}
	}

	//Add Pyrite Stair blocks.
	public static void addPyriteBlock(String blockID, Block copyBlock, BlockBehaviour.Properties blockSettings) {
		pyriteBlockIDs.add(blockID);
		pyriteBlocks.add(new ModStairs(copyBlock.defaultBlockState(), blockSettings));
	}

	//Create Stained blocks that require a wood set or wood type, then add them.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type) {
		BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).mapColor(color).lightLevel(parseLux(lux));
		switch (blockType) {
			case "door", "trapdoor", "button", "pressure_plate":
				addPyriteBlock(blockID, blockType, blockSettings, set);
				break;
			case "fence_gate":
				addPyriteBlock(blockID, blockSettings, type);
				break;
			default:
				addPyriteBlock(blockID, blockType, blockSettings);
				break;
		}
	}

	//Create and add Pyrite items.
	public static void createPyriteItem(String itemID) {
		pyriteItems.add(new Item(new Item.Properties()));
		pyriteItemIDs.add(itemID);
	}

	//Generate an entire brick set.
	public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, int lux) {
		//Bricks
		createPyriteBlock( blockID+"s", "block", copyBlock, color, lux);
		//Brick Stairs
		createPyriteBlock( blockID+"_stairs", "stairs", getLastBlock(), color, lux);
		//Brick Slab
		createPyriteBlock( blockID+"_slab", "slab", copyBlock, color, lux);
		//Brick Wall
		createPyriteBlock( blockID+"_wall", "wall", copyBlock, color, lux);
		//Brick Wall Gate
		createPyriteBlock(blockID+"_wall_gate","fence_gate", copyBlock);
	}

	//Generate a block and its slab and stair variants.
	public static void generateGrassTurfSet(String blockID, Block copyBlock) {
		createPyriteBlock( blockID+"_turf", "block", copyBlock);
		addGrassBlock();
		createStair(blockID);
		addGrassBlock();
		createSlab(blockID);
		addGrassBlock();
		createCarpet(blockID+"_carpet");
		addGrassBlock();
	}
	//Generate a block and its slab and stair variants.
	public static void createTurfSet(String blockID, Block copyBlock) {
		createPyriteBlock( blockID+"_turf", "block", copyBlock);
		createStair(blockID);
		createSlab(blockID);
		createCarpet(blockID+"_carpet");
	}

	//Generate an entire wood set.
	public static void createWoodSet(String blockID, MapColor color, int blockLux) {
		BlockSetType GENERATED_SET = BlockSetType.BAMBOO;
		WoodType GENERATED_TYPE = WoodType.BAMBOO;
		//Stained Planks
		createPyriteBlock( blockID+"_planks", "block", Blocks.OAK_PLANKS, color, blockLux);
		//Stained Stairs
		createPyriteBlock(blockID+"_stairs", "stairs", getLastBlock(), color, blockLux);
		//Stained Slabs
		createPyriteBlock( blockID+"_slab", "slab", getLastBlock(2), color, blockLux);
		//Stained Pressure Plates
		createPyriteBlock( blockID+"_pressure_plate", "pressure_plate", getLastBlock(3), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Buttons
		createPyriteBlock(blockID+"_button", "button", getLastBlock(4), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Fences
		createPyriteBlock(blockID+"_fence", "fence", getLastBlock(5), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Fence Gates
		createPyriteBlock(blockID+"_fence_gate", "fence_gate", getLastBlock(5), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Doors
		createPyriteBlock(blockID+"_door", "door", getLastBlock(6), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Trapdoors
		createPyriteBlock(blockID+"_trapdoor", "trapdoor", getLastBlock(7), color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Crafting Tables
		createPyriteBlock( blockID+"_crafting_table", "crafting", Blocks.CRAFTING_TABLE, color, blockLux);

	}

	//Generate an entire Cut Block set.
	public static void createCutBlocks(String blockID, Block block) {
		String cutBlockID = "cut_" + blockID;
		if (!blockID.contains("copper")) {
			//Cut Block
			createPyriteBlock(cutBlockID, block);
			//Cut Stairs
			createStair(cutBlockID);
			//Cut Slab
			createSlab(cutBlockID);
		}
		//Cut Wall
		createPyriteBlock(cutBlockID+"_wall", "wall", block);
		//Cut Wall Gate
		createPyriteBlock(cutBlockID+"_wall_gate","fence_gate", block);
	}

	//Generate an entire Smooth Block set.
	public static void createSmoothBlocks(String blockID, Block block) {
		String smoothBlockID = "smooth_" + blockID;
		if (!Objects.equals(blockID, "quartz")) {
			//Smooth Block
			createPyriteBlock(smoothBlockID, block);
			//Smooth Stairs
			createStair(smoothBlockID);
			//Smooth Slab
			createSlab(smoothBlockID);
		}
		//Smooth Wall
		createPyriteBlock(smoothBlockID+"_wall", "wall", block);
		//Smooth Wall Gate
		createPyriteBlock(smoothBlockID+"_wall_gate","fence_gate", block);
	}

	//Create a set of Resource Blocks
	public static void createResourceBlockSet(String blockID, Block block) {
		//Create Cut Blocks for those that don't already exist (Copper)
		createCutBlocks(blockID, block);
		//Create Bricks/Chiseled/Pillar/Smooth for those that don't already exist (Quartz)
		if (!Objects.equals(blockID, "quartz")) {
			//Brick Blocks
			createPyriteBlock(blockID+"_bricks", block);
			//Chiseled Blocks
			createPyriteBlock("chiseled_"+blockID+"_block", "log", block);
			//Pillar Blocks
			createPyriteBlock(blockID+"_pillar", "log", block);
		}
		//Smooth Blocks
		createSmoothBlocks(blockID, block);
		createPyriteBlock("nostalgia_"+blockID+"_block", block);
		//Block set for modded blocks
		boolean openByHand = !Objects.equals(blockID, "emerald") && (!Objects.equals(blockID, "netherite") && (!Objects.equals(blockID, "diamond")));
		BlockSetType set = BlockSetType.GOLD;
		//Create Bars/Doors/Trapdoors/Plates for those that don't already exist (Iron)
		if (!Objects.equals(blockID, "iron")) {
			//Bars
			createPyriteBlock(blockID+"_bars","bars", block);
			createPyriteBlock(blockID+"_door","door", block, set);
			createPyriteBlock(blockID+"_trapdoor","trapdoor", block, set);
			//Create Plates for those that don't already exist (Iron and Gold)
			if (!Objects.equals(blockID, "gold")) {
				createPyriteBlock(blockID+"_pressure_plate","pressure_plate", block, set);
			}
		}
		//Create buttons for all blocks.
		createPyriteBlock(blockID+"_button","button", block, set);
	}

	public static void init() {
//Framed Glass
		createPyriteBlock("framed_glass","glass", 2.0f, MapColor.NONE, 0);
		//Framed Glass Pane
		createPyriteBlock( "framed_glass_pane","glass_pane", 2.0f, MapColor.NONE, 0);
		//Cobblestone Bricks
		generateBrickSet("cobblestone_brick", Blocks.COBBLESTONE, MapColor.STONE, 0);
		//Mossy Cobblestone Bricks
		generateBrickSet("mossy_cobblestone_brick", Blocks.MOSSY_COBBLESTONE, MapColor.STONE, 0);
		generateBrickSet("smooth_stone_brick", Blocks.COBBLESTONE, MapColor.STONE, 0);
		//Grass Set
		generateGrassTurfSet("grass", Blocks.GRASS_BLOCK);
		//Mycelium Set
		createTurfSet("mycelium", Blocks.MYCELIUM);
		//Podzol Set
		createTurfSet("podzol", Blocks.PODZOL);
		//Path Set
		createTurfSet("path", Blocks.DIRT_PATH);
		//Nether Brick Fence Gate
		createPyriteBlock("nether_brick_fence_gate","fence_gate", Blocks.NETHER_BRICK_FENCE);
		//Resource Blocks
		generateResourceBlocks();
		//Lamps
		createPyriteBlock("lit_redstone_lamp","block", 0.3f, MapColor.COLOR_ORANGE, 15);
		createPyriteBlock("glowstone_lamp","block", 0.3f, MapColor.COLOR_YELLOW, 15);
		//Classic Features
		createPyriteBlock("glowing_obsidian","obsidian", 50f, MapColor.COLOR_RED, 15);
		createPyriteBlock("nostalgia_glowing_obsidian","obsidian", 50f, MapColor.COLOR_RED, 15);
		createPyriteBlock("locked_chest", "facing", Blocks.CHEST, 15);
		createPyriteBlock("nostalgia_grass_block", Blocks.GRASS_BLOCK);
		createTurfSet("nostalgia_grass", getLastBlock());
		createPyriteBlock("nostalgia_cobblestone", Blocks.COBBLESTONE);
		createPyriteBlock("nostalgia_mossy_cobblestone", Blocks.MOSSY_COBBLESTONE);
		createPyriteBlock("nostalgia_gravel", "gravel", Blocks.GRAVEL);
		createPyriteBlock("nostalgia_netherrack", Blocks.NETHERRACK);
		//Classic Flowers
		createPyriteBlock("rose", "flower", Blocks.POPPY);
		createPyriteBlock("orange_rose", "flower", Blocks.POPPY);
		createPyriteBlock("white_rose", "flower", Blocks.POPPY);
		createPyriteBlock("pink_rose", "flower", Blocks.POPPY);
		createPyriteBlock("blue_rose", "flower", Blocks.POPPY);
		createPyriteBlock("paeonia", "flower", Blocks.PEONY);
		createPyriteBlock("buttercup", "flower", Blocks.DANDELION);
		createPyriteBlock("pink_daisy", "flower", Blocks.PINK_TULIP);
		//Charred Nether Bricks
		generateBrickSet("charred_nether_brick", Blocks.NETHER_BRICKS, MapColor.COLOR_BLACK, 0);
		//Blue Nether Bricks
		generateBrickSet("blue_nether_brick", Blocks.NETHER_BRICKS, MapColor.COLOR_BLUE, 0);
		//Vanilla Crafting Tables
		generateVanillaCraftingTables();
		//Red Mushroom Blocks
		createPyriteBlock("red_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
		createWoodSet("red_mushroom", MapColor.COLOR_RED, 0);
		//Brown Mushroom Blocks
		createPyriteBlock("brown_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
		createWoodSet("brown_mushroom", MapColor.COLOR_BROWN, 0);
		//Autogenerate dye blocks.

		for (int dyeIndex = 0; dyeIndex < dyes.length; dyeIndex++) {
			String dye = dyes[dyeIndex];
			int blockLux = checkDyeLux(dye);
			MapColor color = checkDyeMapColour(dye);
			if (dyeIndex > 15) {
				//Dye items.
				createPyriteItem(dye + "_dye");
				//Dyed Wool
				createPyriteBlock(dye + "_wool", "block", Blocks.WHITE_WOOL, color, blockLux);
				//Terracotta Block
				//coming soon - createPyriteBlock(dye+"_terracotta", "block", Blocks.TERRACOTTA,color, blockLux);
				//Glazed Terracotta Block
				//coming soon - createPyriteBlock(dye+"_glazed_terracotta", "block", Blocks.TERRACOTTA,color, blockLux);
				//Concrete Powder Block
				//coming soon - createPyriteBlock(dye+"_concrete", "block", Blocks.TERRACOTTA,color, blockLux);
				//Concrete Block
				//coming soon - createPyriteBlock(dye+"_concrete", "block", Blocks.CONCRETE,color, blockLux);
				//Carpet block
				createPyriteBlock(dye + "_carpet", "carpet", Blocks.WHITE_WOOL, color, blockLux);
			}
			//Planks and plank products
			createWoodSet(dye + "_stained", color, blockLux);
			//Bricks and brick products
			generateBrickSet(dye + "_brick", Blocks.BRICKS, color, blockLux);
			//Dyed Lamps
			createPyriteBlock(dye + "_lamp","block", 0.3f, color, 15);

		}
		//Autogenerate Wall Gates
		for (Block wallsBlock : walls_blocks) {
			//Find block ID
			String block = findVanillaBlockID(wallsBlock);
			//If the block provided isn't a wall block, add the wall tag.
			if (!block.contains("wall")) {
				block = block + "_wall";
			}
			//Create block.
			createPyriteBlock(block + "_gate","fence_gate", wallsBlock);
		}

//
//		//Register blocks and block items.
//		for (int x = 0; x < pyriteBlockIDs.size(); x++) {
//			Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Pyrite.MOD_ID, pyriteBlockIDs.get(x)), pyriteBlocks.get(x));
//			Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Pyrite.MOD_ID, pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new Item.Properties()));
//		}
//		//Registers items.
//		for (int x = 0; x < pyriteItemIDs.size(); x++) {
//			Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Pyrite.MOD_ID, pyriteItemIDs.get(x)), pyriteItems.get(x));
//		}
		//Registers the Pyrite item group.
//		Registry.register(Registries.ITEM, new ResourceLocation(modID, "pyrite_group"), PYRITE_GROUP);

	}

}
