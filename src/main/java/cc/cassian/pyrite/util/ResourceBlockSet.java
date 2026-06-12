package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.BlockEntry;
import net.minecraft.world.level.block.Block;

public record ResourceBlockSet(Block block, ResourceBlockSubSet cutBlocks, ResourceBlockSubSet smoothBlocks,
                               BlockEntry<Block> bricks, BlockEntry<Block> chiseled, BlockEntry<Block> pillar,
                               BlockEntry<Block> nostalgia, BlockEntry<Block> bars, BlockEntry<Block> door,
                               BlockEntry<Block> trapdoor, BlockEntry<Block> pressurePlate, BlockEntry<Block> button) {
}
