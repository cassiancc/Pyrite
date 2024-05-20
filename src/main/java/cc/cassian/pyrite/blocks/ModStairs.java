package cc.cassian.pyrite.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ModStairs extends StairsBlock {
    private final int power;

    public ModStairs(BlockState baseblockstate, Settings settings) {
        super(baseblockstate, settings);
        this.power = 0;
    }
    public ModStairs(BlockState baseblockstate, Settings settings, int power) {
        super(baseblockstate, settings);
        this.power = power;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return power == 15;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return power;
    }
}
