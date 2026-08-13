package cc.cassian.pyrite.util;

import cc.cassian.mru.util.ItemLikeEntry;
import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static cc.cassian.pyrite.util.ModHelpers.getBlock;

public class VanillaConstants {
	public static final ItemLikeEntry<Block> WHITE_WOOL = vanillaBlock("white_wool");
	public static final ItemLikeEntry<Block> GREEN_WOOL = vanillaBlock("green_wool");
	public static final ItemLikeEntry<Block> PURPLE_WOOL = vanillaBlock("purple_wool");
	public static final ItemLikeEntry<Block> CYAN_WOOL = vanillaBlock("cyan_wool");
	public static final ItemLikeEntry<Block> ORANGE_WOOL = vanillaBlock("orange_wool");
	public static final ItemLikeEntry<Block> MAGENTA_WOOL = vanillaBlock("magenta_wool");
	public static final ItemLikeEntry<Block> YELLOW_WOOL = vanillaBlock("yellow_wool");

	public static final ItemLikeEntry<Block> WHITE_CONCRETE_POWDER = vanillaBlock("white_concrete_powder");
	public static final ItemLikeEntry<Block> PINK_CONCRETE_POWDER = vanillaBlock("pink_concrete_powder");

	public static final ItemLikeEntry<Block> PINK_CARPET = vanillaBlock("pink_carpet");
	public static final ItemLikeEntry<Block> GREEN_CARPET = vanillaBlock("green_carpet");
	public static final ItemLikeEntry<Block> PURPLE_CARPET = vanillaBlock("purple_carpet");
	public static final ItemLikeEntry<Block> CYAN_CARPET = vanillaBlock("cyan_carpet");
	public static final ItemLikeEntry<Block> ORANGE_CARPET = vanillaBlock("orange_carpet");
	public static final ItemLikeEntry<Block> MAGENTA_CARPET = vanillaBlock("magenta_carpet");
	public static final ItemLikeEntry<Block> YELLOW_CARPET = vanillaBlock("yelllow_carpet");

	public static final ItemLikeEntry<Block> WHITE_CONCRETE = vanillaBlock("white_concrete");
	public static final ItemLikeEntry<Block> GREEN_CONCRETE = vanillaBlock("green_concrete");
	public static final ItemLikeEntry<Block> PURPLE_CONCRETE = vanillaBlock("purple_concrete");
	public static final ItemLikeEntry<Block> LIGHT_BLUE_CONCRETE = vanillaBlock("light_blue_concrete");
	public static final ItemLikeEntry<Block> ORANGE_CONCRETE = vanillaBlock("orange_concrete");
	public static final ItemLikeEntry<Block> MAGENTA_CONCRETE = vanillaBlock("magenta_concrete");
	public static final ItemLikeEntry<Block> YELLOW_CONCRETE = vanillaBlock("yellow_concrete");
	public static final ItemLikeEntry<Block> CYAN_CONCRETE = vanillaBlock("cyan_concrete");
	public static final ItemLikeEntry<Block> PINK_CONCRETE = vanillaBlock("pink_concrete");

	public static final ItemLikeEntry<Block> CUT_COPPER_SLAB = vanillaBlock("cut_copper_slab");
	public static final ItemLikeEntry<Block> EXPOSED_CUT_COPPER_SLAB = vanillaBlock("exposed_cut_copper_slab");
	public static final ItemLikeEntry<Block> WEATHERED_CUT_COPPER_SLAB = vanillaBlock("weathered_cut_copper_slab");
	public static final ItemLikeEntry<Block> OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("oxidized_cut_copper_slab");
	public static final ItemLikeEntry<Block> WAXED_CUT_COPPER_SLAB = vanillaBlock("waxed_cut_copper_slab");
	public static final ItemLikeEntry<Block> WAXED_EXPOSED_CUT_COPPER_SLAB = vanillaBlock("waxed_exposed_cut_copper_slab");
	public static final ItemLikeEntry<Block> WAXED_WEATHERED_CUT_COPPER_SLAB = vanillaBlock("waxed_weathered_cut_copper_slab");
	public static final ItemLikeEntry<Block> WAXED_OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("waxed_oxidized_cut_copper_slab");

