package cc.cassian.pyrite;

import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.block.PaneBlock;

public class ModGlassPane extends PaneBlock {
    public ModGlassPane() {
        super(Settings.of(Material.GLASS).nonOpaque().strength(1.8f, 1.5f)); }
}
