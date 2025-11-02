package cc.cassian.pyrite.registry;

import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.functions.ModHelpers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import java.util.*;
import java.util.function.Supplier;

public class PyriteItemGroups {
    public static final ArrayList<Supplier<Block>> REDSTONE_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Item>> SIGNS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CRAFTING_TABLES = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> FLOWERS = new ArrayList<>();
    public static final LinkedHashMap<String, Supplier<FlowerPotBlock>> POTTED_FLOWERS = new LinkedHashMap<>();
    public static final ArrayList<Supplier<Item>> DYES = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> IRON_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> GOLD_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> EMERALD_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> LAPIS_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> REDSTONE_RESOURCE_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DIAMOND_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> NETHERITE_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> QUARTZ_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> AMETHYST_BLOCKS = new ArrayList<>();
    public static final LinkedHashMap<String, Supplier<Block>> COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> EXPOSED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> WEATHERED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> OXIDIZED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> WAXED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> WAXED_EXPOSED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> WAXED_WEATHERED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<String, Supplier<Block>> WAXED_OXIDIZED_COPPER_BLOCKS = new LinkedHashMap<>();
    public static final ArrayList<Supplier<Block>> COLOURED_NETHER_BRICKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> COBBLESTONE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> SMOOTH_STONE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> ANDESITE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> GRANITE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DIORITE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CALCITE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> TUFF = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DEEPSLATE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> COBBLED_DEEPSLATE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> SANDSTONE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> RED_SANDSTONE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> STAINED_GLASS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> STAINED_GLASS_PANES = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> FRAMED_GLASS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> FRAMED_GLASS_PANES = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CONCRETE = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CONCRETE_POWDER = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> WOOL = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> CARPET = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> TERRACOTTA = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> TERRACOTTA_BRICKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> TORCH = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> TORCH_LEVER = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> GRASS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> NOSTALGIA_GRASS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> GRAVEL = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> PODZOL = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> MYCELIUM = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DIRT_PATH = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> LAMPS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> OBSIDIAN = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DYED_BRICKS = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> DYED_WOOD = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> WOOD = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> RED_MUSHROOM = new ArrayList<>();
    public static final ArrayList<Supplier<Block>> BROWN_MUSHROOM = new ArrayList<>();
    public static final LinkedHashMap<Block, Supplier<Block>> FUNCTIONAL = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, Supplier<Block>> BUILDING_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, Supplier<Block>> COLORED_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, Supplier<Block>> NATURAL = new LinkedHashMap<>();


