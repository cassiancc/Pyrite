package cc.cassian.pyrite;

import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import net.minecraft.block.*;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static cc.cassian.pyrite.registry.BlockCreator.*;
import static cc.cassian.pyrite.functions.ModLists.*;
import static cc.cassian.pyrite.functions.ModHelpers.*;

public class Pyrite {
	public static final String MOD_ID = "pyrite";
	public static final Logger LOGGER = LogManager.getLogger("Pyrite");

    public static void init() {
		ModLists.populateLinkedHashMaps();
		// Framed Glass
		createPyriteBlock("framed_glass","glass", 2.0f, MapColor.CLEAR, 0, "framed_glass");
		// Framed Glass Pane
		createPyriteBlock( "framed_glass_pane","glass_pane", 2.0f, MapColor.CLEAR, 0, "framed_glass_pane");
		// Switchable Glass
		createPyriteBlock("switchable_glass", "switchable_glass", Blocks.GLASS, "redstone-group");
		// Cobblestone Bricks
		generateBrickSet("cobblestone_brick", Blocks.COBBLESTONE, MapColor.STONE_GRAY, true);
		generateBrickSet("cobbled_deepslate_brick", Blocks.COBBLESTONE, MapColor.STONE_GRAY, true);
		// Smooth Stone Set
		createPyriteBlock("smooth_stone_stairs", "stairs", Blocks.SMOOTH_STONE, "building_blocks");
		generateBrickSet("smooth_stone_brick", Blocks.SMOOTH_STONE, MapColor.STONE_GRAY, true);
		// Granite Bricks
		generateBrickSet("granite_brick", Blocks.GRANITE, MapColor.DIRT_BROWN, true);
		// Andesite Bricks
		generateBrickSet("andesite_brick", Blocks.ANDESITE, MapColor.STONE_GRAY, true);
		// Diorite Bricks
		generateBrickSet("diorite_brick", Blocks.DIORITE, MapColor.OFF_WHITE, true);
		// Calcite Bricks
		generateBrickSet("calcite_brick", Blocks.CALCITE, MapColor.OFF_WHITE, true);
		// Mossy Tuff Bricks
		generateBrickSet("mossy_tuff_brick", Blocks.TUFF_BRICKS);
		// Mossy Deepslate Bricks
		generateBrickSet("mossy_deepslate_brick", Blocks.DEEPSLATE_BRICKS);
		// Sandstone Bricks
		generateBrickSet("sandstone_brick", Blocks.SANDSTONE);
		// Terracotta Bricks
		generateBrickSet("terracotta_brick", Blocks.TERRACOTTA);
		// Grass Set
		generateTurfSets();
		// Nether Brick Fence Gate
		createPyriteBlock("nether_brick_fence_gate","fence_gate", Blocks.NETHER_BRICK_FENCE, "building_blocks");
		// Resource Blocks
		generateResourceBlocks();
		// Torch Levers
		createTorchLever("torch_lever", Blocks.TORCH, ParticleTypes.FLAME);
		createTorchLever("redstone_torch_lever", Blocks.SOUL_TORCH, DustParticleEffect.DEFAULT);
		createTorchLever("soul_torch_lever", Blocks.SOUL_TORCH, ParticleTypes.SOUL_FIRE_FLAME);
		// Lamps
		createPyriteBlock("lit_redstone_lamp", "lamp", Blocks.REDSTONE_LAMP, 15, "functional");
		createPyriteBlock("glowstone_lamp","lamp", Blocks.GLOWSTONE, 15, "functional");
		// Classic Features
		createPyriteBlock("glowing_obsidian","obsidian", 50f, MapColor.RED, 15, "obsidian");
		createPyriteBlock("nostalgia_glowing_obsidian","obsidian", 50f, MapColor.RED, 15, "obsidian");
		createPyriteBlock("locked_chest", "facing", Blocks.CHEST, 15, "functional");
		generateNostalgiaBlocks();
		// Classic Flowers
		generateFlowers();
		// Blue Nether Bricks
		generateBrickSet("blue_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLUE, 0, "coloured_nether_bricks");
		// Charred Nether Bricks
		generateBrickSet("charred_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLACK, 0, "coloured_nether_bricks");
		// Vanilla Crafting Tables
		generateVanillaCraftingTables();
		// Modded Crafting Tables
		if (ModHelpers.isModLoaded("aether")) {
			createPyriteBlock("skyroot_crafting_table","crafting", Blocks.CRAFTING_TABLE, "crafting_table");
			createPyriteBlock( "holystone_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "mossy_holystone_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "holystone_brick_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "icestone_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "aerogel_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "carved_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "angelic_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
			createPyriteBlock( "hellfire_wall_gate","wall_gate", Blocks.STONE, BlockSetType.STONE, "misc");
		}
		// Red Mushroom Wood Set
		createPyriteBlock("red_mushroom_stem", "log", Blocks.MUSHROOM_STEM, "red_mushroom");
		createWoodSet("red_mushroom", MapColor.RED, 0, "wood");
		// Brown Mushroom Wood Set
		createPyriteBlock("brown_mushroom_stem", "log", Blocks.MUSHROOM_STEM, "brown_mushroom");
		createWoodSet("brown_mushroom", MapColor.BROWN, 0, "wood");
		// Azalea Wood Set
		createWoodSetWithLog("azalea", MapColor.DULL_RED, 0);
		// Autogenerate dye blocks.
        for (int dyeIndex = 0; dyeIndex < DYES.length; dyeIndex++) {
			String dye = ModLists.DYES[dyeIndex];
			int blockLux = checkDyeLux(dye);
			MapColor color = checkDyeMapColour(dye);
			final boolean VANILLA_DYE = Arrays.asList(VANILLA_DYES).contains(dye);
			if (!VANILLA_DYE) {
				// Dye items.
				registerPyriteItem(dye + "_dye");
				// Dyed Wool
				createPyriteBlock(dye + "_wool", "block", WOOL_MATCH.getOrDefault(dye, Blocks.WHITE_WOOL), color, blockLux, "colored_blocks");
				// Dyed Carpet
				createPyriteBlock(dye + "_carpet", "carpet", CARPET_MATCH.getOrDefault(dye, Blocks.WHITE_CARPET), color, blockLux, "colored_blocks");
				// Dyed Concrete
				createPyriteBlock(dye+"_concrete", "block", CONCRETE_MATCH.getOrDefault(dye, Blocks.WHITE_CONCRETE), color, blockLux, "concrete");
				// Dyed Concrete Powder
				createPyriteBlock(dye+"_concrete_powder", "concrete_powder", Blocks.WHITE_CONCRETE_POWDER, color, blockLux, "concrete_powder");
			}
			// Dyed Concrete Stairs
			createPyriteBlock(dye+"_concrete_stairs", "stairs", Blocks.WHITE_CONCRETE, color, blockLux, "concrete_stairs");
			// Dyed Concrete Slab
			createPyriteBlock(dye+"_concrete_slab", "slab", Blocks.WHITE_CONCRETE, color, blockLux, "concrete_slab");
			//Dyed Planks and plank products
			createWoodSet(dye + "_stained", color, blockLux, "dyed_wood");
			// Dyed Bricks and brick products
			generateBrickSet(dye + "_brick", Blocks.BRICKS, color, blockLux, "dyed_bricks");
			if (!VANILLA_DYE) {
				// Dyed Terracotta
				createPyriteBlock(dye+"_terracotta", "block", Blocks.TERRACOTTA,color, blockLux, "terracotta");
				// Dyed Glazed Terracotta
				//coming soon - createPyriteBlock(dye+"_glazed_terracotta", "block", Blocks.TERRACOTTA,color, blockLux);
			}
			// Dyed Terracotta Bricks
			generateBrickSet(dye+"_terracotta_brick", Blocks.TERRACOTTA, color, blockLux, "terracotta_bricks");
			// Dyed Torches
			createTorch(dye+"_torch", getTorchParticle(dye));
			if (!VANILLA_DYE) {
				// Dyed Stained Glass
				createPyriteBlock(dye+"_stained_glass","stained_framed_glass", 0.3f, color, blockLux, "stained_glass");
				// Dyed Stained Glass Pane
				createPyriteBlock(dye+"_stained_glass_pane","stained_framed_glass_pane", 0.3f, color, blockLux, "stained_glass_pane");
			}
			// Dyed Lamps
			createPyriteBlock(dye + "_lamp","lamp", 0.3f, color, 15, "lamp");
			// Dyed Framed Glass
			createPyriteBlock(dye+"_framed_glass","stained_framed_glass", 2.0f, color, blockLux, "framed_glass");
			// Dyed Framed Glass Pane
			createPyriteBlock( dye+"_framed_glass_pane","stained_framed_glass_pane", 2.0f, color, blockLux, "framed_glass_pane");
			// Dyed Torch Levers
			createTorchLever(dye+"_torch_lever", Blocks.TORCH, getTorchParticle(dye));
		}
		// Autogenerate Wall Gates
		for (Block wallsBlock : getVanillaWalls()) {
			//Find block ID
			String block = findVanillaBlockID(wallsBlock);
			//If the block provided isn't a wall block, add the wall tag.
			if (!block.contains("wall")) {
				block = block + "_wall";
			}
			//Create block.
			createPyriteBlock(block + "_gate","wall_gate", wallsBlock, BlockSetType.STONE, "building_blocks");
		}
	}
}