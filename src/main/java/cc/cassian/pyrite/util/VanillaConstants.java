package cc.cassian.pyrite.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static cc.cassian.pyrite.util.ModHelpers.getBlock;

public class VanillaConstants {
	public static final Block WHITE_WOOL = vanillaBlock("white_wool");
	public static final Block GREEN_WOOL = vanillaBlock("green_wool");
	public static final Block PURPLE_WOOL = vanillaBlock("purple_wool");
	public static final Block CYAN_WOOL = vanillaBlock("cyan_wool");
	public static final Block ORANGE_WOOL = vanillaBlock("orange_wool");
	public static final Block MAGENTA_WOOL = vanillaBlock("magenta_wool");
	public static final Block YELLOW_WOOL = vanillaBlock("yellow_wool");
	public static final Block WHITE_CONCRETE_POWDER = vanillaBlock("white_concrete_powder");
	public static final Block WHITE_CONCRETE = vanillaBlock("white_concrete");
	public static final Item PINK_DYE = vanillaItem("pink_dye");
	public static final Block PINK_CARPET = vanillaBlock("pink_carpet");
	public static final Block GREEN_CARPET = vanillaBlock("green_carpet");
	public static final Block PURPLE_CARPET = vanillaBlock("purple_carpet");
	public static final Block CYAN_CARPET = vanillaBlock("cyan_carpet");
	public static final Block ORANGE_CARPET = vanillaBlock("orange_carpet");
	public static final Block MAGENTA_CARPET = vanillaBlock("magenta_carpet");
	public static final Block YELLOW_CARPET = vanillaBlock("yelllow_carpet");
	public static final Block GREEN_CONCRETE = vanillaBlock("green_concrete");
	public static final Block PURPLE_CONCRETE = vanillaBlock("purple_concrete");
	public static final Block LIGHT_BLUE_CONCRETE = vanillaBlock("light_blue_concrete");
	public static final Block ORANGE_CONCRETE = vanillaBlock("orange_concrete");
	public static final Block MAGENTA_CONCRETE = vanillaBlock("magenta_concrete");
	public static final Block YELLOW_CONCRETE = vanillaBlock("yellow_concrete");
	public static final Block CYAN_CONCRETE = vanillaBlock("cyan_concrete");
	public static final Block CUT_COPPER_SLAB = vanillaBlock("cut_copper_slab");
	public static final Block EXPOSED_CUT_COPPER_SLAB = vanillaBlock("exposed_cut_copper_slab");
	public static final Block WEATHERED_CUT_COPPER_SLAB = vanillaBlock("weathered_cut_copper_slab");
	public static final Block OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("oxidized_cut_copper_slab");
	public static final Block WAXED_CUT_COPPER_SLAB = vanillaBlock("waxed_cut_copper_slab");
	public static final Block WAXED_EXPOSED_CUT_COPPER_SLAB = vanillaBlock("waxed_exposed_cut_copper_slab");
	public static final Block WAXED_WEATHERED_CUT_COPPER_SLAB = vanillaBlock("waxed_weathered_cut_copper_slab");
	public static final Block WAXED_OXIDIZED_CUT_COPPER_SLAB = vanillaBlock("waxed_oxidized_cut_copper_slab");
	public static final Block PINK_SHULKER_BOX = vanillaBlock("pink_shulker_box");
	public static final Block PINK_STAINED_GLASS = vanillaBlock("pink_stained_glass");
	public static final Block PINK_STAINED_GLASS_PANE = vanillaBlock("pink_stained_glass_pane");
	public static final Block PINK_CONCRETE = vanillaBlock("pink_concrete");
	public static final Block PINK_CONCRETE_POWDER = vanillaBlock("pink_concrete_powder");
	public static final Block PINK_TERRACOTTA = vanillaBlock("pink_terracotta");
	private static final Block COPPER_BLOCK = vanillaBlock("copper_block");
	private static final Block EXPOSED_COPPER = vanillaBlock("exposed_copper");
	private static final Block WEATHERED_COPPER = vanillaBlock("weathered_copper");
	private static final Block OXIDIZED_COPPER = vanillaBlock("oxidized_copper");

	public static final Block[] RESOURCE_BLOCKS = new Block[]{
			Blocks.IRON_BLOCK,
			Blocks.GOLD_BLOCK,
			Blocks.EMERALD_BLOCK,
			Blocks.LAPIS_BLOCK,
			Blocks.REDSTONE_BLOCK,
			Blocks.DIAMOND_BLOCK,
			Blocks.NETHERITE_BLOCK,
			Blocks.QUARTZ_BLOCK,
			Blocks.AMETHYST_BLOCK,
			VanillaConstants.COPPER_BLOCK,
			VanillaConstants.EXPOSED_COPPER,
			VanillaConstants.WEATHERED_COPPER,
			VanillaConstants.OXIDIZED_COPPER

	};

	private static Block vanillaBlock(String name) {
		return BuiltInRegistries.BLOCK.getValue(Identifier.withDefaultNamespace(name));
	};

	private static Item vanillaItem(String name) {
		return BuiltInRegistries.ITEM.getValue(Identifier.withDefaultNamespace(name));
	};
}
