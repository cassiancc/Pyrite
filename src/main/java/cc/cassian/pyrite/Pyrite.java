package cc.cassian.pyrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
import java.util.ArrayList; // import the ArrayList class

public class Pyrite implements ModInitializer {

    //FRAMED GLASS
    public static final Block FRAMED_GLASS = new ModGlass();
    public static final Block FRAMED_GLASS_PANE = new PaneBlock(FabricBlockSettings.create().nonOpaque().strength(2.0f).sounds(BlockSoundGroup.GLASS));

//    COBBLESTONE BRICKS

    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
    public static final Block COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_BRICK_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL));

    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.MOSSY_COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_STAIRS).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_SLAB).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_WALL));

    static Block[] pyriteBlocks = {
            COBBLESTONE_BRICKS, COBBLESTONE_BRICK_STAIRS,COBBLESTONE_BRICK_SLAB,COBBLESTONE_BRICK_WALL,
            MOSSY_COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICK_STAIRS,MOSSY_COBBLESTONE_BRICK_SLAB, MOSSY_COBBLESTONE_BRICK_WALL,
            FRAMED_GLASS, FRAMED_GLASS_PANE,
    };
    String[] pyriteBlockIDs = {
            "cobblestone_bricks", "cobblestone_brick_stairs", "cobblestone_brick_slab","cobblestone_brick_wall",
            "mossy_cobblestone_bricks", "mossy_cobblestone_brick_stairs", "mossy_cobblestone_brick_slab", "mossy_cobblestone_brick_wall",
            "framed_glass", "framed_glass_pane",

    };

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
            "black"
    };
    static ArrayList<Block> generatedBlocks = new ArrayList<Block>();

    public static final BlockSetType DYED_WOOD_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(new Identifier("pyrite", "dyed_wood"));


    @Override
    public void onInitialize() {
        for (String dye : dyes) {
            BlockSetType DYED_WOOD_SET = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(new Identifier("pyrite", dye + "wood"));
            //PLANKS
            Block DYE_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).mapColor(DyeColor.valueOf(dye.toUpperCase())));
            generatedBlocks.add(DYE_PLANKS);
            Registry.register(Registries.BLOCK, new Identifier("pyrite", dye + "_stained_planks"), DYE_PLANKS);
            Registry.register(Registries.ITEM, new Identifier("pyrite", dye + "_stained_planks"), new BlockItem(DYE_PLANKS, new FabricItemSettings()));
            //STAIRS
            Block DYE_STAIRS = new ModStairs(DYE_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).mapColor(DyeColor.valueOf(dye.toUpperCase())));
            generatedBlocks.add(DYE_STAIRS);
            Registry.register(Registries.BLOCK, new Identifier("pyrite", dye + "_stained_stairs"), DYE_STAIRS);
            Registry.register(Registries.ITEM, new Identifier("pyrite", dye + "_stained_stairs"), new BlockItem(DYE_STAIRS, new FabricItemSettings()));
            //SLABS
            Block DYE_SLABS = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_STAIRS).mapColor(DyeColor.valueOf(dye.toUpperCase())));
            generatedBlocks.add(DYE_SLABS);
            Registry.register(Registries.BLOCK, new Identifier("pyrite", dye + "_stained_slab"), DYE_SLABS);
            Registry.register(Registries.ITEM, new Identifier("pyrite", dye + "_stained_slab"), new BlockItem(DYE_SLABS, new FabricItemSettings()));
            //PRESSURE PLATES
            Block DYE_PLATES = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), DYED_WOOD_SET);
            generatedBlocks.add(DYE_PLATES);
            Registry.register(Registries.BLOCK, new Identifier("pyrite", dye + "_stained_pressure_plate"), DYE_PLATES);
            Registry.register(Registries.ITEM, new Identifier("pyrite", dye + "_stained_pressure_plate"), new BlockItem(DYE_PLATES, new FabricItemSettings()));
            //BUTTON
            Block DYE_BUTTONS = new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), DYED_WOOD_SET, 40, true);
            generatedBlocks.add(DYE_BUTTONS);
            Registry.register(Registries.BLOCK, new Identifier("pyrite", dye + "_stained_button"), DYE_BUTTONS);
            Registry.register(Registries.ITEM, new Identifier("pyrite", dye + "_stained_button"), new BlockItem(DYE_BUTTONS, new FabricItemSettings()));

        }



        for (int i = 0; i < pyriteBlockIDs.length; i++) {
            Registry.register(Registries.BLOCK, new Identifier("pyrite", pyriteBlockIDs[i]), pyriteBlocks[i]);
            Registry.register(Registries.ITEM, new Identifier("pyrite", pyriteBlockIDs[i]), new BlockItem(pyriteBlocks[i], new FabricItemSettings()));
        }
        Registry.register(Registries.ITEM_GROUP, new Identifier("pyrite", "pyrite_group"), PYRITE_GROUP);


        //FRAMED GLASS
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            content.addAfter(Items.TINTED_GLASS, FRAMED_GLASS_PANE);
            content.addAfter(Items.TINTED_GLASS, FRAMED_GLASS);


        });
    }

    private static final ItemGroup PYRITE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(COBBLESTONE_BRICKS))
            .displayName(Text.translatable("itemGroup.pyrite.group"))
            .entries((context, entries) -> {
                for (Block block : pyriteBlocks) {
                    entries.add(block);
                }
                for (Block block : generatedBlocks) {
                    entries.add(block);
                }
            })
            .build();
}
