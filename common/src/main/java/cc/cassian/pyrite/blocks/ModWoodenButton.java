package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.awt.*;

public class ModWoodenButton extends ButtonBlock {
    public ModWoodenButton(BlockBehaviour.Properties settings, BlockSetType blockSetType) {
        super(settings, blockSetType, 40, true);
    }
    public ModWoodenButton(BlockBehaviour.Properties settings, BlockSetType blockSetType, int pressTicks) {
        super(settings, blockSetType, pressTicks, true);
    }
}
