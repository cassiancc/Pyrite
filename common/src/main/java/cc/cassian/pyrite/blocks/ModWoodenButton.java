package cc.cassian.pyrite.blocks;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

public class ModWoodenButton extends ButtonBlock {
    public ModWoodenButton(Settings settings, BlockSetType blockSetType) {
        super(blockSetType, 40, settings);
    }
    public ModWoodenButton(Settings settings, BlockSetType blockSetType, int pressTicks) {
        super(blockSetType, pressTicks, settings);
    }
}