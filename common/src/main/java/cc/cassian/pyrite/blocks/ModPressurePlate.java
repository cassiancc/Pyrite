package cc.cassian.pyrite.blocks;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;

public class ModPressurePlate extends PressurePlateBlock {
    public ModPressurePlate(Settings settings, BlockSetType blockSetType) {
        super(ActivationRule.EVERYTHING, settings, blockSetType);
    }
}