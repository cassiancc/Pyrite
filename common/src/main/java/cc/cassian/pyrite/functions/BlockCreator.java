package cc.cassian.pyrite.functions;

import cc.cassian.pyrite.functions.architectury.CommonRegistry;
import cc.cassian.pyrite.functions.fabric.FabricRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleEffect;

import java.util.Map;
import java.util.Objects;

import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.ModHelpers.getBlockSetType;
import static cc.cassian.pyrite.functions.ModLists.*;

public class BlockCreator {
    final static Block[] vanillaWood = getVanillaWood();
    final static Block[] resource_blocks = getVanillaResourceBlocks();

    public static void createPyriteItem(String id, String platform) {
        if (isFabric(platform)) {
            FabricRegistry.registerPyriteItem(id);
        }
        else {
            CommonRegistry.registerPyriteItem(id);
        }
    }
    public static void register(String platform) {
        if (isFabric(platform)) {
            FabricRegistry.register();
        }
		else {
            CommonRegistry.register();
    }
    }

    public static void generateResourceBlocks(String platform) {
        for (Block resourceBlock : resource_blocks) {
            String block = findVanillaBlockID(resourceBlock);
            //If the block provided isn't a wall block, add the wall tag.
            if (block.contains("block")) {
                block = block.substring(0, block.indexOf("_block"));
            }
            createResourceBlockSet(block, resourceBlock, platform);
        }
    }

    public static void createTorchLever(String blockID, Block baseTorch, ParticleEffect particle, String platform) {
        sendToRegistry(blockID, "torch_lever", AbstractBlock.Settings.copy(baseTorch), particle, platform);
    }

    public static void generateVanillaCraftingTables(String platform) {
        //Autogenerate Vanilla Crafting Tables
        for (Block plankBlock : vanillaWood) {
            //Find block ID
            String block = findVanillaBlockID(plankBlock);
            //If the block provided isn't a wall block, add the wall tag.
            if (block.contains("planks")) {
                block = block.substring(0, block.indexOf("_planks"));
            }
            //Create block.
            createPyriteBlock(block + "_crafting_table","crafting", plankBlock, platform);
        }
    }

