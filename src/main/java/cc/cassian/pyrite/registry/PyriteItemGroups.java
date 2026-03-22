package cc.cassian.pyrite.registry;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
//? if fabric && <26.1 {
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
//?} else if fabric {
/*import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
*///?} else {
/*import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
*///?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;


import java.util.*;
import java.util.function.Supplier;

import static cc.cassian.pyrite.registry.BlockCreator.BLOCKS;
import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.ModLists.VANILLA_DYES;

public class PyriteItemGroups {
    public static final ArrayList<Supplier<Block>> REDSTONE_BLOCKS = new ArrayList<>();
    public static final ArrayList<Supplier<Item>> SIGNS = new ArrayList<>();
    public static final ArrayList<Supplier<Item>> BOATS = new ArrayList<>();
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


    public static void buildContents(
            //? if fabric && >26 {
            /*CreativeModeTab creativeModeTab, FabricCreativeModeTabOutput event
             *///?} else if fabric
            CreativeModeTab creativeModeTab, FabricItemGroupEntries event
            //? if neoforge
            //BuildCreativeModeTabContentsEvent event
    ) {
        if (Pyrite.CONFIG.addToVanillaItemGroups) {
            //? neoforge
            //var key = event.getTabKey();
            //? fabric
            var key = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).orElseThrow();

            if (key.equals(CreativeModeTabs.BUILDING_BLOCKS)) {
                addAfter(Items.IRON_BLOCK, getBlockCollectionList(IRON_BLOCKS), event);
                addAfter(Items.GOLD_BLOCK, getBlockCollectionList(GOLD_BLOCKS), event);
                addAfter(Items.EMERALD_BLOCK, getBlockCollectionList(EMERALD_BLOCKS), event);
                addAfter(Items.LAPIS_BLOCK, getBlockCollectionList(LAPIS_BLOCKS), event);
                addAfter(Items.REDSTONE_BLOCK, getBlockCollectionList(REDSTONE_RESOURCE_BLOCKS), event);
                addAfter(Items.DIAMOND_BLOCK, getBlockCollectionList(DIAMOND_BLOCKS), event);
                addAfter(Items.NETHERITE_BLOCK, getBlockCollectionList(NETHERITE_BLOCKS), event);
                addAfter(Items.QUARTZ_BLOCK, getBlockCollectionList(QUARTZ_BLOCKS), event);
                addAfter(Items.AMETHYST_BLOCK, getBlockCollectionList(AMETHYST_BLOCKS), event);
                addAfter(Items.CUT_COPPER_SLAB, getBlockCollectionList(COPPER_BLOCKS.values()), event);
                addAfter(Items.EXPOSED_CUT_COPPER_SLAB, getBlockCollectionList(EXPOSED_COPPER_BLOCKS.values()), event);
                addAfter(Items.WEATHERED_CUT_COPPER_SLAB, getBlockCollectionList(WEATHERED_COPPER_BLOCKS.values()), event);
                addAfter(Items.OXIDIZED_CUT_COPPER_SLAB, getBlockCollectionList(OXIDIZED_COPPER_BLOCKS.values()), event);
                addAfter(Items.WAXED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_COPPER_BLOCKS.values()), event);
                addAfter(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_EXPOSED_COPPER_BLOCKS.values()), event);
                addAfter(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_WEATHERED_COPPER_BLOCKS.values()), event);
                addAfter(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_OXIDIZED_COPPER_BLOCKS.values()), event);
                addAfter(Items.RED_NETHER_BRICK_WALL, getBlockCollectionList(COLOURED_NETHER_BRICKS), event);
                addAfter(Items.COBBLESTONE_WALL, getBlockCollectionList(COBBLESTONE), event);
                addAfter(Items.COBBLED_DEEPSLATE_WALL, getBlockCollectionList(COBBLED_DEEPSLATE), event);
                addAfter(Items.GRANITE_SLAB, getBlockCollectionList(GRANITE), event);
                addAfter(Items.ANDESITE_SLAB, getBlockCollectionList(ANDESITE), event);
                addAfter(Items.POLISHED_DIORITE_SLAB, getBlockCollectionList(DIORITE), event);
                addAfter(Items.SMOOTH_STONE_SLAB, getBlockCollectionList(SMOOTH_STONE), event);
                addAfter(Items.TUFF_BRICK_SLAB, getBlockCollectionList(TUFF), event);
                addAfter(Items.DEEPSLATE_TILE_WALL, getBlockCollectionList(DEEPSLATE), event);
                addBefore(Items.TUFF, Items.CALCITE, event);
                addAfter(Items.CALCITE, getBlockCollectionList(CALCITE), event);
                addAfter(Blocks.CUT_SANDSTONE_SLAB, getBlockCollectionList(SANDSTONE), event);
                addMapToItemGroup(event, BUILDING_BLOCKS);
                addAfter(Items.CHERRY_BUTTON, getBlockCollectionList(WOOD), event);
			} else if (key.equals(CreativeModeTabs.COLORED_BLOCKS)) {
                addAfter(Blocks.PINK_STAINED_GLASS, getBlockCollectionList(STAINED_GLASS), event);
                addAfter(Blocks.PINK_STAINED_GLASS_PANE, getBlockCollectionList(STAINED_GLASS_PANES), event);
                addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS), event);
                addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS_PANES), event);
                addAfter(Blocks.PINK_CONCRETE, getBlockCollectionList(CONCRETE), event);
                addAfter(Blocks.PINK_CONCRETE_POWDER, getBlockCollectionList(CONCRETE_POWDER), event);
                addAfter(Blocks.PINK_TERRACOTTA, getBlockCollectionList(TERRACOTTA), event);
                addBefore(Blocks.WHITE_CONCRETE, getBlockCollectionList(TERRACOTTA_BRICKS), event);
                addMapToItemGroup(event, COLORED_BLOCKS);
                addAfter(Blocks.PINK_CARPET, getBlockCollectionList(CARPET), event);
                addAfter(Blocks.PINK_SHULKER_BOX, getBlockCollectionList(DYED_BRICKS), event);
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
                    final Block stairs = BLOCKS.get(concrete + "_stairs");
                    final Block slab = BLOCKS.get(concrete + "_slab");
                    if (!namespace.equals(MOD_ID) || stairs.asItem().getDefaultInstance().is(PyriteItemTags.ENABLED))
                        addAfter(ModHelpers.getBlock(Pyrite.of(namespace, concrete)).asItem(), List.of(new ItemStack(stairs), new ItemStack(slab)), event);
                }
			} else if (key.equals(CreativeModeTabs.NATURAL_BLOCKS)) {
                addAfter(Items.WITHER_ROSE, getBlockCollectionList(FLOWERS), event);
                addAfter(Items.DIRT_PATH, getBlockCollectionList(DIRT_PATH), event);
                addAfter(Items.GRASS_BLOCK, getBlockCollectionList(NOSTALGIA_GRASS), event);
                addAfter(Items.GRASS_BLOCK, getBlockCollectionList(GRASS), event);
                addAfter(Items.PODZOL, getBlockCollectionList(PODZOL), event);
                addAfter(Items.MYCELIUM, getBlockCollectionList(MYCELIUM), event);
                addAfter(Items.GRAVEL, getBlockCollectionList(GRAVEL), event);
                addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(BROWN_MUSHROOM), event);
                addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(RED_MUSHROOM), event);
			} else if (key.equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
                addAfter(Items.WARPED_HANGING_SIGN, getItemCollectionList(SIGNS), event);
                addAfter(Items.CRAFTING_TABLE, getBlockCollectionList(CRAFTING_TABLES), event);
                addAfter(Items.TORCH, getBlockCollectionList(TORCH), event);
                addAfter(Items.CRYING_OBSIDIAN, getBlockCollectionList(OBSIDIAN), event);
                addMapToItemGroup(event, FUNCTIONAL);
			} else if (key.equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
                addAfter(Items.CAULDRON, getBlockCollectionList(REDSTONE_BLOCKS), event);
                addAfter(Items.REDSTONE_BLOCK, getBlockCollectionList(REDSTONE_RESOURCE_BLOCKS), event);
                addAfter(Items.LEVER, getBlockCollectionList(TORCH_LEVER), event);
			} else if (key.equals(CreativeModeTabs.INGREDIENTS)) {
                addAfter(Items.PINK_DYE, getItemCollectionList(DYES), event);
			} else if (key.equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
                //? if >1.21.4 {
                /*addAfter(Items.PALE_OAK_CHEST_BOAT, getItemCollectionList(BOATS), event);
                *///?} else {
                addAfter(Items.CHERRY_CHEST_BOAT, getItemCollectionList(BOATS), event);
                //?}
            }
        }
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

    public static void addItemGroup(String id, String icon, LinkedHashMap<String, Block> blocks) {
        //? if fabric && <26.1 {
        CreativeModeTab group = FabricItemGroup.builder()
        //?} else if fabric && >26 {
        /*CreativeModeTab group = FabricCreativeModeTab.builder()
        *///?} else {
        /*CreativeModeTab group = CreativeModeTab.builder()
        *///?}
                .icon(() -> new ItemStack(BLOCKS.get(icon)))
                .title(Component.translatable("itemGroup.pyrite." + id))
                .displayItems((context, entries) -> {
                    for (Block block : blocks.values()) {
                        if (block.asItem().getDefaultInstance().is(PyriteItemTags.ENABLED))
                            entries.accept(block);
                    }
                })
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Pyrite.of(MOD_ID, id), group);
    }

    private static void addAfter(Item anchor, Collection<ItemStack> blockCollectionList,
                                 //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                 FabricItemGroupEntries event
                                 //? if neoforge
                                 //BuildCreativeModeTabContentsEvent event
    ) {
        for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
            addAfter(anchor, itemStack, event);
        }
    }

    private static void addAfter(Item anchor, ItemStack itemStack,
                                 //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                 FabricItemGroupEntries event
                                 //? if neoforge
                                 //BuildCreativeModeTabContentsEvent event
    ) {
        //? if neoforge {
        /*event.insertAfter(anchor.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?} else if fabric && >26 {
        /*event.insertAfter(anchor.getDefaultInstance(), itemStack);
         *///?} else {
        event.addAfter(anchor, itemStack);
        //?}
    }

    private static void addAfter(Block block, Collection<ItemStack> blockCollectionList,
                                 //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                 FabricItemGroupEntries event
                                 //? if neoforge
                                 //BuildCreativeModeTabContentsEvent event
    ) {
        addAfter(block.asItem(), blockCollectionList, event);
    }

    private static void addBefore(Block block, Collection<ItemStack> blockCollectionList,
                                  //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                  FabricItemGroupEntries event
                                  //? if neoforge
                                  //BuildCreativeModeTabContentsEvent event
    ) {
        addBefore(block.asItem(), blockCollectionList, event);
    }

    private static void addBefore(Item anchor, ItemStack itemStack,
                                  //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                  FabricItemGroupEntries event
                                  //? if neoforge
                                  //BuildCreativeModeTabContentsEvent event
    ) {
        //? if fabric && <26.1 {
        event.addBefore(anchor, itemStack);
        //?} else if fabric {
        /*event.insertBefore(anchor, itemStack);
         *///?} else {
        /*event.insertBefore(anchor.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?}
    }

    private static void addBefore(Item anchor, Collection<ItemStack> blockCollectionList,
                                  //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                  FabricItemGroupEntries event
                                  //? if neoforge
                                  //BuildCreativeModeTabContentsEvent event
    ) {
        for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
            addBefore(anchor, itemStack, event);
        }
    }

    private static void addBefore(Item anchor, Item item,
                                  //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
                                  FabricItemGroupEntries event
                                  //? if neoforge
                                  //BuildCreativeModeTabContentsEvent event
    ) {
        //? if fabric && <26.1 {
        event.addBefore(anchor, item);
        //?} else if fabric {
        /*event.insertBefore(anchor, item);
         *///?} else {
        /*event.insertBefore(anchor.getDefaultInstance(), item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
         *///?}
    }

    public static void addMapToItemGroup(
            //? if fabric && >26 {
            /*FabricCreativeModeTabOutput event
             *///?} else if fabric
            FabricItemGroupEntries event
            //? if neoforge
            //BuildCreativeModeTabContentsEvent event
            , LinkedHashMap<Block, Supplier<Block>> map) {
        for (Map.Entry<Block, Supplier<Block>> entry : map.entrySet()) {
            Block anchor = entry.getKey();
            Block value = entry.getValue().get();
            if (value.asItem().getDefaultInstance().is(PyriteItemTags.ENABLED)) {
                if (anchor != null) {
                    addAfter(anchor.asItem(), new ItemStack(value), event);
                } else
                    event.accept(value);
            }
        }
    }

    public static Collection<ItemStack> getBlockCollectionList(Collection<Supplier<Block>> items) {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        for (Supplier<Block> block : items) {
            var stack = block.get().asItem().getDefaultInstance();
            if (!stack.is(PyriteItemTags.HIDDEN_FROM_RECIPE_VIEWERS) && stack.is(PyriteItemTags.ENABLED)) {
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
            if (!stack.is(PyriteItemTags.HIDDEN_FROM_RECIPE_VIEWERS) && stack.is(PyriteItemTags.ENABLED)) {
                stacks.add(stack);
            } else {
//                ModHelpers.log(stack.getName().getString() + " was not added to its item group as it was disabled!");
            }
        }
        return stacks;
    }
}
