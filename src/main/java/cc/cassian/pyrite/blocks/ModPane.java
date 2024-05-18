package cc.cassian.pyrite.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ModPane extends PaneBlock {
    private final int power;

    public ModPane(Settings settings) {
        super(settings.nonOpaque());
        this.power = 0;
    }
    public ModPane(Settings settings, int power) {
        super(settings.nonOpaque());
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
