package cc.cassian.pyrite.util.sets;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.mru.util.ItemLikeEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

public record ColoredSet(String dye, Optional<ItemLikeEntry<Block>> base, Optional<ItemLikeEntry<Block>> carpet, ItemLikeEntry<Block> stairs,
                         ItemLikeEntry<Block> slab) {
	public TagKey<Item> dyeTag() {
		return TagKey.create(Registries.ITEM, Pyrite.of("c", "dyes/" + dye()));
	}
}
