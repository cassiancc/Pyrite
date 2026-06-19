package cc.cassian.pyrite.registry;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.entries.PyriteEntry;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.pyrite.util.ModLists;
import cc.cassian.pyrite.util.VanillaConstants;
//? if fabric && <26.1 {
/*import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
*///?} else if fabric {

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
//?} else {
/*import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
*///?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;


import java.util.*;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.util.ModLists.VANILLA_DYES;

public class PyriteItemGroups {
    public static final ArrayList<BlockEntry<Block>> REDSTONE_BLOCKS = new ArrayList<>();
    public static final ArrayList<ItemEntry<Item>> SIGNS = new ArrayList<>();
    public static final ArrayList<ItemEntry<Item>> BOATS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> CRAFTING_TABLES = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> FLOWERS = new ArrayList<>();
    public static final LinkedHashMap<String, BlockEntry<FlowerPotBlock>> POTTED_FLOWERS = new LinkedHashMap<>();
    public static final ArrayList<ItemEntry<Item>> DYES = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> IRON_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> GOLD_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> EMERALD_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> LAPIS_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> REDSTONE_RESOURCE_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DIAMOND_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> NETHERITE_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> QUARTZ_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> AMETHYST_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> EXPOSED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WEATHERED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> OXIDIZED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WAXED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WAXED_EXPOSED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WAXED_WEATHERED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WAXED_OXIDIZED_COPPER_BLOCKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> COLOURED_NETHER_BRICKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> COBBLESTONE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> SMOOTH_STONE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> ANDESITE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> GRANITE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DIORITE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> CALCITE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> TUFF = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DEEPSLATE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> COBBLED_DEEPSLATE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> SANDSTONE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> RED_SANDSTONE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> STAINED_GLASS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> STAINED_GLASS_PANES = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> FRAMED_GLASS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> FRAMED_GLASS_PANES = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> CONCRETE = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> CONCRETE_POWDER = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WOOL = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> CARPET = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> TERRACOTTA = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> TERRACOTTA_BRICKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> TORCH = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> TORCH_LEVER = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> GRASS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> NOSTALGIA_GRASS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> GRAVEL = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> PODZOL = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> MYCELIUM = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DIRT_PATH = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> LAMPS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> OBSIDIAN = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DYED_BRICKS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> DYED_WOOD = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WOOD = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> RED_MUSHROOM = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> BROWN_MUSHROOM = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WOOL_STAIRS = new ArrayList<>();
    public static final ArrayList<BlockEntry<Block>> WOOL_SLAB = new ArrayList<>();
    public static final LinkedHashMap<Block, BlockEntry<Block>> FUNCTIONAL = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, BlockEntry<Block>> BUILDING_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, BlockEntry<Block>> COLORED_BLOCKS = new LinkedHashMap<>();
    public static final LinkedHashMap<Block, BlockEntry<Block>> NATURAL = new LinkedHashMap<>();
    private static Block get(String id) {
        return BuiltInRegistries.BLOCK.getValue(Pyrite.of(id));
    };


