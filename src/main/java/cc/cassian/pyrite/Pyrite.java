package cc.cassian.pyrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
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

    @Override
    public void onInitialize() {
        //Framed Glass - 0
        pyriteBlocks.add(new ModGlass());
        //Framed Glass Pane - 1
        pyriteBlocks.add(new PaneBlock(FabricBlockSettings.create().nonOpaque().strength(2.0f).sounds(BlockSoundGroup.GLASS)));
        //Cobblestone Bricks - 2
        pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        //Cobblestone Brick Stairs - 3
        pyriteBlocks.add(new ModStairs(pyriteBlocks.get(2).getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_BRICK_STAIRS)));
        //Cobblestone Brick Stairs - 4
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_SLAB)));
        //Cobblestone Brick Walls - 5
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL)));
        //Mossy Cobblestone Bricks - 6
        pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS).strength(3.0f)));
        //Mossy Cobblestone Brick Stairs - 7
        pyriteBlocks.add(new ModStairs(pyriteBlocks.get(6).getDefaultState(),FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_STAIRS).strength(3.0f)));
        //Mossy Cobblestone Brick Slabs - 8
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_SLAB).strength(3.0f)));
        //Mossy Cobblestone Brick Walls - 9
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_WALL)));
        //Grass Carpet - 10
        pyriteBlocks.add(new CarpetBlock(FabricBlockSettings.copyOf(Blocks.MOSS_CARPET)));
        //Mycelium Carpet - 11
        pyriteBlocks.add(new CarpetBlock(FabricBlockSettings.copyOf(Blocks.MOSS_CARPET)));
        //Podzol Carpet - 12
        pyriteBlocks.add(new CarpetBlock(FabricBlockSettings.copyOf(Blocks.MOSS_CARPET)));
        //Path Carpet - 13
        pyriteBlocks.add(new CarpetBlock(FabricBlockSettings.copyOf(Blocks.MOSS_CARPET)));
        //Nether Brick Fence Gate - 14
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICK_FENCE), WoodType.CRIMSON));
        //Cut Iron - 15
        pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
        //Cut Iron Stairs - 16
        pyriteBlocks.add(new StairsBlock(pyriteBlocks.get(14).getDefaultState(),FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
        //Cut Iron Slab - 17
        pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
        //Cut Iron Wall - 18
        pyriteBlocks.add(new WallBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
        //Cut Iron Wall Gate - 19
        pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), WoodType.CRIMSON));



        //Add all manually generated block IDs.
        pyriteBlockIDs.addAll(Arrays.asList(
                "framed_glass", "framed_glass_pane",
                "cobblestone_bricks", "cobblestone_brick_stairs", "cobblestone_brick_slab", "cobblestone_brick_wall",
                "mossy_cobblestone_bricks", "mossy_cobblestone_brick_stairs", "mossy_cobblestone_brick_slab", "mossy_cobblestone_brick_wall",
                "grass_carpet", "mycelium_carpet", "podzol_carpet", "path_carpet",
                "nether_brick_fence_gate",
                "cut_iron", "cut_iron_stairs", "cut_iron_slab", "cut_iron_wall", "cut_iron_wall_gate"
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
            BlockSetType DYED_WOOD_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(new Identifier("pyrite", dye + "wood"));
            WoodType DYED_WOOD_TYPE = WoodTypeBuilder.copyOf(WoodType.CHERRY).register(new Identifier("pyrite", dye + "wood"), DYED_WOOD_SET);
            //Stained Planks
            pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).luminance(blockLux).mapColor(color)));
            //Stained Stairs
            pyriteBlocks.add(new ModStairs(pyriteBlocks.get(pyriteBlocks.size()-1).getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).luminance(blockLux).mapColor(color)));
            //Stained Slabs
            pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).luminance(blockLux).mapColor(color)));
            //Stained Pressure Plates
            pyriteBlocks.add(new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE).luminance(blockLux).mapColor(color), DYED_WOOD_SET));
            //Stained Buttons
            pyriteBlocks.add(new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON).mapColor(color).luminance(blockLux), DYED_WOOD_SET, 40, true));
            //Stained Fences
            pyriteBlocks.add(new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE).mapColor(color).luminance(blockLux)));
            //Stained Fence Gates
            pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE).mapColor(color).luminance(blockLux), DYED_WOOD_TYPE));
            //Stained Doors
            pyriteBlocks.add(new DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR), DYED_WOOD_SET));
            //Stained Trapdoors
            pyriteBlocks.add(new TrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR), DYED_WOOD_SET));
            //Dyed Bricks
            pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.BRICKS).luminance(blockLux).mapColor(color)));
            //Dyed Brick Stairs
            pyriteBlocks.add(new ModStairs(pyriteBlocks.get(pyriteBlocks.size()-1).getDefaultState(), FabricBlockSettings.copyOf(Blocks.BRICK_STAIRS).luminance(blockLux).mapColor(color)));
            //Dyed Brick Slab
            pyriteBlocks.add(new SlabBlock(FabricBlockSettings.copyOf(Blocks.BRICK_SLAB).luminance(blockLux).mapColor(color)));
            //Dyed Brick Wall
            pyriteBlocks.add(new WallBlock(FabricBlockSettings.copyOf(Blocks.BRICK_WALL).luminance(blockLux).mapColor(color)));
            //Dyed Lamps
            pyriteBlocks.add(new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP).luminance(15).mapColor(color)));

            //Generate Block IDs
            for (String generatedID : generated) {
                pyriteBlockIDs.add(dye + "_" + generatedID);
            }
        }
        //Autogenerate Wall Gates
        for (int w = 0; w < walls_blocks.length; w++) {
            //WALL GATES
            pyriteBlocks.add(new FenceGateBlock(FabricBlockSettings.copyOf(walls_blocks[w]), WoodType.CRIMSON));
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
            Registry.register(Registries.BLOCK, new Identifier("pyrite", pyriteBlockIDs.get(x)), pyriteBlocks.get(x));
            Registry.register(Registries.ITEM, new Identifier("pyrite", pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new FabricItemSettings()));
        }
        Registry.register(Registries.ITEM_GROUP, new Identifier("pyrite", "pyrite_group"), PYRITE_GROUP);
    }
    //Add items to the Pyrite Item Group
    private static final ItemGroup PYRITE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(pyriteBlocks.get(0)))
            .displayName(Text.translatable("itemGroup.pyrite.group"))
            .entries((context, entries) -> {
                for (Block block : pyriteBlocks) {
                    entries.add(block);
                }
            })
            .build();
}
