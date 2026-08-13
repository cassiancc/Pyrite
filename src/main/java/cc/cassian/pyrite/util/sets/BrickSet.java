package cc.cassian.pyrite.util.sets;

import cc.cassian.mru.util.ItemLikeEntry;
import net.minecraft.world.level.block.Block;

public record BrickSet(String blockID, ItemLikeEntry<Block> base, ItemLikeEntry<Block> stairs, ItemLikeEntry<Block> slab, ItemLikeEntry<Block> wall, ItemLikeEntry<Block> wallGate) {
}
