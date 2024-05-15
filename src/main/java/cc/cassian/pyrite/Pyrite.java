package cc.cassian.pyrite;

import cc.cassian.pyrite.blocks.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.ToIntFunction;

public class Pyrite implements ModInitializer {
    public AbstractBlock.Settings copyBlock(Block copyBlock) {
        return AbstractBlock.Settings.copy(copyBlock);
    }
    public void addTransparentBlock() {
        transparentBlocks.add(getLastBlock());
    }
    public Block getLastBlock() {
        return pyriteBlocks.get(pyriteBlocks.size()-1);
    }
    public Block getLastBlock(int index) {
        return pyriteBlocks.get(pyriteBlocks.size()-index);
    }
    public ToIntFunction<BlockState> parseLux(int lux) {
        return state -> lux;
    }
    //List of Blocks and Block IDS.
    public static ArrayList<Block> pyriteBlocks = new ArrayList<>();
    public static ArrayList<Item> pyriteItems = new ArrayList<>();
    public static ArrayList<Block> transparentBlocks = new ArrayList<>();
    static ArrayList<String> pyriteBlockIDs = new ArrayList<>();
    static ArrayList<String> pyriteItemIDs = new ArrayList<>();
    static String modID = "pyrite";
    //List of dyes to autogenerate blocks for.
    String[] dyes = {
            "white",
            "orange",
            "magenta",
            "light_blue",
            "yellow",
            "lime",
            "pink",
            "gray",
            "light_gray",
            "cyan",
            "purple",
            "blue",
            "brown",
            "green",
            "red",
            "black",
            "glow",
            "dragon",
            "star",
            "honey",
            "nostalgia",
            "rose"
    };

    //List of Wall Blocks to generated Wall Gates for.
    Block[] walls_blocks = {
            Blocks.COBBLESTONE_WALL,
            Blocks.MOSSY_COBBLESTONE_WALL,
            Blocks.STONE_BRICK_WALL,
            Blocks.MOSSY_STONE_BRICK_WALL,
            Blocks.GRANITE_WALL,
            Blocks.DIORITE_WALL,
            Blocks.ANDESITE_WALL,
            Blocks.COBBLED_DEEPSLATE_WALL,
            Blocks.POLISHED_DEEPSLATE_WALL,
            Blocks.DEEPSLATE_BRICK_WALL,
            Blocks.DEEPSLATE_TILE_WALL,
            Blocks.BRICK_WALL,
            Blocks.MUD_BRICK_WALL,
            Blocks.SANDSTONE_WALL,
            Blocks.RED_SANDSTONE_WALL,
            Blocks.PRISMARINE_WALL,
            Blocks.NETHER_BRICK_WALL,
            Blocks.RED_NETHER_BRICK_WALL,
            Blocks.BLACKSTONE_WALL,
            Blocks.POLISHED_BLACKSTONE_WALL,
            Blocks.POLISHED_BLACKSTONE_BRICK_WALL,
            Blocks.END_STONE_BRICK_WALL

    };

    //Primarily used for Framed Glass, Glowstone/Dyed Lamps, Glowing Obsidian
    public void createPyriteBlock(String blockID, String blockType, Float strength, MapColor color, int lightLevel) {
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().strength(strength).luminance(state -> lightLevel).mapColor(color);
        if (Objects.equals(blockType, "obsidian")) {
            addPyriteBlock(blockID, "block", settings.strength(strength, 1200f).pistonBehavior(PistonBehavior.BLOCK));
        }
        else {
            addPyriteBlock(blockID, blockType, settings);
        }
    }

    //Create and then add carpets
    private void createCarpet(String blockID) {
        AbstractBlock.Settings blockSettings = copyBlock(Blocks.MOSS_CARPET);
        addPyriteBlock(blockID, "carpet", blockSettings);
    }

