package cc.cassian.pyrite.blocks;

import net.minecraft.block.PaneBlock;

public class ModPane extends PaneBlock {
    public ModPane(Settings settings) {
        super(settings.nonOpaque());
    }
}