    public static Collection<ItemStack> getBlockCollectionList(Collection<Supplier<Block>> items) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (Supplier<Block> block : items) {
            var stack = block.get().asItem().getDefaultInstance();
            if (!stack.is(PyriteTags.HIDDEN_FROM_RECIPE_VIEWERS) && stack.is(PyriteTags.ENABLED)) {
                stacks.add(stack);
            } else {
//                ModHelpers.log(stack.getName().getString() + " was not added to its item group as it was disabled!");
            }
        }
        return stacks;
    }

    public static Collection<ItemStack> getItemCollectionList(ArrayList<Supplier<Item>> items) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (Supplier<Item> item : items) {
            var stack = item.get().getDefaultInstance();
            if (!stack.is(PyriteTags.HIDDEN_FROM_RECIPE_VIEWERS) && stack.is(PyriteTags.ENABLED)) {
                stacks.add(stack);
            } else {
//                ModHelpers.log(stack.getName().getString() + " was not added to its item group as it was disabled!");
            }
        }
        return stacks;
    }

    public static void match(Supplier<Block> newBlock, Block copyBlock, String group, String blockID) {
        if (blockID.equals("glowstone_lamp"))
            LAMPS.add(newBlock);
        switch (group) {
            case "iron":
                IRON_BLOCKS.add(newBlock);
                break;
            case "gold":
                GOLD_BLOCKS.add(newBlock);
                break;
            case "emerald":
                EMERALD_BLOCKS.add(newBlock);
                break;
            case "lapis":
                LAPIS_BLOCKS.add(newBlock);
                break;
            case "diamond":
                DIAMOND_BLOCKS.add(newBlock);
                break;
            case "redstone":
                REDSTONE_RESOURCE_BLOCKS.add(newBlock);
                break;
            case "redstone-group":
                REDSTONE_BLOCKS.add(newBlock);
                break;
            case "torch_lever":
                TORCH_LEVER.add(newBlock);
                break;
            case "netherite":
                NETHERITE_BLOCKS.add(newBlock);
                break;
            case "quartz":
                QUARTZ_BLOCKS.add(newBlock);
                break;
            case "amethyst":
                AMETHYST_BLOCKS.add(newBlock);
                break;
            case "copper":
                COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "exposed_copper":
                EXPOSED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "weathered_copper":
                WEATHERED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "oxidized_copper":
                OXIDIZED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "waxed_copper":
                WAXED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "waxed_exposed_copper":
                WAXED_EXPOSED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "waxed_weathered_copper":
                WAXED_WEATHERED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "waxed_oxidized_copper":
                WAXED_OXIDIZED_COPPER_BLOCKS.put(blockID, newBlock);
                break;
            case "coloured_nether_bricks":
                COLOURED_NETHER_BRICKS.add(newBlock);
                break;
            case "cobblestone_brick", "mossy_cobblestone_brick":
                COBBLESTONE.add(newBlock);
                break;
            case "smooth_stone_brick", "mossy_smooth_stone_brick":
                SMOOTH_STONE.add(newBlock);
                break;
            case "granite_brick", "mossy_granite_brick":
                GRANITE.add(newBlock);
                break;
            case "diorite_brick", "mossy_diorite_brick":
                DIORITE.add(newBlock);
                break;
            case "andesite_brick", "mossy_andesite_brick":
                ANDESITE.add(newBlock);
                break;
            case "calcite_brick", "mossy_calcite_brick":
                CALCITE.add(newBlock);
                break;
            case "mossy_tuff_brick":
                TUFF.add(newBlock);
                break;
            case "mossy_deepslate_brick":
                DEEPSLATE.add(newBlock);
                break;
            case "cobbled_deepslate_brick", "mossy_cobbled_deepslate_brick":
                COBBLED_DEEPSLATE.add(newBlock);
                break;
            case "sandstone_brick":
                SANDSTONE.add(newBlock);
                break;
            case "red_sandstone_brick":
                RED_SANDSTONE.add(newBlock);
                break;
            case "crafting_table":
                CRAFTING_TABLES.add(newBlock);
                break;
            case "stained_glass":
                STAINED_GLASS.add(newBlock);
                break;
            case "stained_glass_pane":
                STAINED_GLASS_PANES.add(newBlock);
                break;
            case "framed_glass":
                FRAMED_GLASS.add(newBlock);
                break;
            case "framed_glass_pane":
                FRAMED_GLASS_PANES.add(newBlock);
                break;
            case "wool":
                WOOL.add(newBlock);
                break;
            case "concrete":
                CONCRETE.add(newBlock);
                break;
            case "carpet":
                CARPET.add(newBlock);
                break;
            case "concrete_powder":
                CONCRETE_POWDER.add(newBlock);
                break;
            case "terracotta":
                TERRACOTTA.add(newBlock);
                break;
            case "terracotta_bricks", "terracotta_brick":
                TERRACOTTA_BRICKS.add(newBlock);
                break;
            case "lamp":
                LAMPS.add(newBlock);
                break;
            case "torch":
                TORCH.add(newBlock);
                break;
            case "grass":
                GRASS.add(newBlock);
                break;
            case "nostalgia_grass":
                NOSTALGIA_GRASS.add(newBlock);
                break;
            case "gravel":
                GRAVEL.add(newBlock);
                break;
            case "nostalgia_grass_block":
                NOSTALGIA_GRASS.addFirst(newBlock);
                break;
            case "obsidian":
                OBSIDIAN.add(newBlock);
                break;
            case "path":
                DIRT_PATH.add(newBlock);
                break;
            case "mycelium":
                MYCELIUM.add(newBlock);
                break;
            case "podzol":
                PODZOL.add(newBlock);
                break;
            case "flower":
                FLOWERS.add(newBlock);
                break;
            case "dyed_bricks":
                DYED_BRICKS.add(newBlock);
                break;
            case "dyed_wood":
                DYED_WOOD.add(newBlock);
                break;
            case "wood":
                WOOD.add(newBlock);
                break;
            case "red_mushroom":
                RED_MUSHROOM.add(newBlock);
                break;
            case "brown_mushroom":
                BROWN_MUSHROOM.add(newBlock);
                break;
            case "functional":
                FUNCTIONAL.put(copyBlock, newBlock);
                break;
            case "building_blocks", "nostalgia_cobblestone", "nostalgia_mossy_cobblestone", "nostalgia_netherrack":
                BUILDING_BLOCKS.put(copyBlock, newBlock);
                break;
            case "colored_blocks":
                COLORED_BLOCKS.put(copyBlock, newBlock);
                break;
            case "concrete_stairs", "concrete_slab":
                break;
            default:
                ModHelpers.log("%s provided group %s".formatted(blockID, group));
        }
    }
}
