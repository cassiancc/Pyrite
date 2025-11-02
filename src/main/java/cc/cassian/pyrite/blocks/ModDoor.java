package cc.cassian.pyrite.blocks;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModDoor extends DoorBlock {
    public ModDoor(BlockBehaviour.Properties settings, BlockSetType blockSetType) {
        super(blockSetType, settings);
    }
}