    //Primarily used for Framed Glass, Glowstone/Dyed Lamps, Glowing Obsidian
    public static void createPyriteBlock(String blockID, String blockType, Float strength, MapColor color, int lightLevel, String platform) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().strength(strength).luminance(state -> lightLevel).mapColor(color);
        if (Objects.equals(blockType, "obsidian")) {
            sendToRegistry(blockID, "block", settings.strength(strength, 1200f).pistonBehavior(PistonBehavior.BLOCK), platform);
        }
        else {
            sendToRegistry(blockID, blockType, settings, platform);
        }
    }

    //Create and then add carpets
    private static void createCarpet(String blockID, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(Blocks.MOSS_CARPET);
        sendToRegistry(blockID, "carpet", blockSettings, platform);
    }

    //Create and then add most of the manually generated blocks.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        if (isFabric(platform)) {
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
        else {
            switch (blockType) {
                case "fence_gate":
                    CommonRegistry.registerPyriteBlock(blockID, blockSettings, WoodType.CRIMSON);
                    break;
                case "door", "trapdoor":
                    CommonRegistry.registerPyriteBlock(blockID, blockType, blockSettings, BlockSetType.IRON);
                    break;
                default:
                    CommonRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
                    break;
            }
        }

    }

    //Create a slab from the last block added.
    public static void createStair(String blockID, Block copyBlock, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        sendToRegistry(blockID+"_stairs", copyBlock, blockSettings, platform);
    }

    //Create a slab from the last block added.
    public static void createSlab(String blockID, Block copyBlock, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        sendToRegistry(blockID+"_slab", "slab", blockSettings, platform);
    }

    //Create blocks that require a change in light level, e.g. Locked Chests
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).luminance(parseLux(lux));
        sendToRegistry(blockID, blockType, blockSettings, platform);
    }

    private static void sendToRegistry(String blockID, String blockType, AbstractBlock.Settings blockSettings, String platform) {
        if (isFabric(platform)) {
            FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
        } else {
            CommonRegistry.registerPyriteBlock(blockID, blockType, blockSettings);
        }
    }
    private static void sendToRegistry(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings, String platform) {
        if (isFabric(platform)) {
            FabricRegistry.registerPyriteBlock(blockID, copyBlock, blockSettings);
        } else {
            CommonRegistry.registerPyriteBlock(blockID, copyBlock, blockSettings);
        }
    }
    private static void sendToRegistry(String blockID, String blockType, AbstractBlock.Settings blockSettings, ParticleEffect particle, String platform) {
        if (isFabric(platform)) {
            FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings, particle);
        } else {
            CommonRegistry.registerPyriteBlock(blockID, blockType, blockSettings, particle);
        }
    }

    //Create blocks that require a Block Set.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set, String platform) {
        if (isFabric(platform)) {
            FabricRegistry.registerPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
        }
        else {
            CommonRegistry.registerPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
        }
    }

    //Create most of the generic Stained Blocks, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        if (Objects.equals(blockType, "stairs")) {
            sendToRegistry(blockID, copyBlock, blockSettings, platform);
        }
        else {
            sendToRegistry(blockID, blockType, blockSettings, platform);
        }
    }

    //Create basic blocks.
    public static void createPyriteBlock(String blockID, Block copyBlock, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        sendToRegistry(blockID, "block", blockSettings, platform);
    }

    //Create Stained blocks that require a wood set or wood type, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type, String platform) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        switch (blockType) {
            case "door", "trapdoor", "button", "pressure_plate":
                if (isFabric(platform)) {
                    FabricRegistry.registerPyriteBlock(blockID, blockType, blockSettings, set);
                }
                else {
                    CommonRegistry.registerPyriteBlock(blockID, blockType, blockSettings, set);
                }
                break;
            case "fence_gate":
                if (isFabric(platform)) {
                    FabricRegistry.registerPyriteBlock(blockID, blockSettings, type);
                }
                else {
                    CommonRegistry.registerPyriteBlock(blockID, blockSettings, type);
                }
                break;
            default:
                sendToRegistry(blockID, blockType, blockSettings, platform);
                break;
        }
    }

    public static void createFlowers(String platform) {
        for (Map.Entry<String, Block> entry : flowers.entrySet()) {
            createPyriteBlock(entry.getKey(), "flower", entry.getValue(), platform);
        }
    }

    public static void createTurfSets(String platform) {
        for (Map.Entry<String, Block> entry : turfSets.entrySet()) {
            createTurfSet(entry.getKey(), entry.getValue(), platform);
        }
    }

    public static void createNostalgia(String platform) {
        for (Map.Entry<String, Block> entry : nostalgiaBlocks.entrySet()) {
            createPyriteBlock(entry.getKey(), "block", entry.getValue(), platform);
        }
        createPyriteBlock("nostalgia_gravel", "gravel", Blocks.GRAVEL, platform);
    }

    //Generate an entire brick set.
    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, int lux, String platform) {
        //Bricks
        createPyriteBlock( blockID+"s", "block", copyBlock, color, lux, platform);
        //Brick Stairs
        createPyriteBlock( blockID+"_stairs", "stairs", copyBlock, color, lux, platform);
        //Brick Slab
        createPyriteBlock( blockID+"_slab", "slab", copyBlock, color, lux, platform);
        //Brick Wall
        createPyriteBlock( blockID+"_wall", "wall", copyBlock, color, lux, platform);
        //Brick Wall Gate
        createPyriteBlock(blockID+"_wall_gate","fence_gate", copyBlock, platform);
    }


    //Generate a Turf block set - including block and its slab, stair, and carpet variants.
    public static void createTurfSet(String blockID, Block copyBlock, String platform) {
        createPyriteBlock( blockID+"_turf", "block", copyBlock, platform);
        createStair(blockID, copyBlock, platform);
        createSlab(blockID, copyBlock, platform);
        createCarpet(blockID+"_carpet", platform);
    }

    //Generate an entire wood set.
    public static void createWoodSet(String blockID, MapColor color, int blockLux, String platform) {
        BlockSetType GENERATED_SET = new BlockSetType(blockID);
        WoodType GENERATED_TYPE = new WoodType(blockID, GENERATED_SET);
        //Stained Planks
        createPyriteBlock( blockID+"_planks", "block", Blocks.OAK_PLANKS, color, blockLux, platform);
        //Stained Stairs
        createPyriteBlock(blockID+"_stairs", "stairs",Blocks.OAK_STAIRS, color, blockLux, platform);
        //Stained Slabs
        createPyriteBlock( blockID+"_slab", "slab", Blocks.OAK_SLAB, color, blockLux, platform);
        //Stained Pressure Plates
        createPyriteBlock( blockID+"_pressure_plate", "pressure_plate", Blocks.OAK_PRESSURE_PLATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Stained Buttons
        createPyriteBlock(blockID+"_button", "button", Blocks.OAK_BUTTON, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Stained Fences
        createPyriteBlock(blockID+"_fence", "fence", Blocks.OAK_FENCE, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Stained Fence Gates
        createPyriteBlock(blockID+"_fence_gate", "fence_gate", Blocks.OAK_FENCE_GATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Stained Doors
        createPyriteBlock(blockID+"_door", "door", Blocks.OAK_DOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Stained Trapdoors
        createPyriteBlock(blockID+"_trapdoor", "trapdoor", Blocks.OAK_TRAPDOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, platform);
        //Crafting Tables
        createPyriteBlock( blockID+"_crafting_table", "crafting", Blocks.CRAFTING_TABLE, color, blockLux, platform);
        createPyriteBlock( blockID+"_ladder", "ladder", Blocks.LADDER, color, blockLux, platform);


    }

    //Generate an entire Cut Block set.
    public static void createCutBlocks(String blockID, Block block, String platform) {
        String cutBlockID = "cut_" + blockID;
        if (!blockID.contains("copper")) {
            //Cut Block
            createPyriteBlock(cutBlockID, block, platform);
            //Cut Stairs
            createStair(cutBlockID, block, platform);
            //Cut Slab
            createSlab(cutBlockID, block, platform);
        }
        //Cut Wall
        createPyriteBlock(cutBlockID+"_wall", "wall", block, platform);
        //Cut Wall Gate
        createPyriteBlock(cutBlockID+"_wall_gate","fence_gate", block, platform);
    }

    //Generate an entire Smooth Block set.
    public static void createSmoothBlocks(String blockID, Block block, String platform) {
        String smoothBlockID = "smooth_" + blockID;
        if (!Objects.equals(blockID, "quartz")) {
            //Smooth Block
            createPyriteBlock(smoothBlockID, block, platform);
            //Smooth Stairs
            createStair(smoothBlockID, block, platform);
            //Smooth Slab
            createSlab(smoothBlockID, block, platform);
        }
        //Smooth Wall
        createPyriteBlock(smoothBlockID+"_wall", "wall", block, platform);
        //Smooth Wall Gate
        createPyriteBlock(smoothBlockID+"_wall_gate","fence_gate", block, platform);
    }

    //Create a set of Resource Blocks
    public static void createResourceBlockSet(String blockID, Block block, String platform) {
        //Create Cut Blocks for those that don't already exist (Copper)
        createCutBlocks(blockID, block, platform);
        //Create Bricks/Chiseled/Pillar/Smooth for those that don't already exist (Quartz)
        if (!Objects.equals(blockID, "quartz")) {
            //Brick Blocks
            createPyriteBlock(blockID+"_bricks", block, platform);
            //Chiseled Blocks - Copper Blocks
            if (blockID.contains("copper")) {
                if (!isTrialsOrLater(platform)) {
                    createPyriteBlock("chiseled_"+blockID+"_block", "log", block, platform);
                }
            }
            else {
                createPyriteBlock("chiseled_"+blockID+"_block", "log", block, platform);
            }
            //Pillar Blocks
            createPyriteBlock(blockID+"_pillar", "log", block, platform);
        }
        //Smooth Blocks
        createSmoothBlocks(blockID, block, platform);
        createPyriteBlock("nostalgia_"+blockID+"_block", block, platform);
        //Block set for modded blocks
        BlockSetType set = getBlockSetType(blockID);
        //Create Bars/Doors/Trapdoors/Plates for those that don't already exist (Iron)
        if (!Objects.equals(blockID, "iron")) {
            //Bars
            createPyriteBlock(blockID+"_bars","bars", block, platform);
            //Disable Copper doors in 1.21+
            if (blockID.contains("copper")) {
                if (!isTrialsOrLater(platform)) {
                    createPyriteBlock(blockID+"_door","door", block, set, platform);
                }
            }
            else {
                createPyriteBlock(blockID+"_door","door", block, set, platform);
            }
            createPyriteBlock(blockID+"_trapdoor","trapdoor", block, set, platform);
            //Create Plates for those that don't already exist (Iron and Gold)
            if (!Objects.equals(blockID, "gold")) {
                createPyriteBlock(blockID+"_pressure_plate","pressure_plate", block, set, platform);
            }
        }
        //Create buttons for all blocks.
        createPyriteBlock(blockID+"_button","button", block, set, platform);
    }

}
