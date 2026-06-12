package cc.cassian.pyrite.registry;

import net.minecraft.world.level.block.Block;

public record TurfSet(String name, Block grassBlock, Block turf, Block stair, Block slab, Block carpet) {
}
