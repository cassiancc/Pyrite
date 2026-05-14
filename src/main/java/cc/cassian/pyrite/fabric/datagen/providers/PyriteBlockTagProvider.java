//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.*;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.core.PyriteBlockTags;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import dev.lieonlion.quad.tags.QuadBlockTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
		optionalBuilder(PyriteBlockItemTags.COPPER, get("copper").stream().filter(t->{
			String string = t.identifier().toString();
			if (string.contains("oxidized") || string.contains("weathered") || string.contains("exposed")) {
				return false;
			}
			return true;
		}).toList());
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
		optionalBuilder(PyriteBlockItemTags.LAMPS, "_lamp");
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
		builder(BlockTags.BEACON_BASE_BLOCKS).addAll(BlockCreator.BLOCKS.entrySet().stream().filter(blockEntry -> {
			var b = blockEntry.getValue().getClass().equals(ModBlock.class) || blockEntry.getValue().getClass().equals(ModPillar.class);
			if (!b) return false;
			var key = blockEntry.getKey();
			if (key.contains("diamond") || key.contains("emerald") || key.contains("iron") || key.contains("gold") || key.contains("netherite")) {
				return true;
			}
			return false;
		}).map(PyriteBlockTagProvider::of).sorted(Comparator.comparing(ResourceKey::identifier)));
		builder(BlockTags.CEILING_HANGING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->contains(key, "hanging_sign")));
		builder(BlockTags.CLIMBABLE).addTag(PyriteBlockItemTags.LADDERS.block());
		builder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS).addTag(PyriteBlockItemTags.CARPET.block());
		builder(BlockTags.DIRT).addAll(get("turf"));
		builder(BlockTags.DRAGON_IMMUNE).addTag(PyriteBlockItemTags.OBSIDIAN.block());
		optionalBuilder(BlockTags.CONCRETE_POWDER, "concrete_powder");
		builder(BlockTags.GUARDED_BY_PIGLINS).addTag(PyriteBlockItemTags.CHESTS.block());
		builder(BlockTags.FLOWER_POTS).addAll(PyriteItemGroups.POTTED_FLOWERS.values().stream().map(s->s.get().builtInRegistryHolder().key()));
		builder(BlockTags.INFINIBURN_END).addAll(get("netherrack"));
		builder(BlockTags.INFINIBURN_OVERWORLD).addAll(get("netherrack"));
		builder(BlockTags.INFINIBURN_NETHER).addAll(get("netherrack"));
		optionalBuilder(BlockTags.LOGS_THAT_BURN, "log");
		optionalBuilder(BlockTags.LOGS_THAT_BURN, "stem");
		builder(BlockTags.NEEDS_DIAMOND_TOOL).addTag(PyriteBlockItemTags.OBSIDIAN.block()).addTag(PyriteBlockItemTags.NETHERITE.block());
		builder(BlockTags.NEEDS_IRON_TOOL).addTag(PyriteBlockItemTags.GOLD.block()).addTag(PyriteBlockItemTags.DIAMOND.block()).addTag(PyriteBlockItemTags.EMERALD.block());
		builder(BlockTags.NEEDS_STONE_TOOL).addTag(PyriteBlockItemTags.IRON.block()).addTag(PyriteBlockItemTags.LAPIS.block()).addTag(PyriteBlockItemTags.COPPER.block()).addTag(PyriteBlockItemTags.EXPOSED_COPPER.block()).addTag(PyriteBlockItemTags.OXIDIZED_COPPER.block()).addTag(PyriteBlockItemTags.WEATHERED_COPPER.block());
		builder(BlockTags.PLANKS).addTag(PyriteBlockItemTags.PLANKS.block());
		optionalBuilder(BlockTags.SUPPORTS_BAMBOO, "gravel");
		builder(BlockTags.WALL_HANGING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->contains(key, "hanging_sign")).map(blockResourceKey -> of(blockResourceKey.identifier().withPath(path->path.replace("hanging_sign","hanging_wall_sign")))));
		optionalBuilder(BlockTags.WOOL, "wool");
		builder(BlockTags.STANDING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->!contains(key, "hanging_sign")));
		builder(BlockTags.WOODEN_SHELVES).addAll(get(ShelfBlock.class));
		builder(BlockTags.WALLS).addAll(get(ModWall.class));

		for (BlockFamily family : BlockCreator.FAMILIES) {
			if (family.getBaseBlock().builtInRegistryHolder().key().identifier().getPath().contains("planks")) {
				valueLookupBuilder(PyriteBlockItemTags.PLANKS.block()).add(family.getBaseBlock());
				family.getVariants().forEach((variant, block) -> {
					if (block == null) return;
					switch (variant) {
						case BUTTON -> valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add(block);
						case DOOR -> valueLookupBuilder(BlockTags.WOODEN_DOORS).add(block);
						case FENCE -> valueLookupBuilder(BlockTags.WOODEN_FENCES).add(block);
						case FENCE_GATE -> valueLookupBuilder(BlockTags.FENCE_GATES).add(block);
						case PRESSURE_PLATE -> valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(block);
						case SIGN -> valueLookupBuilder(BlockTags.SIGNS).add(block);
						case SLAB -> valueLookupBuilder(BlockTags.WOODEN_SLABS).add(block);
						case STAIRS -> valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(block);
						case TRAPDOOR -> valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(block);
					}
				});
			} else {
				family.getVariants().forEach((variant, block) -> {
					if (block == null) return;
					switch (variant) {
						case BUTTON -> valueLookupBuilder(BlockTags.BUTTONS).add(block);
						case CUSTOM_FENCE_GATE -> valueLookupBuilder(PyriteBlockItemTags.WALL_GATES.block()).add(block);
						case DOOR -> valueLookupBuilder(BlockTags.DOORS).add(block);
						case FENCE -> valueLookupBuilder(BlockTags.FENCES).add(block);
						case PRESSURE_PLATE -> valueLookupBuilder(BlockTags.PRESSURE_PLATES).add(block);
						case SIGN -> valueLookupBuilder(BlockTags.SIGNS).add(block);
						case SLAB -> valueLookupBuilder(BlockTags.SLABS).add(block);
						case STAIRS -> valueLookupBuilder(BlockTags.STAIRS).add(block);
						case TRAPDOOR -> valueLookupBuilder(BlockTags.TRAPDOORS).add(block);
					}
				});
			}
		}
		builder(BlockTags.SLABS).addAll(get(ModSlab.class));
		builder(BlockTags.SLABS).addAll(get(WeatheringCopperSlabBlock.class));

		builder(BlockTags.STAIRS).addAll(get(ModStairs.class));
		builder(BlockTags.STAIRS).addAll(get(WeatheringCopperStairBlock.class));

		// fd tags
		optionalBuilder(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", "cabinets/wooden")), "cabinet");
		optionalBuilder(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("farmersdelight", "cabinets")), "cabinet");

		// quad tags
		builder(QuadBlockTags.CATS_ON_BLOCKS_SIT).addTag(PyriteBlockItemTags.CHESTS.block());
	}

    private boolean contains(ResourceKey<Block> key, String hangingSign) {
        return key.identifier().toString().contains(hangingSign);
    }

	private List<ResourceKey<Block>> get(ArrayList<Supplier<Item>> signs) {
        List<ResourceKey<Block>> blocks = new ArrayList<>();
		signs.forEach(sign -> {
			if (sign.get() instanceof BlockItem blockItem) {
				blocks.add(BuiltInRegistries.BLOCK.getResourceKey(blockItem.getBlock()).get());
			}
		});
		return blocks;
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

	private TagAppender<ResourceKey<Block>, Block> optionalBuilder(PyriteBlockItemTags.BlockItemTagId tag, Collection<ResourceKey<Block>> id) {
		TagAppender<ResourceKey<Block>, Block> builder = builder(tag.block());
		id.forEach(builder::addOptional);
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

	private static ResourceKey<Block> of(Identifier e) {
		return ResourceKey.create(Registries.BLOCK, e);
	}
}
//?}