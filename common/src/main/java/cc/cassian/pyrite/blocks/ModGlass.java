package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.TransparentBlock;

public class ModGlass extends TransparentBlock {
    public ModGlass(Properties settings) {
        super (settings.noOcclusion());
    }
}