    //Create and then add most of the manually generated blocks.
    public void createPyriteBlock(String blockID, String blockType, Block copyBlock) {
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
    public void createStair(String blockID) {
        AbstractBlock.Settings blockSettings = copyBlock(getLastBlock());
        addPyriteBlock(blockID+"_stairs", getLastBlock(), blockSettings);
    }

    //Create a slab from the last block added.
    public void createSlab(String blockID) {
        AbstractBlock.Settings blockSettings = copyBlock(getLastBlock());
        addPyriteBlock(blockID+"_slab", "slab", blockSettings);
    }

    //Create blocks that require a change in light level, e.g. Locked Chests
    public void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).luminance(parseLux(lux));
        addPyriteBlock(blockID, blockType, blockSettings);
    }

    //Create blocks that require a Block Set.
    public void createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set) {
        addPyriteBlock(blockID, blockType, copyBlock(copyBlock), set);
    }

    //Create most of the generic Stained Blocks, then add them.
    public void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock).mapColor(color).luminance(parseLux(lux));
        if (Objects.equals(blockType, "stairs")) {
            addPyriteBlock(blockID, copyBlock, blockSettings);
        }
        else {
            addPyriteBlock(blockID, blockType, blockSettings);
        }
    }

    //Create basic blocks.
    public void createPyriteBlock(String blockID, Block copyBlock) {
        AbstractBlock.Settings blockSettings = copyBlock(copyBlock);
        addPyriteBlock(blockID, "block", blockSettings);
    }

    //Add most Pyrite blocks.
    public void addPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings) {
        pyriteBlockIDs.add(blockID);
        switch (blockType.toLowerCase()) {
            case "block":
                pyriteBlocks.add(new Block(blockSettings));
                break;
            case "carpet":
                pyriteBlocks.add(new ModCarpet(blockSettings));
                break;
            case "slab":
                pyriteBlocks.add(new SlabBlock(blockSettings));
                break;
            case "wall":
                pyriteBlocks.add(new WallBlock(blockSettings));
                break;
            case "fence":
                pyriteBlocks.add(new FenceBlock(blockSettings));
                break;
            case "log":
                pyriteBlocks.add(new PillarBlock(blockSettings));
                break;
            case "facing":
                pyriteBlocks.add(new ModFacingBlock(blockSettings));
                break;
            case "bars", "glass_pane":
                pyriteBlocks.add(new ModPane(blockSettings));
                addTransparentBlock();
                break;
            case "glass":
                pyriteBlocks.add(new ModGlass(blockSettings));
                addTransparentBlock();
                break;
            default:
                System.out.println(blockID + "created as a generic block, block provided" + blockType);
                pyriteBlocks.add(new Block(blockSettings));
                break;
        }
    }

    //Add Pyrite blocks that require Wood Types - Fence gates.
    public void addPyriteBlock(String blockID, AbstractBlock.Settings blockSettings, WoodType type) {
        pyriteBlockIDs.add(blockID);
        pyriteBlocks.add(new FenceGateBlock(blockSettings, type));
    }

    //Add Pyrite blocks that require Block Sets.
    public void addPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings, BlockSetType type) {
        pyriteBlockIDs.add(blockID);
        switch (blockType) {
            case "door":
                pyriteBlocks.add(new DoorBlock(blockSettings.nonOpaque(), type));
                addTransparentBlock();
                break;
            case "trapdoor":
                pyriteBlocks.add(new TrapdoorBlock(blockSettings.nonOpaque(), type));
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
    public void addPyriteBlock(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings) {
        pyriteBlockIDs.add(blockID);
        pyriteBlocks.add(new ModStairs(copyBlock.getDefaultState(), blockSettings));
    }

    //Create Stained blocks that require a wood set or wood type, then add them.
    public void createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type) {
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
    public void createPyriteItem(String itemID) {
        pyriteItems.add(new Item(new Item.Settings()));
        pyriteItemIDs.add(itemID);
    }

    //Generate an entire brick set.
    public void createBrickSet(String blockID, Block copyBlock, MapColor color, int lux) {
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

    //Generate an entire wood set.
    public void createWoodSet(String blockID, MapColor color, int blockLux) {
        BlockSetType GENERATED_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(new Identifier(modID, blockID));
        WoodType GENERATED_TYPE = WoodTypeBuilder.copyOf(WoodType.CRIMSON).register(new Identifier(modID, blockID), GENERATED_SET);
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
    }

    //Generate an entire Cut Block set.
    public void createCutBlocks(String blockID, Block block) {
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
    public void createSmoothBlocks(String blockID, Block block) {
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
    public void createResourceBlockSet(String blockID, Block block) {
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
        BlockSetType set = BlockSetTypeBuilder.copyOf(BlockSetType.GOLD).openableByHand(openByHand).register(new Identifier("pyrite", blockID+"_set"));
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

    @Override
    public void onInitialize() {
        //Framed Glass
        createPyriteBlock("framed_glass","glass", 2.0f, MapColor.CLEAR, 0);
        //Framed Glass Pane
        createPyriteBlock( "framed_glass_pane","glass_pane", 2.0f, MapColor.CLEAR, 0);
        //Cobblestone Bricks
        createBrickSet("cobblestone_brick", Blocks.COBBLESTONE, MapColor.STONE_GRAY, 0);
        //Mossy Cobblestone Bricks
        createBrickSet("mossy_cobblestone_brick", Blocks.MOSSY_COBBLESTONE, MapColor.STONE_GRAY, 0);
        //Grass Carpet
        createCarpet("grass_carpet");
        //Mycelium Carpet
        createCarpet("mycelium_carpet");
        //Podzol Carpet
        createCarpet("podzol_carpet");
        //Path Carpet
        createCarpet("path_carpet");
        //Nether Brick Fence Gate
        createPyriteBlock("nether_brick_fence_gate","fence_gate", Blocks.NETHER_BRICK_FENCE);
        //Resource Blocks
        createResourceBlockSet("iron", Blocks.IRON_BLOCK);
        createResourceBlockSet("gold", Blocks.GOLD_BLOCK);
        createResourceBlockSet("emerald", Blocks.EMERALD_BLOCK);
        createResourceBlockSet("lapis", Blocks.LAPIS_BLOCK);
        createResourceBlockSet("diamond", Blocks.DIAMOND_BLOCK);
        createResourceBlockSet("netherite", Blocks.NETHERITE_BLOCK);
        createResourceBlockSet("quartz", Blocks.QUARTZ_BLOCK);
        createResourceBlockSet("amethyst", Blocks.AMETHYST_BLOCK);
        createResourceBlockSet("copper", Blocks.COPPER_BLOCK);
        createResourceBlockSet("exposed_copper", Blocks.EXPOSED_COPPER);
        createResourceBlockSet("weathered_copper", Blocks.WEATHERED_COPPER);
        createResourceBlockSet("oxidized_copper", Blocks.OXIDIZED_COPPER);
        //Glowstone Lamp
        createPyriteBlock("glowstone_lamp","block", 0.3f, MapColor.YELLOW, 15);
        //Glowing Obsidian
        createPyriteBlock("glowing_obsidian","obsidian", 50f, MapColor.RED, 15);
        //Locked Chest
        createPyriteBlock("locked_chest", "facing", Blocks.CHEST, 15);
        createPyriteBlock("nostalgia_grass_block", Blocks.COBBLESTONE);
        createPyriteBlock("nostalgia_cobblestone", Blocks.COBBLESTONE);
        createPyriteBlock("nostalgia_mossy_cobblestone", Blocks.COBBLESTONE);
        createPyriteBlock("nostalgia_gravel", Blocks.COBBLESTONE);
        createPyriteBlock("nostalgia_netherrack", Blocks.COBBLESTONE);
        //Charred Nether Bricks
        createBrickSet("charred_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLACK, 0);
        //Blue Nether Bricks
        createBrickSet("blue_nether_brick", Blocks.NETHER_BRICKS, MapColor.BLUE, 0);
        //Red Mushroom Blocks
        createPyriteBlock("red_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
        createWoodSet("red_mushroom", MapColor.RED, 0);
        //Brown Mushroom Blocks
        createPyriteBlock("brown_mushroom_stem", "log", Blocks.MUSHROOM_STEM);
        createWoodSet("brown_mushroom", MapColor.BROWN, 0);

        //Autogenerate dye blocks.
        for (int dyeIndex = 0; dyeIndex < dyes.length; dyeIndex++) {
            String dye = dyes[dyeIndex];
            int blockLux = 0;
            MapColor color;
            if (dyeIndex > 15) {
                color = switch (dye) {
                    case "glow" -> {
                        blockLux = 8;
                        yield MapColor.CYAN;
                    }
                    case "dragon" -> MapColor.BLACK;
                    case "star" -> {
                        blockLux = 15;
                        yield MapColor.OFF_WHITE;
                    }
                    case "honey" -> MapColor.YELLOW;
                    case "nostalgia" -> MapColor.BROWN;
                    case "rose" -> MapColor.BRIGHT_RED;
                    default -> MapColor.RED;
                };
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
                //coming soon - createPyriteBlock(dye+"_concrete", "block", Blocks.TERRACOTTA,color, blockLux);
                //Carpet block
                createPyriteBlock(dye + "_carpet", "carpet", Blocks.WHITE_WOOL, color, blockLux);
            }
            //Normal dye colours.
            else {
                color = DyeColor.valueOf(dye.toUpperCase()).getMapColor();
            }
            //Planks and plank products
            createWoodSet(dye + "_stained", color, blockLux);
            //Bricks and brick products
            createBrickSet(dye + "_brick", Blocks.BRICKS, color, blockLux);
            //Dyed Lamps
            createPyriteBlock(dye + "_lamp","block", 0.3f, color, 15);

        }
        //Autogenerate Wall Gates
        for (Block wallsBlock : walls_blocks) {
            //Find block ID
            String block = wallsBlock.toString().substring(wallsBlock.toString().indexOf(":") + 1, wallsBlock.toString().indexOf("}"));
            //If the block provided isn't a wall block, add the wall tag.
            if (!block.contains("wall")) {
                block = block + "_wall";
            }
            //Create block.
            createPyriteBlock(block + "_gate","fence_gate", wallsBlock);
        }


        //Register blocks and block items.
        for (int x = 0; x < pyriteBlockIDs.size(); x++) {
            Registry.register(Registries.BLOCK, new Identifier(modID, pyriteBlockIDs.get(x)), pyriteBlocks.get(x));
            Registry.register(Registries.ITEM, new Identifier(modID, pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new Item.Settings()));
        }
        //Registers items.
        for (int x = 0; x < pyriteItemIDs.size(); x++) {
            Registry.register(Registries.ITEM, new Identifier(modID, pyriteItemIDs.get(x)), pyriteItems.get(x));
        }
        //Registers the Pyrite item group.
        Registry.register(Registries.ITEM_GROUP, new Identifier(modID, "pyrite_group"), PYRITE_GROUP);
    }



    //Add items to the Pyrite Item Group
    private static final ItemGroup PYRITE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(pyriteBlocks.get(2)))
            .displayName(Text.translatable("itemGroup.pyrite.group"))
            .entries((context, entries) -> {
                for (Block block : pyriteBlocks) {
                    entries.add(block);
                }
                for (Item item : pyriteItems) {
                    entries.add(item);
                }
            })
            .build();
}
