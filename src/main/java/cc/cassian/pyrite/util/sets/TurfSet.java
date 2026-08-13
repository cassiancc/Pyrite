package cc.cassian.pyrite.util.sets;

import net.minecraft.world.level.block.Block;

public record TurfSet(String name, Block grassBlock, cc.cassian.mru.util.ItemLikeEntry<Block> turf, cc.cassian.mru.util.ItemLikeEntry<Block> stair, cc.cassian.mru.util.ItemLikeEntry<Block> slab, cc.cassian.mru.util.ItemLikeEntry<Block> carpet) {
}
