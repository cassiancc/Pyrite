package cc.cassian.pyrite.util;

import net.minecraft.world.level.block.Block;

public record BrickSet(String blockID, Block base, Block stairs, Block slab, Block wall, Block wallGate) {
}
