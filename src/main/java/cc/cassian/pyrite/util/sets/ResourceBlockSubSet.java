package cc.cassian.pyrite.util.sets;

import cc.cassian.mru.util.ItemLikeEntry;
import net.minecraft.world.level.block.Block;

public record ResourceBlockSubSet(ItemLikeEntry<Block> block, ItemLikeEntry<Block> stairs, ItemLikeEntry<Block> slab,
								  ItemLikeEntry<Block> wall, ItemLikeEntry<Block> wallGate) {
}
