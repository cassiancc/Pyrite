package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;


public class ModStairs extends StairBlock {
    private final int power;

    public ModStairs(BlockState baseblockstate, BlockBehaviour.Properties settings) {
        super(baseblockstate, settings);
        this.power = 0;
    }
    public ModStairs(BlockState baseblockstate, BlockBehaviour.Properties settings, int power) {
        super(baseblockstate, settings);
        this.power = power;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return power == 15;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return power;
    }
}
