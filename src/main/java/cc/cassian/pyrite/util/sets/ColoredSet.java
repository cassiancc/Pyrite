package cc.cassian.pyrite.util.sets;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public record ColoredSet(String dye, Optional<BlockEntry<Block>> base, Optional<BlockEntry<Block>> carpet, BlockEntry<Block> stairs,
                         BlockEntry<Block> slab) {
	public TagKey<Item> dyeTag() {
		return TagKey.create(Registries.ITEM, Pyrite.of("c", "dyes/" + dye()));
	}
}
