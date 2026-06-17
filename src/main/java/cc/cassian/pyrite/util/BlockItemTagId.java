package cc.cassian.pyrite.util;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public record BlockItemTagId(TagKey<Block> block, TagKey<Item> item) {
	public static BlockItemTagId create(final Identifier blockId, final Identifier itemId) {
		return new BlockItemTagId(TagKey.create(Registries.BLOCK, blockId), TagKey.create(Registries.ITEM, itemId));
	}

	public static BlockItemTagId create(final String blockName, final String itemName) {
		return create(Pyrite.of(blockName), Pyrite.of(itemName));
	}

	public static BlockItemTagId create(final String name) {
		Identifier id = Pyrite.of(name);
		return create(id, id);
	}

	public static BlockItemTagId create(final Identifier id) {
		return create(id, id);
	}
}
