package cc.cassian.pyrite;

import cc.cassian.pyrite.blocks.*;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

import static cc.cassian.pyrite.functions.ModLists.*;
import static cc.cassian.pyrite.functions.ModHelpers.*;

public class Pyrite {
	public final static String modID = "pyrite";
	//List of Blocks and Block IDS.
	//Lists of generated material.
    final static String[] dyes = getDyes();
	final static Block[] vanillaWood = getVanillaWood();
	final static Block[] walls_blocks = getVanillaWalls();
	final static Block[] resource_blocks = getVanillaResourceBlocks();
	//Deferred registry entries
	public static final DeferredRegister<Block> pyriteBlocks = DeferredRegister.create(modID, RegistryKeys.BLOCK);
	public static final DeferredRegister<Item> pyriteItems = DeferredRegister.create(modID, RegistryKeys.ITEM);

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
		AbstractBlock.Settings settings = AbstractBlock.Settings.create().strength(strength).luminance(state -> lightLevel).mapColor(color);
		if (Objects.equals(blockType, "obsidian")) {
			addPyriteBlock(blockID, "block", settings.strength(strength, 1200f).pistonBehavior(PistonBehavior.BLOCK));
		}
		else {
			addPyriteBlock(blockID, blockType, settings);
		}
	}

	//Create and then add carpets
	private static void createCarpet(String blockID) {
		AbstractBlock.Settings blockSettings = copyBlock(Blocks.MOSS_CARPET);
		addPyriteBlock(blockID, "carpet", blockSettings);
	}

	//Create and then add most of the manually generated blocks.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
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
	public static void createStair(String blockID, Block copyBlock) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
		addPyriteBlock(blockID+"_stairs", copyBlock, blockSettings);
	}

	//Create a slab from the last block added.
	public static void createSlab(String blockID, Block copyBlock) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
		addPyriteBlock(blockID+"_slab", "slab", blockSettings);
	}

	//Create blocks that require a change in light level, e.g. Locked Chests
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock).luminance(parseLux(lux));
		addPyriteBlock(blockID, blockType, blockSettings);
	}

	//Create blocks that require a Block Set.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set) {
		addPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
	}

	//Create most of the generic Stained Blocks, then add them.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
		if (Objects.equals(blockType, "stairs")) {
			addPyriteBlock(blockID, copyBlock, blockSettings);
		}
		else {
			addPyriteBlock(blockID, blockType, blockSettings);
		}
	}

	//Create basic blocks.
	public static void createPyriteBlock(String blockID, Block copyBlock) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
		addPyriteBlock(blockID, "block", blockSettings);
	}

	//Add most Pyrite blocks.
	public static void addPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings) {
		RegistrySupplier<Block> newBlock;
		int power;
		if (blockID.contains("redstone")) {
			power = 15;
		}
		else {
			power = 0;
		}

		switch (blockType.toLowerCase()) {
			case "block":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModBlock(blockSettings, power));
				break;
			case "crafting":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModCraftingTable(blockSettings));
				fuel.put(newBlock, 300);
				break;
			case "carpet":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModCarpet(blockSettings));
				break;
			case "slab":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModSlab(blockSettings, power));
				break;
			case "wall":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModWall(blockSettings, power));
				break;
			case "fence":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new FenceBlock(blockSettings));
				break;
			case "log":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModPillar(blockSettings, power));
				break;
			case "facing":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModFacingBlock(blockSettings, power));
				break;
			case "bars", "glass_pane":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModPane(blockSettings, power));
				addTransparentBlock(newBlock);
				break;
			case "glass":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModGlass(blockSettings));
				addTransparentBlock(newBlock);
				break;
			case "gravel":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new GravelBlock(blockSettings));
				break;
			case "flower":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new FlowerBlock(StatusEffects.NIGHT_VISION, 5, blockSettings));
				addTransparentBlock(newBlock);
				break;
			default:
				System.out.println(blockID + "created as a generic block, block provided" + blockType);
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new Block(blockSettings));
				break;
		}
		addBlockItem(newBlock);
		if (blockID.contains("grass")) {
			addGrassBlock(newBlock);
		}
		else if (blockID.equals("cobblestone_bricks")) {
			creativeTabIcon = newBlock;
		}
		else {
			addGrassBlock(newBlock);
		}

	}
	static RegistrySupplier<Block> creativeTabIcon;
	//Add Pyrite blocks that require Wood Types - Fence gates.
	public static void addPyriteBlock(String blockID, AbstractBlock.Settings blockSettings, WoodType type) {
		RegistrySupplier<Block> newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new FenceGateBlock(blockSettings, type));
		addBlockItem(newBlock);
	}

	//Add Pyrite blocks that require Block Sets.
	public static void addPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings, BlockSetType type) {

		RegistrySupplier<Block> newBlock;
		switch (blockType) {
			case "door":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new DoorBlock(blockSettings.nonOpaque(), type));
				addTransparentBlock(newBlock);
				break;
			case "trapdoor":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new TrapdoorBlock(blockSettings.nonOpaque(), type));
				addTransparentBlock(newBlock);
				break;
			case "button":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModWoodenButton(blockSettings, type));
				break;
			case "pressure_plate":
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModPressurePlate(blockSettings, type));
				break;
			default:
				System.out.println(blockID + "created as a generic block.");
				newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new Block(blockSettings));
				break;
		}
		addBlockItem(newBlock);
	}

	//Add Pyrite Stair blocks.
	public static void addPyriteBlock(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings) {
		RegistrySupplier<Block> newBlock = pyriteBlocks.register(new Identifier(modID, blockID), () -> new ModStairs(copyBlock.getDefaultState(), blockSettings));
		addBlockItem(newBlock);
		if (blockID.contains("grass")) {
			addGrassBlock(newBlock);
		}
		else {
			addGrassBlock(newBlock);
		}
	}

	//Create Stained blocks that require a wood set or wood type, then add them.
	public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type) {
		AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
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
		pyriteItems.register(new Identifier(modID, itemID), () -> (new Item(new Item.Settings())));
	}

	//Generate an entire brick set.
	public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, int lux) {
		//Bricks
		createPyriteBlock( blockID+"s", "block", copyBlock, color, lux);
		//Brick Stairs
		createPyriteBlock( blockID+"_stairs", "stairs", copyBlock, color, lux);
		//Brick Slab
		createPyriteBlock( blockID+"_slab", "slab", copyBlock, color, lux);
		//Brick Wall
		createPyriteBlock( blockID+"_wall", "wall", copyBlock, color, lux);
		//Brick Wall Gate
		createPyriteBlock(blockID+"_wall_gate","fence_gate", copyBlock);
	}


	//Generate a Turf block set - including block and its slab, stair, and carpet variants.
	public static void createTurfSet(String blockID, Block copyBlock) {
		createPyriteBlock( blockID+"_turf", "block", copyBlock);
		createStair(blockID, copyBlock);
		createSlab(blockID, copyBlock);
		createCarpet(blockID+"_carpet");
	}

	//Generate an entire wood set.
	public static void createWoodSet(String blockID, MapColor color, int blockLux) {
		BlockSetType GENERATED_SET = new BlockSetType(blockID);
		WoodType GENERATED_TYPE = new WoodType(blockID, GENERATED_SET);
		//Stained Planks
		createPyriteBlock( blockID+"_planks", "block", Blocks.OAK_PLANKS, color, blockLux);
		//Stained Stairs
		createPyriteBlock(blockID+"_stairs", "stairs",Blocks.OAK_STAIRS, color, blockLux);
		//Stained Slabs
		createPyriteBlock( blockID+"_slab", "slab", Blocks.OAK_SLAB, color, blockLux);
		//Stained Pressure Plates
		createPyriteBlock( blockID+"_pressure_plate", "pressure_plate", Blocks.OAK_PRESSURE_PLATE, color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Buttons
		createPyriteBlock(blockID+"_button", "button", Blocks.OAK_BUTTON, color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Fences
		createPyriteBlock(blockID+"_fence", "fence", Blocks.OAK_FENCE, color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Fence Gates
		createPyriteBlock(blockID+"_fence_gate", "fence_gate", Blocks.OAK_FENCE_GATE, color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Doors
		createPyriteBlock(blockID+"_door", "door", Blocks.OAK_DOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE);
		//Stained Trapdoors
		createPyriteBlock(blockID+"_trapdoor", "trapdoor", Blocks.OAK_TRAPDOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE);
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
			createStair(cutBlockID, block);
			//Cut Slab
			createSlab(cutBlockID, block);
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
			createStair(smoothBlockID, block);
			//Smooth Slab
			createSlab(smoothBlockID, block);
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
		BlockSetType set = getBlockSetType(blockID);
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

	private static @NotNull BlockSetType getBlockSetType(String blockID) {
		boolean openByHand = !Objects.equals(blockID, "emerald") && (!Objects.equals(blockID, "netherite") && (!Objects.equals(blockID, "diamond")));
        return new BlockSetType(blockID, openByHand, BlockSoundGroup.METAL, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON);
	}

	public static final DeferredRegister<ItemGroup> pyriteTabs =
			DeferredRegister.create(modID, RegistryKeys.ITEM_GROUP);

	public static final RegistrySupplier<ItemGroup> PYRITE_GROUP = pyriteTabs.register(
			"pyrite", // Tab ID
			() -> CreativeTabRegistry.create(
					Text.translatable("itemGroup.pyrite.group"), // Tab Name
					() -> new ItemStack(creativeTabIcon.get()) // Icon
			)
	);

	public static void init() {
		//Framed Glass
		createPyriteBlock("framed_glass","glass", 2.0f, MapColor.CLEAR, 0);
		//Framed Glass Pane
		createPyriteBlock( "framed_glass_pane","glass_pane", 2.0f, MapColor.CLEAR, 0);
		//Cobblestone Bricks
		generateBrickSet("cobblestone_brick", Blocks.COBBLESTONE, MapColor.STONE_GRAY, 0);
		//Mossy Cobblestone Bricks
		generateBrickSet("mossy_cobblestone_brick", Blocks.MOSSY_COBBLESTONE, MapColor.STONE_GRAY, 0);
		generateBrickSet("smooth_stone_brick", Blocks.COBBLESTONE, MapColor.STONE_GRAY, 0);
		//Grass Set
		createTurfSet("grass", Blocks.GRASS_BLOCK);
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
		createPyriteBlock("lit_redstone_lamp","block", 0.3f, MapColor.ORANGE, 15);
		createPyriteBlock("glowstone_lamp","block", 0.3f, MapColor.YELLOW, 15);
		//Classic Features
		createPyriteBlock("glowing_obsidian","obsidian", 50f, MapColor.RED, 15);
		createPyriteBlock("nostalgia_glowing_obsidian","obsidian", 50f, MapColor.RED, 15);
		createPyriteBlock("locked_chest", "facing", Blocks.CHEST, 15);
		createPyriteBlock("nostalgia_grass_block", Blocks.GRASS_BLOCK);
		createTurfSet("nostalgia_grass", Blocks.GRASS_BLOCK);
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
		generateBrickSet("charred_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLACK, 0);
		//Blue Nether Bricks
		generateBrickSet("blue_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLUE, 0);
		//Vanilla Crafting Tables
		generateVanillaCraftingTables();
		//Red Mushroom Blocks
		createPyriteBlock("red_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
		createWoodSet("red_mushroom", MapColor.RED, 0);
		//Brown Mushroom Blocks
		createPyriteBlock("brown_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
		createWoodSet("brown_mushroom", MapColor.BROWN, 0);
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

		pyriteBlocks.register();
		pyriteItems.register();
		pyriteTabs.register();

	}


}