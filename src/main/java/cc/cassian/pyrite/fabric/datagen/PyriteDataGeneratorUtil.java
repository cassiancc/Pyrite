package cc.cassian.pyrite.fabric.datagen;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.pyrite.util.sets.ResourceBlockSet;
import cc.cassian.pyrite.util.sets.ResourceBlockSubSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Optional;

import static cc.cassian.pyrite.registry.BlockCreator.RESOURCE_BLOCK_SETS;

public class PyriteDataGeneratorUtil {

	public static @NonNull ArrayList<ResourceBlockSet> getResourceBlockSets() {
		ArrayList<ResourceBlockSet> newSets = new ArrayList<>(RESOURCE_BLOCK_SETS);
		for (ResourceBlockSet resourceBlockSet : RESOURCE_BLOCK_SETS) {
			var id = ModHelpers.findVanillaBlockID(resourceBlockSet.block());
			if (id.contains("copper")) {
				var cutBlocks = getWaxed(resourceBlockSet.cutBlocks());
				newSets.add(new ResourceBlockSet(getWaxed(resourceBlockSet.block()), getWaxed(cutBlocks), getWaxed(resourceBlockSet.smoothBlocks()), getWaxed(resourceBlockSet.bricks()), getWaxed(resourceBlockSet.chiseled()), getWaxed(resourceBlockSet.pillar()), getWaxed(resourceBlockSet.nostalgia()), getWaxed(resourceBlockSet.bars()), getWaxed(resourceBlockSet.door()), getWaxed(resourceBlockSet.trapdoor()), getWaxed(resourceBlockSet.pressurePlate()), getWaxed(resourceBlockSet.button())));
			}
		}
		return newSets;
	}

	public static ResourceBlockSubSet getWaxed(ResourceBlockSubSet resourceBlockSubSet) {
		return new ResourceBlockSubSet(getWaxed(resourceBlockSubSet.block()), getWaxed(resourceBlockSubSet.stairs()), getWaxed(resourceBlockSubSet.slab()), getWaxed(resourceBlockSubSet.wall()), getWaxed(resourceBlockSubSet.wallGate()));
	}

	public static BlockEntry<Block> getWaxed(BlockEntry<Block> block) {
		if (block.getPath().contains("copper") && !block.getPath().contains("waxed")) {
			Optional<BlockState> waxed = HoneycombItem.getWaxed(block.defaultBlockState());
			if (waxed.isPresent())
				return new BlockEntry<>(waxed.get().getBlock());
			Identifier waxedId = block.getId().withPrefix("waxed_");
			return new BlockEntry<>(waxedId, BuiltInRegistries.BLOCK.getValue(waxedId));
		} else {
			return block;
		}
	}

	public static Block getWaxed(Block block) {
		return getWaxed(new BlockEntry<>(block)).value();
	}

	public static BlockEntry<Block> getBlockOrVanilla(Identifier id) {
		ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
		if (BuiltInRegistries.BLOCK.get(key).isPresent()) {
			return new BlockEntry<>(id, BuiltInRegistries.BLOCK.getOrThrow(key).value());
		} else {
			Identifier location = Identifier.withDefaultNamespace(id.getPath());
			return new BlockEntry<>(location, BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, location)).value());
		}
	}
}
