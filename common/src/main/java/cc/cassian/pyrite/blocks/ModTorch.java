package cc.cassian.pyrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;

public class ModTorch extends WallMountedBlock {
    public ModTorch(Settings settings) {
        super(settings);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING);
    }
}
