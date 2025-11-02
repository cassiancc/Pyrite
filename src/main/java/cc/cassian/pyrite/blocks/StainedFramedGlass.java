package cc.cassian.pyrite.blocks;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.StainedGlassBlock;

public class StainedFramedGlass extends StainedGlassBlock {
    public StainedFramedGlass(DyeColor color, Properties settings) {
        super (color, settings.noOcclusion());
    }
}