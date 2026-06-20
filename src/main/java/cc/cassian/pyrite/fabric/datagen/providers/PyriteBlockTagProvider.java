//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.*;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.sets.ResourceBlockSet;
import cc.cassian.pyrite.util.sets.WoodSet;
import com.github.smallinger.copperagebackport.ModTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
//~ if >26.1 'cc.cassian.pyrite.util' -> 'net.minecraft.tags' {
import cc.cassian.pyrite.util.BlockItemTagId;
//~}
//? if >26.1 {
/*import net.minecraft.tags.BlockItemTags;
*///?}
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
public class PyriteBlockTagProvider extends FabricTagProvider.BlockTagProvider {
	public PyriteBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		// pyrite tags
		optionalBuilder(PyriteBlockItemTags.AMETHYST, "amethyst");
		builder(PyriteBlockItemTags.CARPET, ModCarpet.class);
		optionalBuilder(PyriteBlockItemTags.CHESTS, "_chest");
		optionalBuilder(PyriteBlockItemTags.COPPER, get("copper").stream().filter(t->{
			String string = t.location().toString();
			if (string.contains("oxidized") || string.contains("weathered") || string.contains("exposed") || string.contains("lever")) {
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
		builder(PyriteBlockItemTags.STAINED_FRAMED_GLASS.block()).addAll(get("_framed_glass").stream().filter(p->!p.location().getPath().contains("pane")).toList());
		builder(PyriteBlockItemTags.TERRACOTTA.block()).addAll(get("terracotta").stream().filter(p->!p.location().getPath().contains("bricks")).toList());
		builder(PyriteBlockItemTags.TERRACOTTA_BRICKS, "terracotta_bricks");
		builder(PyriteBlockItemTags.WALL_GATES, "wall_gate")
				.addOptional(of("holystone_wall_gate").location())
				.addOptional(of("mossy_holystone_wall_gate").location())
				.addOptional(of("holystone_brick_wall_gate").location())
				.addOptional(of("icestone_wall_gate").location())
				.addOptional(of("aerogel_wall_gate").location())
				.addOptional(of("carved_wall_gate").location())
				.addOptional(of("angelic_wall_gate").location())
				.addOptional(of("hellfire_wall_gate").location());
		optionalBuilder(PyriteBlockItemTags.WEATHERED_COPPER, "weathered_copper");

		// fabric tags
		builder(BlockFunctionalityTags.CAN_CLIMB_TRAPDOOR_ABOVE).addTag(PyriteBlockItemTags.LADDERS.block());

		// conventional tag
		builder(ConventionalBlockTags.WOODEN_CHESTS).addTag(PyriteBlockItemTags.CHESTS.block());
		optionalBuilder(ConventionalBlockTags.CONCRETES, "concrete");
		optionalBuilder(ConventionalBlockTags.OBSIDIANS, "obsidian");
		builder(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES).addTag(PyriteBlockItemTags.CRAFTING_TABLES.block());
		builder(ConventionalBlockTags.GLASS_BLOCKS_COLORLESS).add(of("framed_glass"));
		builder(ConventionalBlockTags.GLASS_PANES_COLORLESS).add(of("framed_glass_pane"));
		builder(ConventionalBlockTags.GLASS_BLOCKS).addTag(PyriteBlockItemTags.STAINED_GLASS.block());
		builder(ConventionalBlockTags.GLASS_BLOCKS_TINTED).addTag(PyriteBlockItemTags.STAINED_GLASS.block()).addTag(PyriteBlockItemTags.STAINED_FRAMED_GLASS.block());
		optionalBuilder(ConventionalBlockTags.BLACK_DYED, "black_");
		optionalBuilder(ConventionalBlockTags.BLUE_DYED, "blue_");
		optionalBuilder(ConventionalBlockTags.BROWN_DYED, get("brown_").stream().filter(c-> {
			String path = c.location().getPath();
			if (path.contains("mushroom")) return false;
			return true;
		}).toList());
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
		optionalBuilder(ConventionalBlockTags.RED_DYED, get("red_").stream().filter(c-> {
			String path = c.location().getPath();
			if (path.contains("sandstone")) return false;
			else if (path.contains("mushroom")) return false;
			else if (path.contains("copper")) return false;
			return true;
		}).toList());
		optionalBuilder(ConventionalBlockTags.WHITE_DYED, "white_");
		optionalBuilder(ConventionalBlockTags.YELLOW_DYED, "yellow_");

		// minecraft tags
		builder(BlockTags.BEACON_BASE_BLOCKS).addAll(BlockCreator.BLOCKS.stream().filter(blockEntry -> {
			var b = blockEntry.getValue().getClass().equals(ModBlock.class) || blockEntry.getValue().getClass().equals(ModPillar.class);
			if (!b) return false;
			var key = blockEntry.getPath();
			if (key.contains("diamond") || key.contains("emerald") || key.contains("iron") || key.contains("gold") || key.contains("netherite")) {
				return true;
			}
			return false;
		}).map(BlockEntry::resourceKey).sorted(Comparator.comparing(ResourceKey::location)).toList());
		builder(BlockTags.CEILING_HANGING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->contains(key, "hanging_sign")).toList());
		builder(BlockTags.CLIMBABLE).addTag(PyriteBlockItemTags.LADDERS.block());
		builder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS).addTag(PyriteBlockItemTags.CARPET.block());
		builder(BlockTags.DIRT).addAll(get("turf"));
		builder(BlockTags.DRAGON_IMMUNE).addTag(PyriteBlockItemTags.OBSIDIAN.block());
		builder(BlockTags.GUARDED_BY_PIGLINS).addTag(PyriteBlockItemTags.CHESTS.block());
		builder(BlockTags.FLOWER_POTS).addAll(PyriteItemGroups.POTTED_FLOWERS.values().stream().map(s->s.get().builtInRegistryHolder().key()).toList());
		builder(BlockTags.INFINIBURN_END).addAll(get("netherrack"));
		builder(BlockTags.INFINIBURN_OVERWORLD).addAll(get("netherrack"));
		builder(BlockTags.INFINIBURN_NETHER).addAll(get("netherrack"));
		//~ if >26.1 'BlockTags.CONCRETE_POWDER' -> 'BlockItemTags.CONCRETE_POWDERS' {
		optionalBuilder(BlockTags.CONCRETE_POWDER, "concrete_powder");
		//~}
		//~ if >26.1 'BlockTags' -> 'BlockItemTags' {
		optionalBuilder(BlockTags.LOGS_THAT_BURN, "log");
		optionalBuilder(BlockTags.LOGS_THAT_BURN, "stem");
		//~}
		builder(BlockTags.NEEDS_DIAMOND_TOOL).addTag(PyriteBlockItemTags.OBSIDIAN.block()).addTag(PyriteBlockItemTags.NETHERITE.block());
		builder(BlockTags.NEEDS_IRON_TOOL).addTag(PyriteBlockItemTags.GOLD.block()).addTag(PyriteBlockItemTags.DIAMOND.block()).addTag(PyriteBlockItemTags.EMERALD.block());
		builder(BlockTags.NEEDS_STONE_TOOL).addTag(PyriteBlockItemTags.IRON.block()).addTag(PyriteBlockItemTags.LAPIS.block()).addTag(PyriteBlockItemTags.COPPER.block()).addTag(PyriteBlockItemTags.EXPOSED_COPPER.block()).addTag(PyriteBlockItemTags.OXIDIZED_COPPER.block()).addTag(PyriteBlockItemTags.WEATHERED_COPPER.block());
		builder(BlockTags.PLANKS).addTag(PyriteBlockItemTags.PLANKS.block());
		optionalBuilder(BlockTags.BAMBOO_PLANTABLE_ON, "gravel");
		builder(BlockTags.WALL_HANGING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->contains(key, "hanging_sign")).map(blockResourceKey -> of(blockResourceKey.location().withPath(path->path.replace("hanging_sign","hanging_wall_sign")))).toList());
		optionalBuilder(BlockTags.WOOL, "wool");
		builder(BlockTags.STANDING_SIGNS).addAll(get(PyriteItemGroups.SIGNS).stream().filter(key->!contains(key, "hanging_sign")).toList());
		builder(BlockTags.WALLS).addAll(get(ModWall.class));

		for (WoodSet woodSet : BlockCreator.WOOD_SETS) {
			builder(BlockTags.FENCE_GATES).add(woodSet.fenceGate().resourceKey());
			builder(BlockTags.SIGNS).add(woodSet.sign().resourceKey());
			add(PyriteBlockItemTags.PLANKS, woodSet.planks());
			builder(BlockTags.WOODEN_BUTTONS).add(woodSet.button().resourceKey());
			builder(BlockTags.WOODEN_DOORS).add(woodSet.door().resourceKey());
			builder(BlockTags.WOODEN_FENCES).add(woodSet.fence().resourceKey());
			builder(BlockTags.WOODEN_PRESSURE_PLATES).add(woodSet.pressurePlate().resourceKey());
			builder(BlockTags.WOODEN_SLABS).add(woodSet.slab().resourceKey());
			builder(BlockTags.WOODEN_STAIRS).add(woodSet.stairs().resourceKey());
			builder(ModTags.Blocks.WOODEN_SHELVES).add(woodSet.shelf().resourceKey());
			builder(BlockTags.WOODEN_TRAPDOORS).add(woodSet.trapdoor().resourceKey());
		}

		for (ResourceBlockSet resourceBlockSet : BlockCreator.RESOURCE_BLOCK_SETS) {
			add(BlockTags.BUTTONS, resourceBlockSet.button());
			add(BlockTags.WOODEN_DOORS, resourceBlockSet.door());
			add(BlockTags.WALLS, resourceBlockSet.smoothBlocks().wall(), resourceBlockSet.cutBlocks().wall());
			add(BlockTags.PRESSURE_PLATES, resourceBlockSet.pressurePlate());
			add(BlockTags.SLABS, resourceBlockSet.cutBlocks().slab(), resourceBlockSet.smoothBlocks().slab());
			add(BlockTags.STAIRS, resourceBlockSet.cutBlocks().stairs(), resourceBlockSet.smoothBlocks().stairs());
			add(BlockTags.TRAPDOORS, resourceBlockSet.trapdoor());
			add(BlockTags.DOORS, resourceBlockSet.door());
			add(PyriteBlockItemTags.WALL_GATES, resourceBlockSet.cutBlocks().wallGate(), resourceBlockSet.smoothBlocks().wallGate());
		}

		builder(BlockTags.SLABS).addAll(get(ModSlab.class));
		builder(BlockTags.SLABS).addAll(get(WeatheringCopperSlabBlock.class));

		builder(BlockTags.STAIRS).addAll(get(ModStairs.class));
		builder(BlockTags.STAIRS).addAll(get(WeatheringCopperStairBlock.class));

		// fd tags
		optionalBuilder(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("farmersdelight", "cabinets/wooden")), "cabinet");
		optionalBuilder(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("farmersdelight", "cabinets")), "cabinet");

		// quad tags
		builder(TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("quad", "cats_on_blocks/sit"))).addTag(PyriteBlockItemTags.CHESTS.block());
	}

	private void add(TagKey<Block> woodenDoors, BlockEntry<Block>... blocks) {
		for (BlockEntry<Block> block : blocks) {
			if (block != null && !block.isVanilla()) {
				builder(woodenDoors).add(block.resourceKey());
			}
		}
	}

	private void add(BlockItemTagId woodenDoors, BlockEntry<Block>... blocks) {
		add(woodenDoors.block(), blocks);
	}

	private boolean contains(ResourceKey<Block> key, String hangingSign) {
        return key.location().toString().contains(hangingSign);
    }

	private List<ResourceKey<Block>> get(ArrayList<ItemEntry<Item>> signs) {
        List<ResourceKey<Block>> blocks = new ArrayList<>();
		signs.forEach(sign -> {
			if (sign.get() instanceof BlockItem blockItem) {
				blocks.add(BuiltInRegistries.BLOCK.getResourceKey(blockItem.getBlock()).get());
			}
		});
		return blocks;
    }

	//~ if <26.1 'TagAppender<ResourceKey<Block>, Block>' -> 'TagAppender<Block>' {

	private TagAppender<Block> optionalBuilder(TagKey<Block> tag, String id) {
		TagAppender<Block> builder = builder(tag);
		get(id).stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<Block> builder(TagKey<Block> tag) {
		return getOrCreateTagBuilder(tag);
	}

	private TagAppender<Block> builder(BlockItemTagId fences, Class<? extends Block> aClass) {
		return builder(fences.block()).addAll(get(aClass));
	}

	private TagAppender<Block> builder(BlockItemTagId fences, String id) {
		return builder(fences.block()).addAll(get(id));
	}

	private TagAppender<Block> optionalBuilder(BlockItemTagId tag, String id) {
		TagAppender<Block> builder = builder(tag.block());
		get(id).stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<Block> optionalBuilder(BlockItemTagId tag, Collection<ResourceKey<Block>> id) {
		TagAppender<Block> builder = builder(tag.block());
		id.stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}

	private TagAppender<Block> optionalBuilder(TagKey<Block> tag, Collection<ResourceKey<Block>> id) {
		TagAppender<Block> builder = builder(tag);
		id.stream().map(c->c.location()).forEach(builder::addOptional);
		return builder;
	}
	//~}


	private List<ResourceKey<Block>> get(String id) {
		return BlockCreator.BLOCKS.stream().filter(stringItemEntry -> stringItemEntry.getPath().contains(id)).map(BlockEntry::resourceKey).sorted(Comparator.comparing(ResourceKey::location)).toList();
	}

	private List<ResourceKey<Block>> get(Class<? extends Block> block) {
		return BlockCreator.BLOCKS.stream().filter(blockEntry -> blockEntry.getValue().getClass().equals(block)).map(BlockEntry::resourceKey).sorted(Comparator.comparing(ResourceKey::location)).toList();
	}

	private static ResourceKey<Block> of(Map.Entry<String, Block> e) {
		return ResourceKey.create(Registries.BLOCK, Pyrite.of(e.getKey()));
	}

	private static ResourceKey<Block> of(String e) {
		return ResourceKey.create(Registries.BLOCK, Pyrite.of(e));
	}

	private static ResourceKey<Block> of(ResourceLocation e) {
		return ResourceKey.create(Registries.BLOCK, e);
	}
}
//?}