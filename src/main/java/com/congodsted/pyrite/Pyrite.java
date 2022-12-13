package com.congodsted.pyrite;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Pyrite implements ModInitializer {
    public static final ItemGroup PYRITE = FabricItemGroupBuilder.build(
            new Identifier("pyrite"),
            () -> new ItemStack(Pyrite.FRAMED_GLASS)
    );
    //FRAMED GLASS
    public static final Block FRAMED_GLASS = new ModGlass();
    public static final Block FRAMED_GLASS_PANE = new PaneBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().strength(2.0f));

    //COBBLESTONE BRICKS

    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
    public static final Block COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_BRICK_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL));

    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.MOSSY_COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_WALL));

    @Override
    public void onInitialize() {
        //FRAMED GLASS
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "framed_glass"), FRAMED_GLASS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "framed_glass_pane"), FRAMED_GLASS_PANE);
        Registry.register(Registry.ITEM, new Identifier("pyrite", "framed_glass"), new BlockItem(FRAMED_GLASS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "framed_glass_pane"), new BlockItem(FRAMED_GLASS_PANE, new FabricItemSettings().group(Pyrite.PYRITE)));

        //COBBLESTONE BRICKS
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_bricks"), COBBLESTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_stairs"), COBBLESTONE_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_slab"), COBBLESTONE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_wall"), COBBLESTONE_BRICK_WALL);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL);
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_bricks"), new BlockItem(COBBLESTONE_BRICKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_brick_stairs"), new BlockItem(COBBLESTONE_BRICK_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_brick_slab"), new BlockItem(COBBLESTONE_BRICK_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_bricks"), new BlockItem(MOSSY_COBBLESTONE_BRICKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_brick_stairs"), new BlockItem(MOSSY_COBBLESTONE_BRICK_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_brick_slab"), new BlockItem(MOSSY_COBBLESTONE_BRICK_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));


    }
}
