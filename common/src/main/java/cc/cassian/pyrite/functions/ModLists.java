package cc.cassian.pyrite.functions;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModLists {
    //List of dyes.
    private final static String[] dyes = {
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
    //List of Vanilla wood types.
    private final static Block[] vanillaWood = {
        Blocks.SPRUCE_PLANKS,
        Blocks.BIRCH_PLANKS,
        Blocks.JUNGLE_PLANKS,
        Blocks.ACACIA_PLANKS,
        Blocks.DARK_OAK_PLANKS,
        Blocks.MANGROVE_PLANKS,
        Blocks.CHERRY_PLANKS,
        Blocks.BAMBOO_PLANKS,
        Blocks.CRIMSON_PLANKS,
        Blocks.WARPED_PLANKS
    };

    //List of Wall Blocks to generated Wall Gates for.
    private final static Block[] vanillaWalls = {
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

    final private static Block[] vanillaResourceBlocks = {
            Blocks.IRON_BLOCK,
            Blocks.GOLD_BLOCK,
            Blocks.EMERALD_BLOCK,
            Blocks.LAPIS_BLOCK,
            Blocks.REDSTONE_BLOCK,
            Blocks.DIAMOND_BLOCK,
            Blocks.NETHERITE_BLOCK,
            Blocks.QUARTZ_BLOCK,
            Blocks.AMETHYST_BLOCK,
            Blocks.COPPER_BLOCK,
            Blocks.EXPOSED_COPPER,
            Blocks.WEATHERED_COPPER,
            Blocks.OXIDIZED_COPPER

    };

    public static String[] getDyes() {
        return dyes;
    }

    public static Block[] getVanillaWood() {
        return vanillaWood;
    }

    public static Block[] getVanillaWalls() {
        return vanillaWalls;
    }

    public static Block[] getVanillaResourceBlocks() {
        return vanillaResourceBlocks;
    }
}
