package cc.cassian.pyrite;

import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;

public class ModGlass extends GlassBlock {
    public ModGlass() {
        super(Settings.of(Material.GLASS).nonOpaque().strength(2.0f)); }
}