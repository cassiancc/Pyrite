package cc.cassian.pyrite;

import net.minecraft.block.TransparentBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ModGlass extends TransparentBlock {
    public ModGlass() {
        super(Settings.create().nonOpaque().strength(2.0f).sounds(BlockSoundGroup.GLASS)); }
}
