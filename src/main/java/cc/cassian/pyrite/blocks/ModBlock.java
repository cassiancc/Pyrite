package cc.cassian.pyrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ModBlock extends Block {
    private final int power;

    public ModBlock(Settings settings) {
        super(settings);
        this.power = 0;
    }
    public ModBlock(Settings settings, int power) {
        super(settings);
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
