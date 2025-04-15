package cc.cassian.pyrite.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.ModHelpers.getBlockSetType;
import static cc.cassian.pyrite.functions.ModLists.*;

public class BlockCreator {
    final static Block[] vanillaWood = getVanillaWood();
    final static Block[] resource_blocks = getVanillaResourceBlocks();

    @ExpectPlatform @SuppressWarnings("unused")
    public static void platformRegister(String blockID, String blockType, AbstractBlock.Settings blockSettings, WoodType woodType, BlockSetType blockSetType, ParticleEffect particle, Block copyBlock, String group, MapColor color) {
        throw new AssertionError();
    }

    //Create and add Pyrite items.
    @ExpectPlatform @SuppressWarnings("unused")
    public static void registerPyriteItem(String itemID) {
        throw new AssertionError();
    }

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
        sendToRegistry(blockID, "torch_lever", AbstractBlock.Settings.copy(baseTorch), particle, "torch_lever");
    }
    public static void createTorch(String blockID, ParticleEffect particle) {
        sendToRegistry(blockID, "torch", AbstractBlock.Settings.copy(Blocks.TORCH), particle, "torch");
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
            createPyriteBlock(block + "_crafting_table","crafting", plankBlock, "crafting_table");
        }
    }

    //Primarily used for Framed Glass, Glowstone/Dyed Lamps, Glowing Obsidian
    public static void createPyriteBlock(String blockID, String blockType, Float strength, MapColor color, int lightLevel, String group) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().strength(strength).luminance(state -> lightLevel).mapColor(color);
        if (Objects.equals(blockType, "obsidian")) {
            sendToRegistry(blockID, "block", settings.strength(strength, 1200f).pistonBehavior(PistonBehavior.BLOCK), group);
        }
        else if (blockType.equals("lamp")) {
            sendToRegistry(blockID, blockType, settings.sounds(BlockSoundGroup.GLASS), group);
        }
        else {
            sendToRegistry(blockID, blockType, settings.sounds(BlockSoundGroup.GLASS).nonOpaque().solidBlock(BlockCreator::never), group);
        }
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    //Create and then add carpets
    private static void createCarpet(String blockID, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(Blocks.MOSS_CARPET);
        sendToRegistry(blockID, "carpet", blockSettings, group);
    }

    //Create and then add most of the manually generated blocks.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, String group) {
        platformRegister(blockID, blockType, copyBlock(copyBlock), WoodType.CRIMSON, BlockSetType.IRON, null, copyBlock, group, null);
    }

    //Create a slab from the last block added.
    public static void createStair(String blockID, Block copyBlock, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        sendToRegistry(blockID+"_stairs", copyBlock, blockSettings, group);
    }

    //Create a slab from the last block added.
    public static void createSlab(String blockID, Block copyBlock, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        sendToRegistry(blockID+"_slab", "slab", blockSettings, group);
    }

    //Create blocks that require a change in light level, e.g. Locked Chests
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).luminance(parseLux(lux));
        platformRegister(blockID, blockType, blockSettings, null, null, null, copyBlock, group, null);
    }

    private static void sendToRegistry(String blockID, String blockType, AbstractBlock.Settings blockSettings, String group) {
        platformRegister(blockID, blockType, blockSettings, null, null, null, null, group, null);

    }
    private static void sendToRegistry(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings, String group) {
        platformRegister(blockID, "stairs", blockSettings,  null, null, null, copyBlock, group, null);
    }
    
    //Add blocks with particles - Torches/Torch Levers
    private static void sendToRegistry(String blockID, String blockType, AbstractBlock.Settings blockSettings, ParticleEffect particle, String group) {
        platformRegister(blockID, blockType, blockSettings, null, null, particle, null, group, null);
    }

    //Create blocks that require a Block Set.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set, String group) {
        platformRegister(blockID, blockType, copyBlock(copyBlock),  null, set, null, copyBlock, group, null);
    }

    //Create most of the generic Stained Blocks, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        if ((copyBlock.equals(Blocks.OAK_PLANKS)) || (copyBlock.equals(Blocks.OAK_SLAB) || (copyBlock.equals(Blocks.OAK_STAIRS)))) {
            blockSettings = blockSettings.burnable();
        }
        platformRegister(blockID, blockType, blockSettings,  null, null, null, copyBlock, group, color);
    }

    //Create basic blocks.
    public static void createPyriteBlock(String blockID, Block copyBlock, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        platformRegister(blockID, "block", blockSettings,  null, null, null, null, group, null);
    }

    //Create Stained blocks that require a wood set or wood type, then add them.
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type, String group) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        if (!blockType.equals("button")) {
            blockSettings = blockSettings.burnable();
        }
        platformRegister(blockID, blockType, blockSettings,  type, set, null, null, group, color);
    }

    public static void generateFlowers() {
        for (Map.Entry<String, Block> entry : FLOWERS.entrySet()) {
            createPyriteBlock(entry.getKey(), "flower", entry.getValue(), "flower");
        }
    }

    public static void generateTurfSets() {
        for (Map.Entry<String, Block> entry : TURF_SETS.entrySet()) {
            createTurfSet(entry.getKey(), entry.getValue());
        }
    }

    public static void generateNostalgiaBlocks() {
        for (Map.Entry<String, Block> entry : NOSTALGIA_BLOCKS.entrySet()) {
            createPyriteBlock(entry.getKey(), "block", entry.getValue(), entry.getKey());
        }
        createPyriteBlock("nostalgia_gravel", "gravel", Blocks.GRAVEL, "gravel");
    }

    //Generate an entire brick set.
    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, int lux, @Nullable String group) {
        if (group == null)
            group = blockID;
        //Bricks
        createPyriteBlock( blockID+"s", "block", copyBlock, color, lux, group);
        //Brick Stairs
        createPyriteBlock( blockID+"_stairs", "stairs", copyBlock, color, lux, group);
        //Brick Slab
        createPyriteBlock( blockID+"_slab", "slab", copyBlock, color, lux, group);
        //Brick Wall
        createPyriteBlock( blockID+"_wall", "wall", copyBlock, color, lux, group);
        //Brick Wall Gate
        createPyriteBlock(blockID+"_wall_gate","wall_gate", copyBlock, BlockSetType.STONE, group);
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color) {
        generateBrickSet(blockID, copyBlock, color, 0, blockID);
    }

    public static void generateBrickSet(String blockID, Block copyBlock) {
        generateBrickSet(blockID, copyBlock, copyBlock.getDefaultMapColor());
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, boolean generateMossySet, String group) {
        generateBrickSet(blockID, copyBlock, color, 0, group);
        if (generateMossySet)
            generateBrickSet("mossy_"+blockID, copyBlock, color, 0, null);
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, boolean generateMossySet) {
        generateBrickSet(blockID, copyBlock, color, generateMossySet, blockID);
    }

        //Generate a Turf block set - including block and its slab, stair, and carpet variants.
    public static void createTurfSet(String blockID, Block copyBlock) {
        createPyriteBlock( blockID+"_turf", "block", copyBlock, blockID);
        createStair(blockID, copyBlock, blockID);
        createSlab(blockID, copyBlock, blockID);
        createCarpet(blockID+"_carpet", blockID);
    }
    
    @ExpectPlatform @SuppressWarnings("unused")
    public static WoodType createWoodType(String blockID, BlockSetType setType) {
        throw new AssertionError();
    }

    /**
     * Generate an entire wood set.
     */
    public static void createWoodSet(String blockID, MapColor color, int blockLux, String group) {
        BlockSetType GENERATED_SET = new BlockSetType(blockID);
        WoodType GENERATED_TYPE = createWoodType(blockID, GENERATED_SET);
        // Planks
        createPyriteBlock("%s_planks".formatted(blockID), "block", Blocks.OAK_PLANKS, color, blockLux, group);
        // Stairs
        createPyriteBlock("%s_stairs".formatted(blockID), "stairs",Blocks.OAK_STAIRS, color, blockLux, group);
        // Slabs
        createPyriteBlock("%s_slab".formatted(blockID), "slab", Blocks.OAK_SLAB, color, blockLux, group);
        // Fences
        createPyriteBlock("%s_fence".formatted(blockID), "fence", Blocks.OAK_FENCE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Fence Gates
        createPyriteBlock("%s_fence_gate".formatted(blockID), "fence_gate", Blocks.OAK_FENCE_GATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Doors
        createPyriteBlock("%s_door".formatted(blockID), "door", Blocks.OAK_DOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Trapdoors
        createPyriteBlock("%s_trapdoor".formatted(blockID), "trapdoor", Blocks.OAK_TRAPDOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Pressure Plates
        createPyriteBlock("%s_pressure_plate".formatted(blockID), "pressure_plate", Blocks.OAK_PRESSURE_PLATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Buttons
        createPyriteBlock("%s_button".formatted(blockID), "button", Blocks.OAK_BUTTON, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Crafting Tables
        createPyriteBlock("%s_crafting_table".formatted(blockID), "crafting", Blocks.CRAFTING_TABLE, color, blockLux, group);
        // Ladders
        createPyriteBlock("%s_ladder".formatted(blockID), "ladder", Blocks.LADDER, color, blockLux, group);
        // Signs
        createPyriteBlock("%s_sign".formatted(blockID), "sign", Blocks.OAK_SIGN, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        // Hanging Signs
        createPyriteBlock("%s_hanging_sign".formatted(blockID), "hanging_sign", Blocks.OAK_HANGING_SIGN, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
        createPyriteBlock("%s_chest".formatted(blockID), "chest", Blocks.CHEST, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
    }

    /**
     * Generate an entire wood set, alongside Logs, Wood, and Stripped Logs/Wood.
     */
    public static void createWoodSetWithLog(String blockID, MapColor color, int blockLux) {
        createPyriteBlock("%s_log".formatted(blockID), "log", Blocks.OAK_LOG, color, blockLux, "wood");
        createPyriteBlock("stripped_%s_log".formatted(blockID), "log", Blocks.STRIPPED_OAK_LOG, color, blockLux, "wood");
        createPyriteBlock("%s_wood".formatted(blockID), "wood", Blocks.OAK_WOOD, color, blockLux, "wood");
        createPyriteBlock("stripped_%s_wood".formatted(blockID), "wood", Blocks.STRIPPED_OAK_WOOD, color, blockLux, "wood");
        createWoodSet(blockID, color, blockLux, "wood");
    }

    /**
     * Generate an entire Cut Block set.
     */
    public static void createCutBlocks(String blockID, Block block) {
        String cutBlockID = "cut_" + blockID;
        if (!blockID.contains("copper")) {
            //Cut Block
            createPyriteBlock(cutBlockID, block, blockID);
            //Cut Stairs
            createStair(cutBlockID, block, blockID);
            //Cut Slab
            createSlab(cutBlockID, block, blockID);
        }
        //Cut Wall
        createPyriteBlock("%s_wall".formatted(cutBlockID), "wall", block, blockID);
        //Cut Wall Gate
        createPyriteBlock("%s_wall_gate".formatted(cutBlockID),"wall_gate", block, blockID);
    }

    /**
     * Generate an entire Smooth Block set.
     */
    public static void createSmoothBlocks(String blockID, Block block) {
        String smoothBlockID = "smooth_" + blockID;
        if (!Objects.equals(blockID, "quartz")) {
            //Smooth Block
            createPyriteBlock(smoothBlockID, block, blockID);
            //Smooth Stairs
            createStair(smoothBlockID, block, blockID);
            //Smooth Slab
            createSlab(smoothBlockID, block, blockID);
        }
        //Smooth Wall
        createPyriteBlock("%s_wall".formatted(smoothBlockID), "wall", block, blockID);
        //Smooth Wall Gate
        createPyriteBlock("%s_wall_gate".formatted(smoothBlockID),"wall_gate", block, blockID);
    }

    //Create a set of Resource Blocks
    public static void createResourceBlockSet(String blockID, Block block) {
        //Create Cut Blocks for those that don't already exist (Copper)
        createCutBlocks(blockID, block);
        //Create Bricks/Chiseled/Pillar/Smooth for those that don't already exist (Quartz)
        if (!Objects.equals(blockID, "quartz")) {
            //Brick Blocks
            createPyriteBlock("%s_bricks".formatted(blockID), block, blockID);
            //Chiseled Blocks - Copper Blocks
            if (!blockID.contains("copper")) {
                createPyriteBlock("chiseled_%s_block".formatted(blockID), "log", block, blockID);
            }
            //Pillar Blocks
            createPyriteBlock("%s_pillar".formatted(blockID), "log", block, blockID);
        }
        //Smooth Blocks
        createSmoothBlocks(blockID, block);
        createPyriteBlock("nostalgia_%s_block".formatted(blockID), block, blockID);
        //Block set for modded blocks
        BlockSetType set = getBlockSetType(blockID);
        //Create Bars/Doors/Trapdoors/Plates for those that don't already exist (Iron)
        if (!blockID.equals("iron")) {
            //Bars
            createPyriteBlock("%s_bars".formatted(blockID),"bars", block, blockID);
            //Disable Copper doors in 1.21+
            if (!blockID.contains("copper")) {
                createPyriteBlock("%s_door".formatted(blockID),"door", block, set, blockID);
                createPyriteBlock("%s_trapdoor".formatted(blockID),"trapdoor", block, set, blockID);
            }
            //Create Plates for those that don't already exist (Iron and Gold)
            if (!blockID.equals("gold")) {
                createPyriteBlock("%s_pressure_plate".formatted(blockID),"pressure_plate", block, set, blockID);
            }
        }
        //Create buttons for all blocks.
        createPyriteBlock("%s_button".formatted(blockID),"button", block, set, blockID);
    }

}
