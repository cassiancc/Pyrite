package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModPane extends IronBarsBlock {
    private final int power;

    public ModPane(BlockBehaviour.Properties settings) {
        super(settings.noCollission());
        this.power = 0;
    }
    public ModPane(BlockBehaviour.Properties settings, int power) {
        super(settings.noOcclusion());
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
