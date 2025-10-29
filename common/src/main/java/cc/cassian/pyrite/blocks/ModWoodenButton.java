package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModWoodenButton extends ButtonBlock {
    public ModWoodenButton(Properties settings, BlockSetType blockSetType) {
        super(blockSetType, 40, settings);
    }
    public ModWoodenButton(Properties settings, BlockSetType blockSetType, int pressTicks) {
        super(blockSetType, pressTicks, settings);
    }
}