    public static void buildContents(
            //? if fabric && >26
            CreativeModeTab creativeModeTab, FabricCreativeModeTabOutput event
            //? if neoforge
            //BuildCreativeModeTabContentsEvent event
    ) {
        if (Pyrite.CONFIG.addToVanillaItemGroups) {
            //? neoforge
            //var key = event.getTabKey();
            //? fabric
            var key = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).orElseThrow();

            if (key.equals(CreativeModeTabs.BUILDING_BLOCKS)) {
                addAfter(Items.IRON_BLOCK, IRON_BLOCKS, event);
                addAfter(Items.GOLD_BLOCK, GOLD_BLOCKS, event);
                addAfter(Items.EMERALD_BLOCK, (EMERALD_BLOCKS), event);
                addAfter(Items.LAPIS_BLOCK, (LAPIS_BLOCKS), event);
                addAfter(Items.REDSTONE_BLOCK, (REDSTONE_RESOURCE_BLOCKS), event);
                addAfter(Items.DIAMOND_BLOCK, (DIAMOND_BLOCKS), event);
                addAfter(Items.NETHERITE_BLOCK, (NETHERITE_BLOCKS), event);
                addAfter(Items.QUARTZ_BLOCK, (QUARTZ_BLOCKS), event);
                addAfter(Items.AMETHYST_BLOCK, (AMETHYST_BLOCKS), event);
                addAfter(VanillaConstants.CUT_COPPER_SLAB, (COPPER_BLOCKS), event);
                addAfter(VanillaConstants.EXPOSED_CUT_COPPER_SLAB, (EXPOSED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.WEATHERED_CUT_COPPER_SLAB, (WEATHERED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.OXIDIZED_CUT_COPPER_SLAB, (OXIDIZED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.WAXED_CUT_COPPER_SLAB, (WAXED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.WAXED_EXPOSED_CUT_COPPER_SLAB, (WAXED_EXPOSED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.WAXED_WEATHERED_CUT_COPPER_SLAB, (WAXED_WEATHERED_COPPER_BLOCKS), event);
                addAfter(VanillaConstants.WAXED_OXIDIZED_CUT_COPPER_SLAB, (WAXED_OXIDIZED_COPPER_BLOCKS), event);
                addAfter(Items.RED_NETHER_BRICK_WALL, (COLOURED_NETHER_BRICKS), event);
                addAfter(Items.COBBLESTONE_WALL, (COBBLESTONE), event);
                addAfter(Items.COBBLED_DEEPSLATE_WALL, (COBBLED_DEEPSLATE), event);
                addAfter(Items.GRANITE_SLAB, (GRANITE), event);
                addAfter(Items.ANDESITE_SLAB, (ANDESITE), event);
                addAfter(Items.POLISHED_DIORITE_SLAB, (DIORITE), event);
                addAfter(Items.SMOOTH_STONE_SLAB, (SMOOTH_STONE), event);
                addAfter(Items.TUFF_BRICK_SLAB, (TUFF), event);
                addAfter(Items.DEEPSLATE_TILE_WALL, (DEEPSLATE), event);
                addBefore(Items.TUFF, Items.CALCITE, event);
                addAfter(Items.CALCITE, (CALCITE), event);
                addAfter(Blocks.CUT_SANDSTONE_SLAB, getBlockCollectionList(SANDSTONE), event);
                addMapToItemGroup(event, BUILDING_BLOCKS);
                addAfter(Items.CHERRY_BUTTON, (WOOD), event);
			} else if (key.equals(CreativeModeTabs.COLORED_BLOCKS)) {
                addAfter(VanillaConstants.PINK_STAINED_GLASS, getBlockCollectionList(STAINED_GLASS), event);
                addAfter(VanillaConstants.PINK_STAINED_GLASS_PANE, getBlockCollectionList(STAINED_GLASS_PANES), event);
                addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS), event);
                addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS_PANES), event);
                addAfter(VanillaConstants.PINK_CONCRETE, getBlockCollectionList(CONCRETE), event);
                addAfter(VanillaConstants.PINK_CONCRETE_POWDER, getBlockCollectionList(CONCRETE_POWDER), event);
                addAfter(VanillaConstants.PINK_TERRACOTTA, getBlockCollectionList(TERRACOTTA), event);
                addBefore(VanillaConstants.WHITE_CONCRETE, getBlockCollectionList(TERRACOTTA_BRICKS), event);
                addMapToItemGroup(event, COLORED_BLOCKS);
                //fixme
                //? fabric {
                Collection<ItemStack> blocksAfterCarpet = getBlockCollectionList(CARPET);
                blocksAfterCarpet.addAll(getBlockCollectionList(WOOL_SLAB));
                blocksAfterCarpet.addAll(getBlockCollectionList(WOOL_STAIRS));
                addAfter(VanillaConstants.PINK_CARPET, blocksAfterCarpet, event);
                //?}
                addAfter(VanillaConstants.PINK_SHULKER_BOX, getBlockCollectionList(DYED_BRICKS), event);
                event.acceptAll(getBlockCollectionList(DYED_WOOD));
                addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(LAMPS), event);

                // Add Pyrite Concrete to vanilla item group.
                for (int dyeIndex = 0; dyeIndex < ModLists.DYES.length; dyeIndex++) {
                    String dye = ModLists.DYES[dyeIndex];
                    String namespace;
                    if (!Arrays.asList(VANILLA_DYES).contains(dye))
                        namespace = MOD_ID;
                    else {
                        namespace = "minecraft";
                    }
                    final var concrete = dye+"_concrete";
                    final Block stairs = get(concrete + "_stairs");
                    final Block slab = get(concrete + "_slab");
                    if (!namespace.equals(MOD_ID) || ModHelpers.enabled(stairs.asItem().getDefaultInstance()))
                        addAfter(ModHelpers.getBlock(Pyrite.of(namespace, concrete)).asItem(), List.of(new ItemStack(stairs), new ItemStack(slab)), event);
                }
			} else if (key.equals(CreativeModeTabs.NATURAL_BLOCKS)) {
                addAfter(Items.WITHER_ROSE, FLOWERS, event);
                addAfter(Items.DIRT_PATH, DIRT_PATH, event);
                addAfter(Items.GRASS_BLOCK, NOSTALGIA_GRASS, event);
                addAfter(Items.GRASS_BLOCK, GRASS, event);
                addAfter(Items.PODZOL, PODZOL, event);
                addAfter(Items.MYCELIUM, MYCELIUM, event);
                addAfter(Items.GRAVEL, GRAVEL, event);
                addAfter(Items.MUSHROOM_STEM, BROWN_MUSHROOM, event);
                addAfter(Items.MUSHROOM_STEM, RED_MUSHROOM, event);
			} else if (key.equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
                addAfter(Items.WARPED_HANGING_SIGN, SIGNS, event);
                addAfter(Items.CRAFTING_TABLE, CRAFTING_TABLES, event);
                addAfter(Items.TORCH, (TORCH), event);
                addAfter(Items.CRYING_OBSIDIAN, (OBSIDIAN), event);
                addMapToItemGroup(event, FUNCTIONAL);
			} else if (key.equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
                addAfter(Items.CAULDRON, (REDSTONE_BLOCKS), event);
                addAfter(Items.REDSTONE_BLOCK, (REDSTONE_RESOURCE_BLOCKS), event);
                addAfter(Items.LEVER, (TORCH_LEVER), event);
			} else if (key.equals(CreativeModeTabs.INGREDIENTS)) {
                addAfter(VanillaConstants.PINK_DYE, getItemCollectionList(DYES), event);
			} else if (key.equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
                addAfter(Items.PALE_OAK_CHEST_BOAT, getItemCollectionList(BOATS), event);
            }
        }
    }

    public static <T extends Block> void match(BlockEntry<T> entry, Block copyBlock, ArrayList<BlockEntry<Block>> group) {
        group.add((BlockEntry<Block>) entry);
    }

    public static <T extends Block> void match(BlockEntry<T> entry, Block copyBlock, String group) {
        var newBlock = (BlockEntry<Block>) entry;
        if (newBlock.getPath().equals("glowstone_lamp"))
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
                COPPER_BLOCKS.add(newBlock);
                break;
            case "exposed_copper":
                EXPOSED_COPPER_BLOCKS.add(newBlock);
                break;
            case "weathered_copper":
                WEATHERED_COPPER_BLOCKS.add(newBlock);
                break;
            case "oxidized_copper":
                OXIDIZED_COPPER_BLOCKS.add(newBlock);
                break;
            case "waxed_copper":
                WAXED_COPPER_BLOCKS.add(newBlock);
                break;
            case "waxed_exposed_copper":
                WAXED_EXPOSED_COPPER_BLOCKS.add(newBlock);
                break;
            case "waxed_weathered_copper":
                WAXED_WEATHERED_COPPER_BLOCKS.add(newBlock);
                break;
            case "waxed_oxidized_copper":
                WAXED_OXIDIZED_COPPER_BLOCKS.add(newBlock);
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
            case "wool_stairs":
                WOOL_STAIRS.add(newBlock);
            case "wool_slab":
                WOOL_SLAB.add(newBlock);
            case "concrete_stairs", "concrete_slab":
                break;
            default:
                ModHelpers.log("%s provided group %s".formatted(newBlock.getPath(), group));
        }
    }

    public static void addItemGroup(String id, String icon, ArrayList<BlockEntry<Block>> blocks) {
        //~ if neoforge 'FabricCreativeModeTab' -> 'CreativeModeTab' {
        var group = FabricCreativeModeTab.builder()
        //~}
                .icon(() -> new ItemStack(BuiltInRegistries.ITEM.getValue(Pyrite.of(icon))))
                .title(Component.translatable("itemGroup.pyrite." + id))
                .displayItems((context, entries) -> {
                    for (BlockEntry<?> block : blocks) {
                        if (ModHelpers.enabled(block))
                            entries.accept(block.get());
                    }
                })
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Pyrite.of(MOD_ID, id), group);
    }

    //~ if neoforge 'FabricCreativeModeTabOutput' -> 'BuildCreativeModeTabContentsEvent' {
    private static void addAfter(ItemLike anchor, ArrayList<? extends PyriteEntry> blockCollectionList, FabricCreativeModeTabOutput event) {
        addAfter(anchor, getBlockCollectionList(blockCollectionList), event);
    }

    private static void addAfter(ItemLike anchor, Collection<ItemStack> blockCollectionList, FabricCreativeModeTabOutput event) {
        for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
            addAfter(anchor, itemStack, event);
        }
    }

    private static void addAfter(ItemLike anchor, ItemStack itemStack, FabricCreativeModeTabOutput event) {
        //? if neoforge {
        /*event.insertAfter(anchor.asItem().getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?} else {
        event.insertAfter(anchor.asItem().getDefaultInstance(), itemStack);
         //?}
    }

    private static void addBefore(ItemLike block, Collection<ItemStack> blockCollectionList, FabricCreativeModeTabOutput event) {
        addBefore(block.asItem(), blockCollectionList, event);
    }

    private static void addBefore(ItemLike anchor, ItemStack itemStack, FabricCreativeModeTabOutput event) {
        //? if fabric {
        event.insertBefore(anchor, itemStack);
         //?} else {
        /*event.insertBefore(anchor.asItem().getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?}
    }

    private static void addBefore(Item anchor, Collection<ItemStack> blockCollectionList, FabricCreativeModeTabOutput event) {
        for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
            addBefore(anchor, itemStack, event);
        }
    }

    private static void addBefore(ItemLike anchor, ItemLike item, FabricCreativeModeTabOutput event) {
        //? if fabric {
        event.insertBefore(anchor, item);
         //?} else {
        /*event.insertBefore(anchor.asItem().getDefaultInstance(), item.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?}
    }

    public static void addMapToItemGroup(FabricCreativeModeTabOutput event, LinkedHashMap<Block, BlockEntry<Block>> map) {
        for (Map.Entry<Block, BlockEntry<Block>> entry : map.entrySet()) {
            Block anchor = entry.getKey();
            Block value = entry.getValue().get();
            if (ModHelpers.enabled(value.asItem().getDefaultInstance())) {
                if (anchor != null) {
                    addAfter(anchor.asItem(), new ItemStack(value), event);
                } else
                    event.accept(value);
            }
        }
    }
    //~}

    public static Collection<ItemStack> getBlockCollectionList(Collection<? extends PyriteEntry> items) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (PyriteEntry block : items) {
            var stack = block.asItem().getDefaultInstance();
            if (!stack.is(PyriteItemTags.HIDDEN_FROM_RECIPE_VIEWERS) && ModHelpers.enabled(stack)) {
                stacks.add(stack);
            }
        }
        return stacks;
    }

    public static Collection<ItemStack> getItemCollectionList(ArrayList<ItemEntry<Item>> items) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (ItemEntry<Item> item : items) {
            var stack = item.get().getDefaultInstance();
            if (!stack.is(PyriteItemTags.HIDDEN_FROM_RECIPE_VIEWERS) && ModHelpers.enabled(stack)) {
                stacks.add(stack);
            }
        }
        return stacks;
    }
}
