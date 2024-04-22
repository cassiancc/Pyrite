package cc.cassian.pyrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Pyrite implements ModInitializer {
    //List of Blocks and Block IDS.
    public static ArrayList<Block> pyriteBlocks = new ArrayList<>();
    static ArrayList<String> pyriteBlockIDs = new ArrayList<>();
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
            "nostalgia"
    };
    //List of blocks to be created for dyes.
    String[] generated = {
            "stained_planks",
            "stained_stairs",
            "stained_slab",
            "stained_pressure_plate",
            "stained_button",
            "stained_fence",
            "stained_fence_gate",
            "stained_door",
            "stained_trapdoor",
            "bricks",
            "brick_stairs",
            "brick_slab",
            "brick_wall",
            "lamp"
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
            Blocks.BRICK_WALL,
            Blocks.SANDSTONE_WALL,
            Blocks.RED_SANDSTONE_WALL,
            Blocks.PRISMARINE_WALL,
            Blocks.NETHER_BRICK_WALL,
            Blocks.RED_NETHER_BRICK_WALL,
            Blocks.END_STONE_BRICK_WALL

    };

    @Override
    public void onInitialize() {
        //Framed Glass - 0
        pyriteBlocks.add(new ModGlass());
        //Framed Glass Pane - 1
        pyriteBlocks.add(new ModGlassPane());
        //Cobblestone Bricks - 2
        pyriteBlocks.add(new Block(AbstractBlock.Settings.of(Material.STONE).strength(1.75f,6)));
        //Cobblestone Brick Stairs - 3
        pyriteBlocks.add(new ModStairs(pyriteBlocks.get(2).getDefaultState(), FabricBlockSettings.copy(Blocks.STONE_BRICK_STAIRS).build()));
        //Cobblestone Brick Stairs - 4
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_SLAB).build()));
        //Cobblestone Brick Walls - 5
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copy(Blocks.STONE_BRICK_WALL).build()));
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(pyriteBlocks.get(2)).build()));
        //Mossy Cobblestone Bricks - 6
        pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICKS).strength(3.0f, 3.0f).build()));
        //Mossy Cobblestone Brick Stairs - 7
        pyriteBlocks.add(new ModStairs(pyriteBlocks.get(7).getDefaultState(), FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICK_STAIRS).strength(3.0f, 3.0f).build()));
        //Mossy Cobblestone Brick Slabs - 8
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICK_SLAB).strength(3.0f, 3.0f).build()));
        //Mossy Cobblestone Brick Walls - 9
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copy(Blocks.MOSSY_STONE_BRICK_WALL).build()));
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(pyriteBlocks.get(7)).build()));
        //Grass Carpet - 10
        pyriteBlocks.add(new ModCarpet(DyeColor.GREEN, FabricBlockSettings.copy(Blocks.GREEN_CARPET).build()));
        //Mycelium Carpet - 11
        pyriteBlocks.add(new ModCarpet(DyeColor.PURPLE, FabricBlockSettings.copy(Blocks.PURPLE_CARPET).build()));
        //Podzol Carpet - 12
        pyriteBlocks.add(new ModCarpet(DyeColor.BROWN, FabricBlockSettings.copy(Blocks.BROWN_CARPET).build()));
        //Path Carpet - 13
        pyriteBlocks.add(new ModCarpet(DyeColor.BROWN, FabricBlockSettings.copy(Blocks.BROWN_CARPET).build()));
        //Nether Brick Fence Gate - 13
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(Blocks.NETHER_BRICK_FENCE).build()));
        //Cut Iron - 14
        pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
        //Cut Iron Stairs - 15
        pyriteBlocks.add(new ModStairs(pyriteBlocks.get(14).getDefaultState(), FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
        //Cut Iron Slab - 16
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
        //Cut Iron Wall - 16
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
        //Cut Iron Wall Gate - 16
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
        //Glowstone Lamp
        pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP).lightLevel(15).materialColor(MaterialColor.YELLOW).build()));
        pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.CRYING_OBSIDIAN).lightLevel(15).materialColor(MaterialColor.RED).build()));



        //Add all manually generated block IDs.
        pyriteBlockIDs.addAll(Arrays.asList(
                "framed_glass", "framed_glass_pane",
                "cobblestone_bricks", "cobblestone_brick_stairs", "cobblestone_brick_slab", "cobblestone_brick_wall", "cobblestone_brick_wall_gate",
                "mossy_cobblestone_bricks", "mossy_cobblestone_brick_stairs", "mossy_cobblestone_brick_slab", "mossy_cobblestone_brick_wall", "mossy_cobblestone_brick_wall_gate",
                "grass_carpet", "mycelium_carpet", "podzol_carpet", "path_carpet",
                "nether_brick_fence_gate",
                "cut_iron", "cut_iron_stairs", "cut_iron_slab", "cut_iron_wall", "cut_iron_wall_gate",
                "glowstone_lamp", "glowing_obsidian"
        ));
        int blockLux;
        DyeColor color;
        //Autogenerate dye blocks.
        for (String dye : dyes) {
            //Glow planks overrides
            if (Objects.equals(dye, "glow")) {
                blockLux = 8;
                color = DyeColor.GREEN;
            }
            //Dragon planks overrides
            else if (Objects.equals(dye, "dragon")) {
                blockLux = 0;
                color = DyeColor.PURPLE;
            }
            //Star planks overrides
            else if (Objects.equals(dye, "star")) {
                blockLux = 15;
                color = DyeColor.LIGHT_BLUE;
            }
            //Honey planks overrides
            else if (Objects.equals(dye, "honey")) {
                blockLux = 0;
                color = DyeColor.YELLOW;
            }
            else if (Objects.equals(dye, "nostalgia")) {
                blockLux = 0;
                color = DyeColor.BROWN;
            }
            //Normal dye colours.
            else {
                color = DyeColor.valueOf(dye.toUpperCase());
                blockLux = 0;
            }
            //Stained Planks
            pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).lightLevel(blockLux).materialColor(color).build()));
            //Stained Stairs
            pyriteBlocks.add(new ModStairs(pyriteBlocks.get(pyriteBlocks.size()-1).getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS).lightLevel(blockLux).materialColor(color).build()));
            //Stained Slabs
            pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_STAIRS).lightLevel(blockLux).materialColor(color).build()));
            //Stained Pressure Plates
            pyriteBlocks.add(new ModPressurePlate(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE).lightLevel(blockLux).materialColor(color).build()));
            //Stained Buttons
            pyriteBlocks.add(new ModButton(FabricBlockSettings.copy(Blocks.OAK_BUTTON).materialColor(color).lightLevel(blockLux).build()));
            //Stained Fences
            pyriteBlocks.add(new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).materialColor(color).lightLevel(blockLux).build()));
            //Stained Fence Gates
            pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).lightLevel(blockLux).materialColor(color).build()));
            //Stained Doors
            pyriteBlocks.add(new ModDoor(FabricBlockSettings.copy(Blocks.OAK_DOOR).build()));
            //Stained Trapdoors
            pyriteBlocks.add(new ModTrapdoor(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR).build()));
            //Dyed Bricks
            pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.BRICKS).lightLevel(blockLux).materialColor(color).build()));
            //Dyed Brick Stairs
            pyriteBlocks.add(new ModStairs(pyriteBlocks.get(pyriteBlocks.size()-1).getDefaultState(), FabricBlockSettings.copy(Blocks.BRICK_STAIRS).lightLevel(blockLux).materialColor(color).build()));
            //Dyed Brick Slab
            pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copy(Blocks.BRICK_SLAB).lightLevel(blockLux).materialColor(color).build()));
            //Dyed Brick Wall
            pyriteBlocks.add(new WallBlock(FabricBlockSettings.copy(Blocks.BRICK_WALL).lightLevel(blockLux).materialColor(color).build()));
            //Dyed Lamps
            pyriteBlocks.add(new Block(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP).lightLevel(15).materialColor(color).build()));

            //Generate Block IDs
            for (String generatedID : generated) {
                pyriteBlockIDs.add(dye + "_" + generatedID);
            }
        }
        //Autogenerate Wall Gates
        for (int w = 0; w < walls_blocks.length; w++) {
            //WALL GATES
            pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copy(walls_blocks[w]).build()));
            //Register
            String block = walls_blocks[w].toString();
            block = block.substring(block.indexOf(":")+1,block.indexOf("}"));
            if (!block.contains("wall")) {
                block = block + "_wall";
            }
            pyriteBlockIDs.add(block + "_gate");
        }
        //Register blocks, block items, and the Pyrite item group.
        for (int x = 0; x < pyriteBlockIDs.size(); x++) {
            Registry.register(Registry.BLOCK, new Identifier("pyrite", pyriteBlockIDs.get(x)), pyriteBlocks.get(x));
            Registry.register(Registry.ITEM, new Identifier("pyrite", pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new Item.Settings().group(PYRITE_GROUP)));
        }
    }
    //Add items to the Pyrite Item Group
    public static final ItemGroup PYRITE_GROUP = FabricItemGroupBuilder.build(
            new Identifier("pyrite"),
            () -> new ItemStack(pyriteBlocks.get(0))
    );}