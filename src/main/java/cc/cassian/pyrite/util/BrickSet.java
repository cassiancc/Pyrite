package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.BlockEntry;
import net.minecraft.world.level.block.Block;

public record BrickSet(String blockID, BlockEntry<Block> base, BlockEntry<Block> stairs, BlockEntry<Block> slab, BlockEntry<Block> wall, BlockEntry<Block> wallGate) {
}
