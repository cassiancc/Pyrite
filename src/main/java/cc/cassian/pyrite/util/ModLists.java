package cc.cassian.pyrite.util;

import cc.cassian.pyrite.Pyrite;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cc.cassian.pyrite.entries.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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

    public final static List<String> PYRITE_DYES = List.of(
            "honey",
            "poisonous",
            "glow",
            "nostalgia",
            "star",
            "dragon",
            "rose"
    );

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
    public final static Block[] VANILLA_WOOD = {
            Blocks.SPRUCE_PLANKS,
            Blocks.BIRCH_PLANKS,
            Blocks.JUNGLE_PLANKS,
            Blocks.ACACIA_PLANKS,
            Blocks.DARK_OAK_PLANKS,
            Blocks.MANGROVE_PLANKS,
            Blocks.CHERRY_PLANKS,
            Blocks.BAMBOO_PLANKS,
            Blocks.CRIMSON_PLANKS,
            Blocks.WARPED_PLANKS,
            //? if >1.21.4 {
            Blocks.PALE_OAK_PLANKS
            //?}
    };

    //List of Wall Blocks to generated Wall Gates for.
    public final static Block[] VANILLA_WALLS = {
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
            Blocks.TUFF_WALL,
            //? if >1.21.4
            Blocks.RESIN_BRICK_WALL
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

    public final static Map<String, BlockEntry<Block>> WOOL_MATCH = new LinkedHashMap<>();
    public final static Map<String, BlockEntry<Block>> CARPET_MATCH = new LinkedHashMap<>();
    public final static Map<String, BlockEntry<Block>> CONCRETE_MATCH = new LinkedHashMap<>();

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

        WOOL_MATCH.put("glow", VanillaConstants.GREEN_WOOL);
        WOOL_MATCH.put("dragon", VanillaConstants.PURPLE_WOOL);
        WOOL_MATCH.put("star", VanillaConstants.CYAN_WOOL);
        WOOL_MATCH.put("honey", VanillaConstants.ORANGE_WOOL);
        WOOL_MATCH.put("rose", VanillaConstants.MAGENTA_WOOL);
        WOOL_MATCH.put("poisonous", VanillaConstants.YELLOW_WOOL);
        WOOL_MATCH.put("nostalgia", VanillaConstants.CYAN_WOOL);

        CARPET_MATCH.put("glow", VanillaConstants.GREEN_CARPET);
        CARPET_MATCH.put("dragon", VanillaConstants.PURPLE_CARPET);
        CARPET_MATCH.put("star", VanillaConstants.CYAN_CARPET);
        CARPET_MATCH.put("honey", VanillaConstants.ORANGE_CARPET);
        CARPET_MATCH.put("rose", VanillaConstants.MAGENTA_CARPET);
        CARPET_MATCH.put("poisonous", VanillaConstants.YELLOW_CARPET);
        CARPET_MATCH.put("nostalgia", VanillaConstants.CYAN_CARPET);

        CONCRETE_MATCH.put("glow", VanillaConstants.GREEN_CONCRETE);
        CONCRETE_MATCH.put("dragon", VanillaConstants.PURPLE_CONCRETE);
        CONCRETE_MATCH.put("star", VanillaConstants.LIGHT_BLUE_CONCRETE);
        CONCRETE_MATCH.put("honey", VanillaConstants.ORANGE_CONCRETE);
        CONCRETE_MATCH.put("rose", VanillaConstants.MAGENTA_CONCRETE);
        CONCRETE_MATCH.put("poisonous", VanillaConstants.YELLOW_CONCRETE);
        CONCRETE_MATCH.put("nostalgia", VanillaConstants.CYAN_CONCRETE);

        DATAPACKS.put("pyrite_oddities", Pyrite.CONFIG.oddities);
        DATAPACKS.put("pyrite_azalea", Pyrite.CONFIG.azalea);
        DATAPACKS.put("pyrite_mushrooms", Pyrite.CONFIG.mushrooms);
        DATAPACKS.put("pyrite_crafting_tables", Pyrite.CONFIG.crafting_tables);

    }
}