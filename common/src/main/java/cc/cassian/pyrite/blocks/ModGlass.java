package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModGlass extends GlassBlock {
    public ModGlass(BlockBehaviour.Properties settings) {
        super (settings.noOcclusion());
    }
}
