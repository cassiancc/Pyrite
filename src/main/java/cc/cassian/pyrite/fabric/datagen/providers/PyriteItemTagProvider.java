//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.util.sets.BrickSet;
import cc.cassian.pyrite.util.sets.ResourceBlockSet;
import com.github.smallinger.copperagebackport.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
//~ if >26.1 'cc.cassian.pyrite.util' -> 'net.minecraft.tags' {
import cc.cassian.pyrite.util.BlockItemTagId;
//~}
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
public class PyriteItemTagProvider extends FabricTagProvider.ItemTagProvider {


	public PyriteItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagProvider blockTagsProvider) {
		super(output, registryLookupFuture, blockTagsProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		copy(PyriteBlockItemTags.AMETHYST);
		copy(PyriteBlockItemTags.CHESTS);
		copy(PyriteBlockItemTags.CONCRETE_SLABS);
		copy(PyriteBlockItemTags.CONCRETE_STAIRS);
		copy(PyriteBlockItemTags.CRAFTING_TABLES);
		copy(PyriteBlockItemTags.DIAMOND);
		copy(PyriteBlockItemTags.EMERALD);
		copy(PyriteBlockItemTags.EXPOSED_COPPER);
		copy(PyriteBlockItemTags.FENCES);
		copy(PyriteBlockItemTags.GOLD);
		copy(PyriteBlockItemTags.IRON);
		copy(PyriteBlockItemTags.LAPIS);
		copy(PyriteBlockItemTags.MUSHROOM_STEM);
		copy(PyriteBlockItemTags.NETHERITE);
		copy(PyriteBlockItemTags.OXIDIZED_COPPER);
		copy(PyriteBlockItemTags.PLANKS);
		copy(PyriteBlockItemTags.STAINED_GLASS);
		copy(PyriteBlockItemTags.STAINED_FRAMED_GLASS);
		copy(PyriteBlockItemTags.TERRACOTTA);
		copy(PyriteBlockItemTags.WALL_GATES);

		builder(PyriteItemTags.BOATS).addAll(get("_boat").stream().filter(p->!p.location().getPath().contains("chest")).toList());
		builder(PyriteItemTags.CHEST_BOATS, "chest_boat");

		// conventional tags
		copy(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES);
		copy(ConventionalBlockTags.OBSIDIANS);
		copy(ConventionalBlockTags.BLACK_DYED);
		copy(ConventionalBlockTags.BLUE_DYED);
		copy(ConventionalBlockTags.BROWN_DYED);
		copy(ConventionalBlockTags.CYAN_DYED);
		copy(ConventionalBlockTags.GRAY_DYED);
		copy(ConventionalBlockTags.GREEN_DYED);
		copy(ConventionalBlockTags.LIGHT_BLUE_DYED);
		copy(ConventionalBlockTags.LIGHT_GRAY_DYED);
		copy(ConventionalBlockTags.LIME_DYED);
		copy(ConventionalBlockTags.MAGENTA_DYED);
		copy(ConventionalBlockTags.ORANGE_DYED);
		copy(ConventionalBlockTags.PURPLE_DYED);
		copy(ConventionalBlockTags.PINK_DYED);
		copy(ConventionalBlockTags.RED_DYED);
		copy(ConventionalBlockTags.WHITE_DYED);
		copy(ConventionalBlockTags.YELLOW_DYED);

		// minecraft
		copy(BlockTags.DIRT);
		copy(BlockTags.DOORS);
		copy(BlockTags.FENCE_GATES);
		copy(BlockTags.GUARDED_BY_PIGLINS);
		copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);
		builder(ItemTags.PIGLIN_LOVED).addAll(get("gold"));
		copy(BlockTags.PLANKS);
		copy(BlockTags.SIGNS);
		copy(BlockTags.STAIRS);
		copy(BlockTags.SLABS);
		copy(BlockTags.WOODEN_BUTTONS);
		copy(BlockTags.WOODEN_DOORS);
		copy(BlockTags.WOODEN_FENCES);
		copy(BlockTags.WOODEN_PRESSURE_PLATES);
		copy(BlockTags.WOODEN_SLABS);
		copy(BlockTags.WOODEN_STAIRS);
		copy(BlockTags.WOODEN_TRAPDOORS);
		copy(ModTags.Blocks.WOODEN_SHELVES);
		copy(BlockTags.WOOL);

		// fd
		copy(ResourceLocation.fromNamespaceAndPath("farmersdelight", "cabinets/wooden"));
		copy(ResourceLocation.fromNamespaceAndPath("farmersdelight", "cabinets"));

		// dyes
		builder(PyriteItemTags.DRAGON_DYES).addAll(get("dragon_dye"));
		builder(PyriteItemTags.POISONOUS_DYES).addAll(get("poisonous_dye"));
		builder(PyriteItemTags.ROSE_DYES).addAll(get("rose_dye"));
		builder(PyriteItemTags.HONEY_DYES).addAll(get("honey_dye"));
		builder(PyriteItemTags.GLOW_DYES).addAll(get("glow_dye"));
		builder(PyriteItemTags.STAR_DYES).addAll(get("star_dye"));
		builder(PyriteItemTags.NOSTALGIA_DYES).addAll(get("nostalgia_dye"));
		builder(ConventionalItemTags.DYES).addTag(PyriteItemTags.DRAGON_DYES).addTag(PyriteItemTags.POISONOUS_DYES).addTag(PyriteItemTags.ROSE_DYES).addTag(PyriteItemTags.HONEY_DYES).addTag(PyriteItemTags.GLOW_DYES).addTag(PyriteItemTags.STAR_DYES).addTag(PyriteItemTags.NOSTALGIA_DYES);

		//? if >26.1 {
		/*for (ResourceBlockSet resourceBlockSet : BlockCreator.RESOURCE_BLOCK_SETS) {
			builder(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_FLAT).add(resourceBlockSet.smoothBlocks().block().itemKey()).add(resourceBlockSet.pillar().itemKey()).add(resourceBlockSet.cutBlocks().block().itemKey()).add(resourceBlockSet.bricks().itemKey()).add(resourceBlockSet.chiseled().itemKey());
		}
		for (BrickSet brickSet : BlockCreator.BRICK_SETS) {
			builder(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY).add(brickSet.base().itemKey());
		}
		builder(ItemTags.SULFUR_CUBE_ARCHETYPE_SLOW_BOUNCY).addAll(get("nether_bricks")).addAll(get("terracotta_bricks"));
		*///?}
	}

    private void copy(TagKey<Block> blockTag) {
        copy(blockTag, TagKey.create(Registries.ITEM, blockTag.location()));
    }

	private void copy(ResourceLocation id) {
		copy(TagKey.create(Registries.BLOCK, id), TagKey.create(Registries.ITEM, id));
	}

	private void copy(BlockItemTagId tagId) {
		copy(tagId.block(), tagId.item());
	}

	//~ if <26.1 'TagAppender<ResourceKey<Item>, Item>' -> 'TagAppender<Item>' {
	private TagAppender<Item> optionalBuilder(TagKey<Item> tag, String id) {
		TagAppender<Item> builder = builder(tag);
		get(id).stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<Item> builder(TagKey<Item> tag, String id) {
		TagAppender<Item> builder = builder(tag);
		get(id).forEach(builder::add);
		return builder;
	}

	private TagAppender<Item> builder(BlockItemTagId tag, Class<? extends Item> aClass) {
		return builder(tag.item()).addAll(get(aClass));
	}

	private TagAppender<Item> builder(BlockItemTagId tag, String id) {
		return builder(tag.item()).addAll(get(id));
	}

	private TagAppender<Item> optionalBuilder(BlockItemTagId tag, String id) {
		TagAppender<Item> builder = builder(tag.item());
		get(id).stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<Item> builder(TagKey<Item> item) {
		return getOrCreateTagBuilder(item);
	}
	//~}

	private List<ResourceKey<Item>> get(String id) {
		return BlockCreator.ITEMS.entrySet().stream().filter(stringItemEntry -> stringItemEntry.getKey().contains(id)).map(PyriteItemTagProvider::of).sorted(Comparator.comparing(ResourceKey::location)).toList();
	}

	private List<ResourceKey<Item>> get(Class<? extends Item> block) {
		return BlockCreator.ITEMS.entrySet().stream().filter(blockEntry -> blockEntry.getValue().getClass().equals(block)).map(PyriteItemTagProvider::of).sorted(Comparator.comparing(ResourceKey::location)).toList();
	}

	private static ResourceKey<Item> of(Map.Entry<String, Item> e) {
		return ResourceKey.create(Registries.ITEM, Pyrite.of(e.getKey()));
	}

	private static ResourceKey<Item> of(String e) {
		return ResourceKey.create(Registries.ITEM, Pyrite.of(e));
	}
}
//?}