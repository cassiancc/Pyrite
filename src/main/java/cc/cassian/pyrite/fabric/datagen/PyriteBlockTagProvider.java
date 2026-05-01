package cc.cassian.pyrite.fabric.datagen;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.ModCarpet;
import cc.cassian.pyrite.blocks.ModCraftingTable;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.registry.BlockCreator;
import dev.lieonlion.quad.tags.QuadBlockTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
public class PyriteBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public PyriteBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		// pyrite tags
		optionalBuilder(PyriteBlockItemTags.AMETHYST, "amethyst");
		builder(PyriteBlockItemTags.CARPET, ModCarpet.class);
		optionalBuilder(PyriteBlockItemTags.CHESTS, "_chest");
		builder(PyriteBlockItemTags.CONCRETE_SLABS, "concrete_slab");
		builder(PyriteBlockItemTags.CONCRETE_STAIRS, "concrete_stairs");
		builder(PyriteBlockItemTags.CRAFTING_TABLES, ModCraftingTable.class);
		builder(PyriteBlockItemTags.FENCES, FenceBlock.class);
		optionalBuilder(PyriteBlockItemTags.DIAMOND, "diamond");
		optionalBuilder(PyriteBlockItemTags.EMERALD, "emerald");
		optionalBuilder(PyriteBlockItemTags.EXPOSED_COPPER, "exposed_copper");
		optionalBuilder(PyriteBlockItemTags.GOLD, "gold");
		optionalBuilder(PyriteBlockItemTags.IRON, "iron");
		builder(PyriteBlockItemTags.LADDERS, "ladder");
		optionalBuilder(PyriteBlockItemTags.LAPIS, "lapis");
		builder(PyriteBlockItemTags.MUSHROOM_STEM, "mushroom_stem");
		optionalBuilder(PyriteBlockItemTags.OBSIDIAN, "obsidian");
		optionalBuilder(PyriteBlockItemTags.PLANKS, "planks");
		optionalBuilder(PyriteBlockItemTags.QUARTZ, "quartz");
		optionalBuilder(PyriteBlockItemTags.NETHERITE, "netherite");
		optionalBuilder(PyriteBlockItemTags.OXIDIZED_COPPER, "oxidized_copper");
		optionalBuilder(PyriteBlockItemTags.REDSTONE, "redstone");
		builder(PyriteBlockItemTags.STAINED_GLASS, "stained_glass");
		builder(PyriteBlockItemTags.STAINED_FRAMED_GLASS.block()).addAll(get("_framed_glass").stream().filter(p->!p.identifier().getPath().contains("pane")));
		builder(PyriteBlockItemTags.TERRACOTTA.block()).addAll(get("terracotta").stream().filter(p->!p.identifier().getPath().contains("bricks")));
		builder(PyriteBlockItemTags.TERRACOTTA_BRICKS, "terracotta_bricks");
		builder(PyriteBlockItemTags.WALL_GATES, "wall_gate")
				.addOptional(of("holystone_wall_gate"))
				.addOptional(of("mossy_holystone_wall_gate"))
				.addOptional(of("holystone_brick_wall_gate"))
				.addOptional(of("icestone_wall_gate"))
				.addOptional(of("aerogel_wall_gate"))
				.addOptional(of("carved_wall_gate"))
				.addOptional(of("angelic_wall_gate"))
				.addOptional(of("hellfire_wall_gate"));
		optionalBuilder(PyriteBlockItemTags.WEATHERED_COPPER, "weathered_copper");

		// fabric tags
		builder(BlockFunctionalityTags.CAN_CLIMB_TRAPDOOR_ABOVE).addTag(PyriteBlockItemTags.LADDERS.block());

		// conventional tag
		builder(ConventionalBlockTags.WOODEN_CHESTS).addTag(PyriteBlockItemTags.CHESTS.block());
		optionalBuilder(ConventionalBlockTags.CONCRETES, "concrete");
		builder(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES).addTag(PyriteBlockItemTags.CRAFTING_TABLES.block());
		builder(ConventionalBlockTags.GLASS_BLOCKS_COLORLESS).add(of("framed_glass"));
		builder(ConventionalBlockTags.GLASS_PANES_COLORLESS).add(of("framed_glass_pane"));
		builder(ConventionalBlockTags.GLASS_BLOCKS).addTag(PyriteBlockItemTags.STAINED_GLASS.block());
		builder(ConventionalBlockTags.GLASS_BLOCKS_TINTED).addTag(PyriteBlockItemTags.STAINED_GLASS.block()).addTag(PyriteBlockItemTags.STAINED_FRAMED_GLASS.block());
		optionalBuilder(ConventionalBlockTags.BLACK_DYED, "black_");
		optionalBuilder(ConventionalBlockTags.BLUE_DYED, "blue_");
		optionalBuilder(ConventionalBlockTags.BROWN_DYED, "brown_");
		optionalBuilder(ConventionalBlockTags.CYAN_DYED, "cyan_");
		optionalBuilder(ConventionalBlockTags.GRAY_DYED, "gray_");
		optionalBuilder(ConventionalBlockTags.GREEN_DYED, "green_");
		optionalBuilder(ConventionalBlockTags.LIGHT_BLUE_DYED, "light_blue_");
		optionalBuilder(ConventionalBlockTags.LIGHT_GRAY_DYED, "light_gray_");
		optionalBuilder(ConventionalBlockTags.LIME_DYED, "lime_");
		optionalBuilder(ConventionalBlockTags.MAGENTA_DYED, "magenta_");
		optionalBuilder(ConventionalBlockTags.ORANGE_DYED, "orange_");
		optionalBuilder(ConventionalBlockTags.PURPLE_DYED, "purple_");
		optionalBuilder(ConventionalBlockTags.PINK_DYED, "pink_");
		optionalBuilder(ConventionalBlockTags.RED_DYED, "red_");
		optionalBuilder(ConventionalBlockTags.WHITE_DYED, "white_");
		optionalBuilder(ConventionalBlockTags.YELLOW_DYED, "yellow_");

		// minecraft tags
		optionalBuilder(BlockTags.CONCRETE_POWDER, "concrete_powder");
		optionalBuilder(BlockTags.WOOL, "wool");
		optionalBuilder(BlockTags.SUPPORTS_BAMBOO, "gravel");

		// fd tags
		optionalBuilder(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", "cabinets/wooden")), "cabinet");
		optionalBuilder(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", "cabinets")), "cabinet");

		// quad tags
		builder(QuadBlockTags.CATS_ON_BLOCKS_SIT).addTag(PyriteBlockItemTags.CHESTS.block());
	}

	private TagAppender<ResourceKey<Block>, Block> optionalBuilder(TagKey<Block> tag, String id) {
		TagAppender<ResourceKey<Block>, Block> builder = builder(tag);
		get(id).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<ResourceKey<Block>, Block> builder(PyriteBlockItemTags.BlockItemTagId fences, Class<? extends Block> aClass) {
		return builder(fences.block()).addAll(get(aClass));
	}

	private TagAppender<ResourceKey<Block>, Block> builder(PyriteBlockItemTags.BlockItemTagId fences, String id) {
		return builder(fences.block()).addAll(get(id));
	}

	private TagAppender<ResourceKey<Block>, Block> optionalBuilder(PyriteBlockItemTags.BlockItemTagId tag, String id) {
		TagAppender<ResourceKey<Block>, Block> builder = builder(tag.block());
		get(id).forEach(builder::addOptional);
		return builder;
	}


	private List<ResourceKey<Block>> get(String id) {
		return BlockCreator.BLOCKS.entrySet().stream().filter(stringItemEntry -> stringItemEntry.getKey().contains(id)).map(PyriteBlockTagProvider::of).sorted(Comparator.comparing(ResourceKey::identifier)).toList();
	}

	private List<ResourceKey<Block>> get(Class<? extends Block> block) {
		return BlockCreator.BLOCKS.entrySet().stream().filter(blockEntry -> blockEntry.getValue().getClass().equals(block)).map(PyriteBlockTagProvider::of).sorted(Comparator.comparing(ResourceKey::identifier)).toList();
	}

	private static ResourceKey<Block> of(Map.Entry<String, Block> e) {
		return ResourceKey.create(Registries.BLOCK, Pyrite.of(e.getKey()));
	}

	private static ResourceKey<Block> of(String e) {
		return ResourceKey.create(Registries.BLOCK, Pyrite.of(e));
	}
}
