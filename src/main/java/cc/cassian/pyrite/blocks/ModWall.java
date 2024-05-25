package cc.cassian.pyrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ModWall extends WallBlock {
    private final int power;

    public ModWall(Settings settings) {
        super(settings);
        this.power = 0;
    }
    public ModWall(Settings settings, int power) {
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
