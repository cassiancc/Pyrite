package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import java.util.OptionalInt;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SwitchableGlass extends TransparentBlock {
    public static final int SIGNAL_DECAY_DISTANCE = 16;
    private static final int TICK_DELAY = 1;
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, SIGNAL_DECAY_DISTANCE);

    @Override
	public MapCodec<? extends TransparentBlock> codec() {
		return simpleCodec(SwitchableGlass::new);
	}

	public SwitchableGlass(final BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, SIGNAL_DECAY_DISTANCE));
    }

    protected boolean skipRendering(final BlockState state, final BlockState neighborState, final Direction direction) {
        return (neighborState.getBlock() instanceof SwitchableGlass && powered(neighborState)) || super.skipRendering(state, neighborState, direction);
    }

    protected void tick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    protected int getLightDampening(final BlockState state) {
        if (powered(state)) {
            return 15;
        }
        return 0;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        if (powered(state)) {
            return 0F;
        }
        else {
            return 1F;
        }
    }

    private static boolean powered(BlockState state) {
        return state.getValue(DISTANCE) < SIGNAL_DECAY_DISTANCE;
    }

    protected BlockState updateShape(final BlockState state, final LevelReader level, final ScheduledTickAccess ticks, final BlockPos pos, final Direction directionToNeighbour, final BlockPos neighbourPos, final BlockState neighbourState, final RandomSource random) {
        int distanceFromNeighbor = getDistanceAt(level, pos, neighbourState) + 1;
        if (distanceFromNeighbor != 1 || state.getValue(DISTANCE) != distanceFromNeighbor) {
            ticks.scheduleTick(pos, this, TICK_DELAY);
        }

        return state;
    }

    private static BlockState updateDistance(BlockState state, final LevelAccessor level, final BlockPos pos) {
        int newDistance = SIGNAL_DECAY_DISTANCE;
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            neighborPos.setWithOffset(pos, direction);
            newDistance = Math.min(newDistance, getDistanceAt(level, pos, level.getBlockState(neighborPos)) + 1);
            if (newDistance == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, newDistance);
    }

    private static int getDistanceAt(LevelReader level, BlockPos pos, final BlockState state) {
        return getOptionalDistanceAt(level, pos, state).orElse(SIGNAL_DECAY_DISTANCE);
    }

    public static OptionalInt getOptionalDistanceAt(LevelReader level, BlockPos pos, final BlockState state) {
        if (level.hasNeighborSignal(pos)) {
            return OptionalInt.of(0);
        } else {
            return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE);
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return updateDistance(this.defaultBlockState(), context.getLevel(), context.getClickedPos());
    }

}