	public static final ItemLikeEntry<Block> PINK_SHULKER_BOX = vanillaBlock("pink_shulker_box");

	public static final ItemLikeEntry<Block> PINK_STAINED_GLASS = vanillaBlock("pink_stained_glass");
	public static final ItemLikeEntry<Block> PINK_STAINED_GLASS_PANE = vanillaBlock("pink_stained_glass_pane");

	public static final ItemLikeEntry<Block> PINK_TERRACOTTA = vanillaBlock("pink_terracotta");

	private static final ItemLikeEntry<Block> IRON_BLOCK = Pyrite.entryOf(Blocks.IRON_BLOCK);
	private static final ItemLikeEntry<Block> GOLD_BLOCk = Pyrite.entryOf(Blocks.GOLD_BLOCK);
	private static final ItemLikeEntry<Block> EMERALD_BLOCK = Pyrite.entryOf(Blocks.EMERALD_BLOCK);
	private static final ItemLikeEntry<Block> LAPIS_BLOCK = Pyrite.entryOf(Blocks.LAPIS_BLOCK);
	private static final ItemLikeEntry<Block> REDSTONE_BLOCK = Pyrite.entryOf(Blocks.REDSTONE_BLOCK);
	private static final ItemLikeEntry<Block> DIAMOND_BLOCK = Pyrite.entryOf(Blocks.DIAMOND_BLOCK);
	private static final ItemLikeEntry<Block> NETHERITE_BLOCK = Pyrite.entryOf(Blocks.NETHERITE_BLOCK);
	private static final ItemLikeEntry<Block> QUARTZ_BLOCK = Pyrite.entryOf(Blocks.QUARTZ_BLOCK);
	private static final ItemLikeEntry<Block> AMETHYST_BLOCK = Pyrite.entryOf(Blocks.AMETHYST_BLOCK);
	private static final ItemLikeEntry<Block> COPPER_BLOCK = vanillaBlock("copper_block");
	private static final ItemLikeEntry<Block> EXPOSED_COPPER_BLOCK = vanillaBlock("exposed_copper");
	private static final ItemLikeEntry<Block> WEATHERED_COPPER_BLOCK = vanillaBlock("weathered_copper");
	private static final ItemLikeEntry<Block> OXIDIZED_COPPER_BLOCK = vanillaBlock("oxidized_copper");

	public static final ItemLikeEntry<Item> PINK_DYE = vanillaItem("pink_dye");

	public static final ItemLikeEntry<?>[] RESOURCE_BLOCKS = new ItemLikeEntry[]{
			IRON_BLOCK,
			GOLD_BLOCk,
			EMERALD_BLOCK,
			LAPIS_BLOCK,
			REDSTONE_BLOCK,
			DIAMOND_BLOCK,
			NETHERITE_BLOCK,
			QUARTZ_BLOCK,
			AMETHYST_BLOCK,
			COPPER_BLOCK,
			EXPOSED_COPPER_BLOCK,
			WEATHERED_COPPER_BLOCK,
			OXIDIZED_COPPER_BLOCK

	};

	private static ItemLikeEntry<Block> vanillaBlock(String name) {
		Identifier id = Identifier.withDefaultNamespace(name);
		return new ItemLikeEntry<>(id, BuiltInRegistries.BLOCK.getValue(id));
	}

	private static ItemLikeEntry<Item> vanillaItem(String name) {
		Identifier key = Identifier.withDefaultNamespace(name);
		return new ItemLikeEntry<>(key, BuiltInRegistries.ITEM.getValue(key));
	}
}
