package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.swing.text.html.BlockView;

public class GravelBlock extends FallingBlock {
    public static final MapCodec<GravelBlock> CODEC = simpleCodec(GravelBlock::new);

    public GravelBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }

    @Override
    public int getDustColor(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return 0;
    }
}
