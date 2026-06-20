package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static cc.cassian.pyrite.util.ModHelpers.getBlock;

public class VanillaConstants {
	public static final BlockEntry<Block> WHITE_WOOL = vanillaBlock("white_wool");
	public static final BlockEntry<Block> GREEN_WOOL = vanillaBlock("green_wool");
	public static final BlockEntry<Block> PURPLE_WOOL = vanillaBlock("purple_wool");
	public static final BlockEntry<Block> CYAN_WOOL = vanillaBlock("cyan_wool");
	public static final BlockEntry<Block> ORANGE_WOOL = vanillaBlock("orange_wool");
	public static final BlockEntry<Block> MAGENTA_WOOL = vanillaBlock("magenta_wool");
	public static final BlockEntry<Block> YELLOW_WOOL = vanillaBlock("yellow_wool");

	public static final BlockEntry<Block> WHITE_CONCRETE_POWDER = vanillaBlock("white_concrete_powder");
	public static final BlockEntry<Block> PINK_CONCRETE_POWDER = vanillaBlock("pink_concrete_powder");

	public static final BlockEntry<Block> PINK_CARPET = vanillaBlock("pink_carpet");
	public static final BlockEntry<Block> GREEN_CARPET = vanillaBlock("green_carpet");
	public static final BlockEntry<Block> PURPLE_CARPET = vanillaBlock("purple_carpet");
	public static final BlockEntry<Block> CYAN_CARPET = vanillaBlock("cyan_carpet");
	public static final BlockEntry<Block> ORANGE_CARPET = vanillaBlock("orange_carpet");
	public static final BlockEntry<Block> MAGENTA_CARPET = vanillaBlock("magenta_carpet");
	public static final BlockEntry<Block> YELLOW_CARPET = vanillaBlock("yelllow_carpet");

	public static final BlockEntry<Block> WHITE_CONCRETE = vanillaBlock("white_concrete");
	public static final BlockEntry<Block> GREEN_CONCRETE = vanillaBlock("green_concrete");
	public static final BlockEntry<Block> PURPLE_CONCRETE = vanillaBlock("purple_concrete");
	public static final BlockEntry<Block> LIGHT_BLUE_CONCRETE = vanillaBlock("light_blue_concrete");
	public static final BlockEntry<Block> ORANGE_CONCRETE = vanillaBlock("orange_concrete");
	public static final BlockEntry<Block> MAGENTA_CONCRETE = vanillaBlock("magenta_concrete");
	public static final BlockEntry<Block> YELLOW_CONCRETE = vanillaBlock("yellow_concrete");
	public static final BlockEntry<Block> CYAN_CONCRETE = vanillaBlock("cyan_concrete");
	public static final BlockEntry<Block> PINK_CONCRETE = vanillaBlock("pink_concrete");

	public static final BlockEntry<Block> CUT_COPPER_SLAB = vanillaBlock("cut_copper_slab");
	public static final BlockEntry<Block> EXPOSED_CUT_COPPER_SLAB = vanillaBlock("exposed_cut_copper_slab");
	public static final BlockEntry<Block> WEATHERED_CUT_COPPER_SLAB = vanillaBlock("weathered_cut_copper_slab");
	public static final BlockEntry<Block> OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("oxidized_cut_copper_slab");
	public static final BlockEntry<Block> WAXED_CUT_COPPER_SLAB = vanillaBlock("waxed_cut_copper_slab");
	public static final BlockEntry<Block> WAXED_EXPOSED_CUT_COPPER_SLAB = vanillaBlock("waxed_exposed_cut_copper_slab");
	public static final BlockEntry<Block> WAXED_WEATHERED_CUT_COPPER_SLAB = vanillaBlock("waxed_weathered_cut_copper_slab");
	public static final BlockEntry<Block> WAXED_OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("waxed_oxidized_cut_copper_slab");

	public static final BlockEntry<Block> PINK_SHULKER_BOX = vanillaBlock("pink_shulker_box");

	public static final BlockEntry<Block> PINK_STAINED_GLASS = vanillaBlock("pink_stained_glass");
	public static final BlockEntry<Block> PINK_STAINED_GLASS_PANE = vanillaBlock("pink_stained_glass_pane");

	public static final BlockEntry<Block> PINK_TERRACOTTA = vanillaBlock("pink_terracotta");

	private static final BlockEntry<Block> IRON_BLOCK = new BlockEntry<>(Blocks.IRON_BLOCK);
	private static final BlockEntry<Block> GOLD_BLOCk = new BlockEntry<>(Blocks.GOLD_BLOCK);
	private static final BlockEntry<Block> EMERALD_BLOCK = new BlockEntry<>(Blocks.EMERALD_BLOCK);
	private static final BlockEntry<Block> LAPIS_BLOCK = new BlockEntry<>(Blocks.LAPIS_BLOCK);
	private static final BlockEntry<Block> REDSTONE_BLOCK = new BlockEntry<>(Blocks.REDSTONE_BLOCK);
	private static final BlockEntry<Block> DIAMOND_BLOCK = new BlockEntry<>(Blocks.DIAMOND_BLOCK);
	private static final BlockEntry<Block> NETHERITE_BLOCK = new BlockEntry<>(Blocks.NETHERITE_BLOCK);
	private static final BlockEntry<Block> QUARTZ_BLOCK = new BlockEntry<>(Blocks.QUARTZ_BLOCK);
	private static final BlockEntry<Block> AMETHYST_BLOCK = new BlockEntry<>(Blocks.AMETHYST_BLOCK);
	private static final BlockEntry<Block> COPPER_BLOCK = vanillaBlock("copper_block");
	private static final BlockEntry<Block> EXPOSED_COPPER_BLOCK = vanillaBlock("exposed_copper");
	private static final BlockEntry<Block> WEATHERED_COPPER_BLOCK = vanillaBlock("weathered_copper");
	private static final BlockEntry<Block> OXIDIZED_COPPER_BLOCK = vanillaBlock("oxidized_copper");

	public static final ItemEntry<Item> PINK_DYE = vanillaItem("pink_dye");

	public static final BlockEntry<?>[] RESOURCE_BLOCKS = new BlockEntry[]{
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

	private static BlockEntry<Block> vanillaBlock(String name) {
		ResourceLocation id = ResourceLocation.withDefaultNamespace(name);
		return new BlockEntry<>(id, BuiltInRegistries.BLOCK.get(id));
	}

	private static ItemEntry<Item> vanillaItem(String name) {
		ResourceLocation key = ResourceLocation.withDefaultNamespace(name);
		return new ItemEntry<>(key, BuiltInRegistries.ITEM.get(key));
	}
}
