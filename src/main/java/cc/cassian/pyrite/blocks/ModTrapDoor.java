package cc.cassian.pyrite.blocks;


import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModTrapDoor extends TrapDoorBlock {
    public ModTrapDoor(BlockBehaviour.Properties settings, BlockSetType blockSetType) {
        super(blockSetType, settings);
    }
}
