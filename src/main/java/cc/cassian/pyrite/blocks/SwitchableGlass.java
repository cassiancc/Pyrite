package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class SwitchableGlass extends TransparentBlock {
    public static final MapCodec<SwitchableGlass> CODEC = simpleCodec(SwitchableGlass::new);
    public static final BooleanProperty POWERED;

    public MapCodec<SwitchableGlass> codec() {
        return CODEC;
    }

    public SwitchableGlass(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));

    }

    protected float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.getValue(POWERED)) {
            return 0F;
        }
        else {
            return 1F;
        }
    }


    @Override
    protected int getLightBlock(BlockState state
    //? if <1.21.4 {
    /*, BlockGetter level, BlockPos pos
    *///?}
      ) {
        if (state.getValue(POWERED)) {
            return 15;
        }
        return 0;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(POWERED, ctx.getLevel().hasNeighborSignal(ctx.getClickedPos()));
    }

    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClientSide()) {
            boolean currentlyPowered = state.getValue(POWERED);
            if (currentlyPowered != world.hasNeighborSignal(pos)) {
                if (currentlyPowered) {
                    world.scheduleTick(pos, this, 4);
                } else {
                    world.setBlock(pos, state.cycle(POWERED), 2);
                }
            }
        }
    }

    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED) && !world.hasNeighborSignal(pos)) {
            world.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    static {
        POWERED = BlockStateProperties.POWERED;
    }
}
