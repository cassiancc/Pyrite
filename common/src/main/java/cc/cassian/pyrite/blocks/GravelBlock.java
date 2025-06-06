package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GravelBlock extends FallingBlock {
    public static final MapCodec<GravelBlock> CODEC = createCodec(GravelBlock::new);

    public GravelBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }
}
