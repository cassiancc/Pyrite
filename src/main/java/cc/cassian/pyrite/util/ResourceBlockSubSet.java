package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.BlockEntry;
import net.minecraft.world.level.block.Block;

public record ResourceBlockSubSet(BlockEntry<Block> block, BlockEntry<Block> stairs, BlockEntry<Block> slab,
                                  BlockEntry<Block> wall, BlockEntry<Block> wallGate) {
}
