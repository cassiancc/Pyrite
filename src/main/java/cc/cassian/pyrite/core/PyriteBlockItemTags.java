package cc.cassian.pyrite.core;

import net.minecraft.resources.ResourceLocation;
//~ if >26.1 'cc.cassian.pyrite.util' -> 'net.minecraft.tags' {
import cc.cassian.pyrite.util.BlockItemTagId;
//~}

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteBlockItemTags {
    public static final BlockItemTagId AMETHYST = tagKey("amethyst");
    public static final BlockItemTagId BRICKS = tagKey("bricks");
    public static final BlockItemTagId HIDDEN_FROM_RECIPE_VIEWERS = tagKey("c", "hidden_from_recipe_viewers");
    public static final BlockItemTagId MUSHROOM_STEM = tagKey("mushroom_stem");
    public static final BlockItemTagId CARPET = tagKey("carpet");
    public static final BlockItemTagId CHESTS = tagKey("chests");
    public static final BlockItemTagId CONCRETE_SLABS = tagKey("concrete_slabs");
    public static final BlockItemTagId CONCRETE_STAIRS = tagKey("concrete_stairs");
    public static final BlockItemTagId COPPER = tagKey("copper");
    public static final BlockItemTagId CRAFTING_TABLES = tagKey("crafting_tables");
    public static final BlockItemTagId DIAMOND = tagKey("diamond");
    public static final BlockItemTagId EMERALD = tagKey("emerald");
    public static final BlockItemTagId EXPOSED_COPPER = tagKey("exposed_copper");
    public static final BlockItemTagId FENCES = tagKey("fences");
    public static final BlockItemTagId GOLD = tagKey("gold");
    public static final BlockItemTagId IRON = tagKey("iron");
    public static final BlockItemTagId LADDERS = tagKey("ladders");
    public static final BlockItemTagId LAPIS = tagKey("lapis");
    public static final BlockItemTagId NETHERITE = tagKey("netherite");
    public static final BlockItemTagId OBSIDIAN = tagKey("obsidian");
    public static final BlockItemTagId OXIDIZED_COPPER = tagKey("oxidized_copper");
    public static final BlockItemTagId PLANKS = tagKey("planks");
    public static final BlockItemTagId REDSTONE = tagKey("redstone");
    public static final BlockItemTagId STAINED_GLASS = tagKey("stained_glass");
    public static final BlockItemTagId STAINED_FRAMED_GLASS = tagKey("stained_framed_glass");
    public static final BlockItemTagId TERRACOTTA = tagKey("terracotta");
    public static final BlockItemTagId TERRACOTTA_BRICKS = tagKey("terracotta_bricks");
    public static final BlockItemTagId WALL_GATES = tagKey("wall_gates");
    public static final BlockItemTagId QUARTZ = tagKey("quartz");
    public static final BlockItemTagId WEATHERED_COPPER = tagKey("weathered_copper");
    public static final BlockItemTagId CONCRETE = tagKey("concrete");
    public static final BlockItemTagId LAMPS = tagKey("lamps");
    public static final BlockItemTagId AZALEA_LOGS = tagKey("azalea_logs");


    public static BlockItemTagId tagKey(String id) {
        return tagKey(MOD_ID, id);
    }

    public static BlockItemTagId tagKey(String namespace, String id) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(namespace, id);
        return BlockItemTagId.create(identifier, identifier);
    }


}
