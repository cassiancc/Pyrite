package cc.cassian.pyrite;

import cc.cassian.pyrite.functions.FabricRegistry;
import net.minecraft.block.*;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;

import static cc.cassian.pyrite.functions.BlockCreator.*;
import static cc.cassian.pyrite.functions.ModLists.*;
import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.FabricRegistry.*;

public class Pyrite {
	public final static String modID = "pyrite";
	private String platform;


	public static void init(String platform) {
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
		//Torch Levers
		createTorchLever("torch_lever", Blocks.TORCH, ParticleTypes.FLAME);
		createTorchLever("redstone_torch_lever", Blocks.SOUL_TORCH, DustParticleEffect.DEFAULT);
		createTorchLever("soul_torch_lever", Blocks.TORCH, ParticleTypes.SOUL_FIRE_FLAME);
		//Lamps
		createPyriteBlock("lit_redstone_lamp", "block", Blocks.REDSTONE_LAMP, 15);
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
		final String[] dyes = getDyes();
		for (int dyeIndex = 0; dyeIndex < dyes.length; dyeIndex++) {
			String dye = dyes[dyeIndex];
			int blockLux = checkDyeLux(dye);
			MapColor color = checkDyeMapColour(dye);
			if (dyeIndex > 15) {
				//Dye items.
				registerPyriteItem(dye + "_dye");
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
		for (Block wallsBlock : getVanillaWalls()) {
			//Find block ID
			String block = findVanillaBlockID(wallsBlock);
			//If the block provided isn't a wall block, add the wall tag.
			if (!block.contains("wall")) {
				block = block + "_wall";
			}
			//Create block.
			createPyriteBlock(block + "_gate","fence_gate", wallsBlock);
		}

		FabricRegistry.register();

	}




}