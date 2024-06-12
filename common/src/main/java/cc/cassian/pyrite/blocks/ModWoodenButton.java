package cc.cassian.pyrite.blocks;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.ButtonBlock;

public class ModWoodenButton extends ButtonBlock {
    public ModWoodenButton(Settings settings, BlockSetType blockSetType) {
        super(settings, blockSetType, 40, true);
    }
    public ModWoodenButton(Settings settings, BlockSetType blockSetType, int pressTicks) {
        super(settings, blockSetType, pressTicks, true);
    }
}