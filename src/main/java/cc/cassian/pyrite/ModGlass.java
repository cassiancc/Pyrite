package cc.cassian.pyrite;

import net.minecraft.block.GlassBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ModGlass extends GlassBlock {
    public ModGlass() {
        super(Settings.create().nonOpaque().strength(2.0f).sounds(BlockSoundGroup.GLASS)); }
}
