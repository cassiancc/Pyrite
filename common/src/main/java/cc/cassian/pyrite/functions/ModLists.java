package cc.cassian.pyrite.functions;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModLists {
    //List of dyes.
    public final static String[] DYES = {
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "honey",
            "yellow",
            "poisonous",
            "lime",
            "green",
            "glow",
            "cyan",
            "nostalgia",
            "star",
            "light_blue",
            "blue",
            "purple",
            "dragon",
            "magenta",
            "rose",
            "pink",
    };
    public final static String[] VANILLA_DYES = {
            "white",
            "light_gray",
            "gray",
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "lime",
            "green",
            "cyan",
            "light_blue",
            "blue",
            "purple",
            "magenta",
            "pink",
    };
    //List of Vanilla wood types.
    private final static Block[] VANILLA_WOOD = {
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
    private final static Block[] VANILLA_WALLS = {
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
            Blocks.END_STONE_BRICK_WALL,
            Blocks.POLISHED_TUFF_WALL,
            Blocks.TUFF_BRICK_WALL,
            Blocks.TUFF_WALL
    };

    final private static Block[] VANILLA_RESOURCE_BLOCKS = {
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

    public static final Map<String, Block> TURF_SETS = Map.of(
            "grass", Blocks.GRASS_BLOCK,
            "mycelium", Blocks.MYCELIUM,
            "podzol", Blocks.PODZOL,
            "path", Blocks.DIRT_PATH,
            "nostalgia_grass", Blocks.GRASS_BLOCK
    );

    public static final Map<String, Block> NOSTALGIA_BLOCKS = Map.of(
            "nostalgia_grass_block", Blocks.GRASS_BLOCK,
            "nostalgia_cobblestone", Blocks.COBBLESTONE,
            "nostalgia_mossy_cobblestone", Blocks.MOSSY_COBBLESTONE,
            "nostalgia_netherrack", Blocks.NETHERRACK
    );

    public final static Map<String, Boolean> DATAPACKS = new LinkedHashMap<>();

    public final static Map<String, Block> WOOL_MATCH = new LinkedHashMap<>();
    public final static Map<String, Block> CARPET_MATCH = new LinkedHashMap<>();
    public final static Map<String, Block> CONCRETE_MATCH = new LinkedHashMap<>();

    public static final LinkedHashMap<String, Block> FLOWERS = new LinkedHashMap<>();
    public static void populateLinkedHashMaps() {
        FLOWERS.put("rose", Blocks.POPPY);
        FLOWERS.put("orange_rose", Blocks.POPPY);
        FLOWERS.put("white_rose", Blocks.POPPY);
        FLOWERS.put("pink_rose", Blocks.POPPY);
        FLOWERS.put("blue_rose", Blocks.POPPY);
        FLOWERS.put("paeonia", Blocks.PEONY);
        FLOWERS.put("buttercup", Blocks.PEONY);
        FLOWERS.put("pink_daisy", Blocks.PEONY);

        WOOL_MATCH.put("glow", Blocks.GREEN_WOOL);
        WOOL_MATCH.put("dragon", Blocks.PURPLE_WOOL);
        WOOL_MATCH.put("star", Blocks.CYAN_WOOL);
        WOOL_MATCH.put("honey", Blocks.ORANGE_WOOL);
        WOOL_MATCH.put("rose", Blocks.MAGENTA_WOOL);
        WOOL_MATCH.put("poisonous", Blocks.YELLOW_WOOL);
        WOOL_MATCH.put("nostalgia", Blocks.CYAN_WOOL);

        CARPET_MATCH.put("glow", Blocks.GREEN_CARPET);
        CARPET_MATCH.put("dragon", Blocks.PURPLE_CARPET);
        CARPET_MATCH.put("star", Blocks.CYAN_CARPET);
        CARPET_MATCH.put("honey", Blocks.ORANGE_CARPET);
        CARPET_MATCH.put("rose", Blocks.MAGENTA_CARPET);
        CARPET_MATCH.put("poisonous", Blocks.YELLOW_CARPET);
        CARPET_MATCH.put("nostalgia", Blocks.CYAN_CARPET);

        CONCRETE_MATCH.put("glow", Blocks.GREEN_CONCRETE);
        CONCRETE_MATCH.put("dragon", Blocks.PURPLE_CONCRETE);
        CONCRETE_MATCH.put("star", Blocks.LIGHT_BLUE_CONCRETE);
        CONCRETE_MATCH.put("honey", Blocks.ORANGE_CONCRETE);
        CONCRETE_MATCH.put("rose", Blocks.MAGENTA_CONCRETE);
        CONCRETE_MATCH.put("poisonous", Blocks.YELLOW_CONCRETE);
        CONCRETE_MATCH.put("nostalgia", Blocks.CYAN_CONCRETE);

        DATAPACKS.put("pyrite_oddities", Pyrite.CONFIG.oddities);
        DATAPACKS.put("pyrite_azalea", Pyrite.CONFIG.azalea);
        DATAPACKS.put("pyrite_mushrooms", Pyrite.CONFIG.mushrooms);
        DATAPACKS.put("pyrite_crafting_tables", Pyrite.CONFIG.crafting_tables);

    }

    public static String[] getDyes() {
        return DYES;
    }

    public static Block[] getVanillaWood() {
        return VANILLA_WOOD;
    }

    public static Block[] getVanillaWalls() {
        return VANILLA_WALLS;
    }

    public static Block[] getVanillaResourceBlocks() {
        return VANILLA_RESOURCE_BLOCKS;
    }
}