package cc.cassian.pyrite;

import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class ModGlass extends GlassBlock {
    public ModGlass() {
        super(Settings.of(Material.GLASS).nonOpaque().strength(1.8f, 1.5f)); }
}
