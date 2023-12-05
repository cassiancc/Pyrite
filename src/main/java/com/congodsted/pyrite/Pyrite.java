package com.congodsted.pyrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Pyrite implements ModInitializer {
    public static final ItemGroup PYRITE = FabricItemGroupBuilder.build(
            new Identifier("pyrite"),
            () -> new ItemStack(Pyrite.COBBLESTONE_BRICKS)
    );
    //COBBLESTONE BRICKS
    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());

    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.MOSSY_COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(1.75f,6).build());


    //FRAMED GLASS
    public static final Block FRAMED_GLASS = new ModGlass();
    public static final Block FRAMED_GLASS_PANE = new ModGlassPane();


    //CRACKED COBBLESTONE
    public static final Block CRACKED_COBBLESTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f,3.0f).build());
    public static final Block CRACKED_COBBLESTONE_STAIRS = new ModStairs(Pyrite.CRACKED_COBBLESTONE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.0f,3.0f).build());
    public static final Block CRACKED_COBBLESTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f,3.0f).build());
    public static final Block CRACKED_COBBLESTONE_WALL = new WallBlock(FabricBlockSettings.copy(Blocks.COBBLESTONE_WALL).build());



    //1.16 FEATURES
    public static final Block BLACKSTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block BLACKSTONE_STAIRS = new ModStairs(Pyrite.BLACKSTONE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block BLACKSTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block BLACKSTONE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());

    public static final Block POLISHED_BLACKSTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block POLISHED_BLACKSTONE_STAIRS = new ModStairs(Pyrite.POLISHED_BLACKSTONE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block POLISHED_BLACKSTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block POLISHED_BLACKSTONE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block POLISHED_BLACKSTONE_BUTTON = new ModButton(AbstractBlock.Settings.of(Material.STONE));
    public static final Block POLISHED_BLACKSTONE_PRESSURE_PLATE = new ModPressurePlate(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.STONE).build());
    public static final Block CHISELED_POLISHED_BLACKSTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());

    public static final Block POLISHED_BLACKSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block POLISHED_BLACKSTONE_BRICK_STAIRS = new ModStairs(Pyrite.POLISHED_BLACKSTONE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block POLISHED_BLACKSTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block POLISHED_BLACKSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(2f,6).build());
    public static final Block CRACKED_POLISHED_BLACKSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());




    //1.17 FEATURES
    public static final Block MOSS_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC).strength(0.1f,0.1f).build());
    public static final Block TINTED_GLASS = new Block(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).nonOpaque().build());

    public static final Block POLISHED_DEEPSLATE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block POLISHED_DEEPSLATE_STAIRS = new ModStairs(Pyrite.POLISHED_DEEPSLATE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block POLISHED_DEEPSLATE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block POLISHED_DEEPSLATE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(3.55f,6).build());
    public static final Block DEEPSLATE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block CRACKED_DEEPSLATE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_BRICK_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(3.55f,6).build());

    public static final Block DEEPSLATE_TILES = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block CRACKED_DEEPSLATE_TILES = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_TILE_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_TILE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block DEEPSLATE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(3.55f,6).build());

    public static final Block DEEPSLATE = new PillarBlock(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block COBBLED_DEEPSLATE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block COBBLED_DEEPSLATE_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block COBBLED_DEEPSLATE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.5f,6).build());
    public static final Block COBBLED_DEEPSLATE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).strength(3.55f,6).build());









    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        String[] pyriteBlockIDs = {
                "cobblestone_bricks", "cobblestone_brick_stairs", "cobblestone_brick_slab","cobblestone_brick_wall",
                "mossy_cobblestone_bricks", "mossy_cobblestone_brick_stairs", "mossy_cobblestone_brick_slab", "mossy_cobblestone_brick_wall",
                "cracked_cobblestone", "cracked_cobblestone_stairs", "cracked_cobblestone_slab", "cracked_cobblestone_wall",
                "framed_glass", "framed_glass_pane",
                "blackstone", "blackstone_stairs", "blackstone_slab", "blackstone_wall",
                "polished_blackstone", "polished_blackstone_stairs", "polished_blackstone_slab", "polished_blackstone_wall", "chiseled_polished_blackstone","polished_blackstone_button", "polished_blackstone_pressure_plate",
                "polished_blackstone_bricks", "polished_blackstone_brick_stairs", "polished_blackstone_brick_slab", "polished_blackstone_brick_wall", "cracked_polished_blackstone_bricks",
                "moss_block",
                "tinted_glass",
                "deepslate",
                "cobbled_deepslate", "cobbled_deepslate_stairs", "cobbled_deepslate_slab", "cobbled_deepslate_wall",
                "polished_deepslate", "polished_deepslate_slab", "polished_deepslate_stairs", "polished_deepslate_wall",
                "deepslate_bricks", "cracked_deepslate_bricks", "deepslate_brick_slab", "deepslate_brick_stairs", "deepslate_brick_wall",
                "deepslate_tiles", "cracked_deepslate_tiles","deepslate_tile_slab", "deepslate_tile_stairs", "deepslate_tile_wall"
        };
        Block[] pyriteBlocks = {
                COBBLESTONE_BRICKS, COBBLESTONE_BRICK_STAIRS,COBBLESTONE_BRICK_SLAB,COBBLESTONE_BRICK_WALL,
                MOSSY_COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICK_STAIRS,MOSSY_COBBLESTONE_BRICK_SLAB, MOSSY_COBBLESTONE_BRICK_WALL,
                CRACKED_COBBLESTONE, CRACKED_COBBLESTONE_STAIRS, CRACKED_COBBLESTONE_SLAB, CRACKED_COBBLESTONE_WALL,
                FRAMED_GLASS, FRAMED_GLASS_PANE,
                BLACKSTONE, BLACKSTONE_STAIRS, BLACKSTONE_SLAB, BLACKSTONE_WALL,
                POLISHED_BLACKSTONE, POLISHED_BLACKSTONE_STAIRS, POLISHED_BLACKSTONE_SLAB, POLISHED_BLACKSTONE_WALL, CHISELED_POLISHED_BLACKSTONE,POLISHED_BLACKSTONE_BUTTON, POLISHED_BLACKSTONE_PRESSURE_PLATE,
                POLISHED_BLACKSTONE_BRICKS, POLISHED_BLACKSTONE_BRICK_STAIRS, POLISHED_BLACKSTONE_BRICK_SLAB, POLISHED_BLACKSTONE_BRICK_WALL, CRACKED_POLISHED_BLACKSTONE_BRICKS,
                MOSS_BLOCK,
                TINTED_GLASS,
                DEEPSLATE,
                COBBLED_DEEPSLATE, COBBLED_DEEPSLATE_STAIRS, COBBLED_DEEPSLATE_SLAB, COBBLED_DEEPSLATE_WALL,
                POLISHED_DEEPSLATE, POLISHED_DEEPSLATE_SLAB, POLISHED_DEEPSLATE_STAIRS, POLISHED_DEEPSLATE_WALL,
                DEEPSLATE_BRICKS, CRACKED_DEEPSLATE_BRICKS, DEEPSLATE_BRICK_SLAB, DEEPSLATE_BRICK_STAIRS, DEEPSLATE_BRICK_WALL,
                DEEPSLATE_TILES, CRACKED_DEEPSLATE_TILES, DEEPSLATE_TILE_SLAB, DEEPSLATE_TILE_STAIRS, DEEPSLATE_TILE_WALL
        };
        for (int i = 0; i < pyriteBlockIDs.length; i++) {
            Registry.register(Registry.BLOCK, new Identifier("pyrite", pyriteBlockIDs[i]), pyriteBlocks[i]);
            Registry.register(Registry.ITEM, new Identifier("pyrite", pyriteBlockIDs[i]), new BlockItem(pyriteBlocks[i], new Item.Settings().group(Pyrite.PYRITE)));
        }
    }
}
