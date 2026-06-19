package cc.cassian.pyrite.util.sets;

import net.minecraft.world.level.block.Block;

public record TurfSet(String name, Block grassBlock, cc.cassian.pyrite.entries.BlockEntry<Block> turf, cc.cassian.pyrite.entries.BlockEntry<Block> stair, cc.cassian.pyrite.entries.BlockEntry<Block> slab, cc.cassian.pyrite.entries.BlockEntry<Block> carpet) {
}
