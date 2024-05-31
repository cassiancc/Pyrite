package cc.cassian.pyrite.functions;

import cc.cassian.pyrite.functions.fabric.FabricRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleEffect;

import java.util.Objects;

import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.ModHelpers.getBlockSetType;
import static cc.cassian.pyrite.functions.ModLists.*;
import static cc.cassian.pyrite.functions.fabric.FabricRegistry.registerPyriteBlock;

public class BlockCreator {
    final static Block[] vanillaWood = getVanillaWood();
    final static Block[] resource_blocks = getVanillaResourceBlocks();


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

    public static void createTorchLever(String blockID, Block baseTorch, ParticleEffect particle) {
        registerPyriteBlock(blockID, "torch_lever", AbstractBlock.Settings.copy(baseTorch), particle);

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
            FabricRegistry.registerPyriteBlock(blockID, "block", settings.strength(strength, 1200f).pistonBehavior(PistonBehavior.BLOCK));
        }
        else {
            FabricRegistry.registerPyriteBlock(blockID, blockType, settings);
        }
    }

    //Create and then add carpets
    private static void createCarpet(String blockID) {
        AbstractBlock.Settings blockSettings = copyBlock(Blocks.MOSS_CARPET);
        FabricRegistry.registerPyriteBlock(blockID, "carpet", blockSettings);
    }

    //Create and then add most of the manually generated blocks.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        switch (blockType) {
            case "fence_gate":
                FabricRegistry.registerPyriteBlock(blockID, blockSettings, WoodType.CRIMSON);
                break;
            case "door", "trapdoor":
                FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings, BlockSetType.IRON);
                break;
            default:
                FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
                break;
        }
    }

    //Create a slab from the last block added.
    public static void createStair(String blockID, Block copyBlock) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        FabricRegistry.registerPyriteBlock(blockID+"_stairs", copyBlock, blockSettings);
    }

    //Create a slab from the last block added.
    public static void createSlab(String blockID, Block copyBlock) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        FabricRegistry.registerPyriteBlock(blockID+"_slab", "slab", blockSettings);
    }

    //Create blocks that require a change in light level, e.g. Locked Chests
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).luminance(parseLux(lux));
        FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
    }

    //Create blocks that require a Block Set.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set) {
        FabricRegistry.registerPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
    }

    //Create most of the generic Stained Blocks, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        if (Objects.equals(blockType, "stairs")) {
            FabricRegistry.registerPyriteBlock(blockID, copyBlock, blockSettings);
        }
        else {
            FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
        }
    }

    //Create basic blocks.
    public static void createPyriteBlock(String blockID, Block copyBlock) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        FabricRegistry.registerPyriteBlock(blockID, "block", blockSettings);
    }

    //Create Stained blocks that require a wood set or wood type, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        switch (blockType) {
            case "door", "trapdoor", "button", "pressure_plate":
                FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings, set);
                break;
            case "fence_gate":
                FabricRegistry.registerPyriteBlock(blockID, blockSettings, type);
                break;
            default:
                FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
                break;
        }
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

}
