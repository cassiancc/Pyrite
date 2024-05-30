package cc.cassian.pyrite.blocks;

import net.minecraft.block.GlassBlock;

public class ModGlass extends GlassBlock {
    public ModGlass(Settings settings) {
        super (settings.nonOpaque());
    }
}