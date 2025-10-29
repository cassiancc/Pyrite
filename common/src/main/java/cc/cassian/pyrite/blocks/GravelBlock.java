package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class GravelBlock extends FallingBlock {
    public static final MapCodec<GravelBlock> CODEC = simpleCodec(GravelBlock::new);

    public GravelBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}
