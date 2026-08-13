package cc.cassian.pyrite.util.sets;

import cc.cassian.mru.util.ItemLikeEntry;
import net.minecraft.world.level.block.Block;

public record ResourceBlockSet(Block block, ResourceBlockSubSet cutBlocks, ResourceBlockSubSet smoothBlocks,
							   ItemLikeEntry<Block> bricks, ItemLikeEntry<Block> chiseled, ItemLikeEntry<Block> pillar,
							   ItemLikeEntry<Block> nostalgia, ItemLikeEntry<Block> bars, ItemLikeEntry<Block> door,
							   ItemLikeEntry<Block> trapdoor, ItemLikeEntry<Block> pressurePlate, ItemLikeEntry<Block> button) {
}
