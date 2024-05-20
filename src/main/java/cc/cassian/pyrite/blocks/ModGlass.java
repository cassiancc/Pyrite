package cc.cassian.pyrite.blocks;

import net.minecraft.block.TransparentBlock;

public class ModGlass extends TransparentBlock {
    public ModGlass(Settings settings) {
        super (settings.nonOpaque());
    }
}
