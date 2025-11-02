package cc.cassian.pyrite.registry.neoforge;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.BlockCreator;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.*;
import java.util.function.Supplier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.ModLists.VANILLA_DYES;
import static cc.cassian.pyrite.registry.PyriteItemGroups.*;
import static cc.cassian.pyrite.registry.PyriteItemGroups.DYES;

public class PyriteItemGroupsImpl {
	public static void addMapToItemGroup(BuildCreativeModeTabContentsEvent group, LinkedHashMap<Block, Supplier<Block>> map) {
		for (Map.Entry<Block, Supplier<Block>> entry : map.entrySet()) {
			Block anchor = entry.getKey();
			Block value = entry.getValue().get();
			if (value.asItem().getDefaultInstance().is(PyriteTags.ENABLED)) {
				if (anchor != null)
					group.insertAfter(anchor.asItem().getDefaultInstance(), value.asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				else
					group.accept(value);
			}
		}
	}

	private static void addAfter(Item anchor, Collection<ItemStack> blockCollectionList, BuildCreativeModeTabContentsEvent event) {
		for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
			event.insertAfter(anchor.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}
	}

	private static void addAfter(Block block, Collection<ItemStack> blockCollectionList, BuildCreativeModeTabContentsEvent event) {
		addAfter(block.asItem(), blockCollectionList, event);
	}

	private static void addBefore(Block block, Collection<ItemStack> blockCollectionList, BuildCreativeModeTabContentsEvent event) {
		addBefore(block.asItem(), blockCollectionList, event);
	}

	private static void addBefore(Item anchor, Collection<ItemStack> blockCollectionList, BuildCreativeModeTabContentsEvent event) {
		for (ItemStack itemStack : blockCollectionList.stream().toList().reversed()) {
			event.insertAfter(anchor.getDefaultInstance(), itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
		}
	}

	private static void addBefore(Item anchor, Item item, BuildCreativeModeTabContentsEvent event) {
		event.insertBefore(anchor.getDefaultInstance(), item.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}

	@SubscribeEvent
	public static void buildContents(BuildCreativeModeTabContentsEvent event) {
		if (Pyrite.CONFIG.addToVanillaItemGroups) {
			if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
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
			}

			else if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS)) {
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

				// TODO Add Pyrite Concrete to vanilla item group.
//			for (int dyeIndex = 0; dyeIndex < ModLists.DYES.length; dyeIndex++) {
//				String dye = ModLists.DYES[dyeIndex];
//				String namespace;
//				if (!Arrays.asList(VANILLA_DYES).contains(dye))
//					namespace = MOD_ID;
//				else {
//					namespace = "minecraft";
//				}
//				final var concrete = dye+"_concrete";
//				final var stairs = Registries.BLOCK.get(Identifier.of(namespace, concrete + "_stairs"));
//				final var slab = Registries.BLOCK.get(Identifier.of(namespace,concrete + "_slab"));
//				if (!namespace.equals(MOD_ID) || stairs.asItem().getDefaultStack().isIn(PyriteTags.ENABLED))
//					addAfter(Registries.BLOCK.get(Identifier.of(namespace, concrete)).asItem(), List.of(stairs.asItem().getDefaultStack(), slab.asItem().getDefaultStack()), event);
//			}
			}

			else if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
				addAfter(Items.WITHER_ROSE, getBlockCollectionList(FLOWERS), event);
				addAfter(Items.DIRT_PATH, getBlockCollectionList(DIRT_PATH), event);
				addAfter(Items.GRASS_BLOCK, getBlockCollectionList(NOSTALGIA_GRASS), event);
				addAfter(Items.GRASS_BLOCK, getBlockCollectionList(GRASS), event);
				addAfter(Items.PODZOL, getBlockCollectionList(PODZOL), event);
				addAfter(Items.MYCELIUM, getBlockCollectionList(MYCELIUM), event);
				addAfter(Items.GRAVEL, getBlockCollectionList(GRAVEL), event);
				addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(BROWN_MUSHROOM), event);
				addAfter(Items.MUSHROOM_STEM, getBlockCollectionList(RED_MUSHROOM), event);
			}

			else if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
				addAfter(Items.WARPED_HANGING_SIGN, getItemCollectionList(SIGNS), event);
				addAfter(Items.CRAFTING_TABLE, getBlockCollectionList(CRAFTING_TABLES), event);
				addAfter(Items.TORCH, getBlockCollectionList(TORCH), event);
				addAfter(Items.CRYING_OBSIDIAN, getBlockCollectionList(OBSIDIAN), event);
				addMapToItemGroup(event, FUNCTIONAL);
			}

			else if (event.getTabKey().equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
				addAfter(Items.CAULDRON, getBlockCollectionList(REDSTONE_BLOCKS), event);
				addAfter(Items.REDSTONE_BLOCK, getBlockCollectionList(REDSTONE_RESOURCE_BLOCKS), event);
				addAfter(Items.LEVER, getBlockCollectionList(TORCH_LEVER), event);
			}

			else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS))
				addAfter(Items.PINK_DYE, getItemCollectionList(DYES), event);
		}
	}
}

*///?}