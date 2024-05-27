package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModPressurePlate extends PressurePlateBlock {
    public ModPressurePlate(BlockBehaviour.Properties settings, BlockSetType blockSetType) {
        super(Sensitivity.EVERYTHING, settings, blockSetType);
    }
}
