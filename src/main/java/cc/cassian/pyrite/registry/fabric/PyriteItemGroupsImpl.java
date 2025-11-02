package cc.cassian.pyrite.registry.fabric;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.config.ModConfig;
import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.functions.ModLists;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.*;
import java.util.function.Supplier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.ModLists.VANILLA_DYES;
import static cc.cassian.pyrite.registry.fabric.BlockCreatorImpl.BLOCKS;
import static cc.cassian.pyrite.registry.PyriteItemGroups.*;

public class PyriteItemGroupsImpl {

    public static void addMapToItemGroup(FabricItemGroupEntries group, LinkedHashMap<Block, Supplier<Block>> map) {
        for (Map.Entry<Block, Supplier<Block>> entry : map.entrySet()) {
            Block anchor = entry.getKey();
            Block value = entry.getValue().get();
            if (value.asItem().getDefaultInstance().is(PyriteTags.ENABLED)) {
                if (anchor != null)
                    group.addAfter(anchor, value);
                else
                    group.accept(value);
            }
        }
    }

    public static void addItemGroup(String id, String icon, LinkedHashMap<String, Block> blocks) {
        CreativeModeTab group = FabricItemGroup.builder()
                .icon(() -> new ItemStack(BLOCKS.get(icon)))
                .title(Component.translatable("itemGroup.pyrite." + id))
                .displayItems((context, entries) -> {
                    for (Block block : blocks.values()) {
                        if (block.asItem().getDefaultInstance().is(PyriteTags.ENABLED))
                            entries.accept(block);
                    }
                })
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Pyrite.of(MOD_ID, id), group);
    }

    @SuppressWarnings("unused")
    public static void modifyEntries() {
        if (Pyrite.CONFIG.addToVanillaItemGroups) {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register((itemGroup) -> {
                itemGroup.addAfter(Items.IRON_BLOCK, getBlockCollectionList(IRON_BLOCKS));
                itemGroup.addAfter(Items.GOLD_BLOCK, getBlockCollectionList(GOLD_BLOCKS));
                itemGroup.addAfter(Items.EMERALD_BLOCK, getBlockCollectionList(EMERALD_BLOCKS));
                itemGroup.addAfter(Items.LAPIS_BLOCK, getBlockCollectionList(LAPIS_BLOCKS));
                itemGroup.addAfter(Items.REDSTONE_BLOCK, getBlockCollectionList(REDSTONE_RESOURCE_BLOCKS));
                itemGroup.addAfter(Items.DIAMOND_BLOCK, getBlockCollectionList(DIAMOND_BLOCKS));
                itemGroup.addAfter(Items.NETHERITE_BLOCK, getBlockCollectionList(NETHERITE_BLOCKS));
                itemGroup.addAfter(Items.QUARTZ_BLOCK, getBlockCollectionList(QUARTZ_BLOCKS));
                itemGroup.addAfter(Items.AMETHYST_BLOCK, getBlockCollectionList(AMETHYST_BLOCKS));
                itemGroup.addAfter(Items.CUT_COPPER_SLAB, getBlockCollectionList(COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.EXPOSED_CUT_COPPER_SLAB, getBlockCollectionList(EXPOSED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.WEATHERED_CUT_COPPER_SLAB, getBlockCollectionList(WEATHERED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.OXIDIZED_CUT_COPPER_SLAB, getBlockCollectionList(OXIDIZED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.WAXED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_EXPOSED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_WEATHERED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, getBlockCollectionList(WAXED_OXIDIZED_COPPER_BLOCKS.values()));
                itemGroup.addAfter(Items.RED_NETHER_BRICK_WALL, getBlockCollectionList(COLOURED_NETHER_BRICKS));
                itemGroup.addAfter(Items.COBBLESTONE_WALL, getBlockCollectionList(COBBLESTONE));
                itemGroup.addAfter(Items.COBBLED_DEEPSLATE_WALL, getBlockCollectionList(COBBLED_DEEPSLATE));
                itemGroup.addAfter(Items.GRANITE_SLAB, getBlockCollectionList(GRANITE));
                itemGroup.addAfter(Items.ANDESITE_SLAB, getBlockCollectionList(ANDESITE));
                itemGroup.addAfter(Items.POLISHED_DIORITE_SLAB, getBlockCollectionList(DIORITE));
                itemGroup.addAfter(Items.SMOOTH_STONE_SLAB, getBlockCollectionList(SMOOTH_STONE));
                itemGroup.addAfter(Items.TUFF_BRICK_SLAB, getBlockCollectionList(TUFF));
                itemGroup.addAfter(Items.DEEPSLATE_TILE_WALL, getBlockCollectionList(DEEPSLATE));
                itemGroup.addBefore(Items.TUFF, Items.CALCITE);
                itemGroup.addAfter(Items.CALCITE, getBlockCollectionList(CALCITE));
                itemGroup.addAfter(Blocks.CUT_SANDSTONE_SLAB, getBlockCollectionList(SANDSTONE));
                itemGroup.addAfter(Items.CUT_RED_SANDSTONE_SLAB, getBlockCollectionList(RED_SANDSTONE));
                addMapToItemGroup(itemGroup, BUILDING_BLOCKS);
                itemGroup.addAfter(Items.CHERRY_BUTTON, getBlockCollectionList(WOOD));
            });

            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register((itemGroup) -> {
                itemGroup.addAfter(Blocks.PINK_STAINED_GLASS, getBlockCollectionList(STAINED_GLASS));
                itemGroup.addAfter(Blocks.PINK_STAINED_GLASS_PANE, getBlockCollectionList(STAINED_GLASS_PANES));
                itemGroup.addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS));
                itemGroup.addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(FRAMED_GLASS_PANES));
                itemGroup.addAfter(Blocks.PINK_CONCRETE, getBlockCollectionList(CONCRETE));
                itemGroup.addAfter(Blocks.PINK_CONCRETE_POWDER, getBlockCollectionList(CONCRETE_POWDER));
                itemGroup.addAfter(Blocks.PINK_TERRACOTTA, getBlockCollectionList(TERRACOTTA));
                itemGroup.addBefore(Blocks.WHITE_CONCRETE, getBlockCollectionList(TERRACOTTA_BRICKS));
                addMapToItemGroup(itemGroup, COLORED_BLOCKS);
                itemGroup.addAfter(Blocks.PINK_CARPET, getBlockCollectionList(CARPET));
                itemGroup.addAfter(Blocks.PINK_SHULKER_BOX, getBlockCollectionList(DYED_BRICKS));
                itemGroup.acceptAll(getBlockCollectionList(DYED_WOOD));
                itemGroup.addBefore(Blocks.SHULKER_BOX, getBlockCollectionList(LAMPS));
            });

            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register((itemGroup) -> {
                itemGroup.addAfter(Items.WITHER_ROSE, getBlockCollectionList(FLOWERS));
                itemGroup.addAfter(Items.DIRT_PATH, getBlockCollectionList(DIRT_PATH));
                itemGroup.addAfter(Items.GRASS_BLOCK, getBlockCollectionList(NOSTALGIA_GRASS));
                itemGroup.addAfter(Items.GRASS_BLOCK, getBlockCollectionList(GRASS));
                itemGroup.addAfter(Items.PODZOL, getBlockCollectionList(PODZOL));
                itemGroup.addAfter(Items.MYCELIUM, getBlockCollectionList(MYCELIUM));
                itemGroup.addAfter(Items.GRAVEL, getBlockCollectionList(GRAVEL));
                itemGroup.addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(BROWN_MUSHROOM));
                itemGroup.addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(RED_MUSHROOM));
            });

            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((itemGroup) -> {
                itemGroup.addAfter(Items.WARPED_HANGING_SIGN, getItemCollectionList(SIGNS));
                itemGroup.addAfter(Items.CRAFTING_TABLE, getBlockCollectionList(CRAFTING_TABLES));
                itemGroup.addAfter(Items.TORCH, getBlockCollectionList(TORCH));
                itemGroup.addAfter(Items.CRYING_OBSIDIAN, getBlockCollectionList(OBSIDIAN));
                addMapToItemGroup(itemGroup, FUNCTIONAL);
            });

            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register((itemGroup) -> {
                itemGroup.addAfter(Items.CAULDRON, getBlockCollectionList(REDSTONE_BLOCKS));
                itemGroup.addAfter(Items.REDSTONE_BLOCK, getBlockCollectionList(REDSTONE_RESOURCE_BLOCKS));
                itemGroup.addAfter(Items.LEVER, getBlockCollectionList(TORCH_LEVER));

            });

            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register((itemGroup) ->
                    itemGroup.addAfter(Items.PINK_DYE, getItemCollectionList(DYES)));

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
                final var stairs = BLOCKS.get(concrete + "_stairs");
                final var slab = BLOCKS.get(concrete + "_slab");
                ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COLORED_BLOCKS).register((itemGroup) -> {
                    if (!namespace.equals(MOD_ID) || stairs.asItem().getDefaultInstance().is(PyriteTags.ENABLED))
                        itemGroup.addAfter(BuiltInRegistries.BLOCK.getValue(Pyrite.of(namespace, concrete)), stairs, slab);
                });
            }
        }

    }
}

//?}