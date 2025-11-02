package cc.cassian.pyrite.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModWallMounted extends FaceAttachedHorizontalDirectionalBlock {
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case FLOOR -> Block.box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
            case WALL -> getBoundingShape(state);
            //Ceiling
            default -> Block.box(6.0, 8.0, 6.0, 10.0, 16.0, 10.0);
        };
    }

    public ModWallMounted(Properties settings) {
        super(settings);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING);
    }


    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return null;
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canAttach(world, pos, getConnectedDirection(state).getOpposite());
    }

    public static boolean canAttach(LevelReader world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.relative(direction);
        return world.getBlockState(blockPos).isFaceSturdy(world, blockPos, direction.getOpposite()) | canSupportCenter(world, pos.below(), Direction.UP);
    }


    private static final Map<Direction, VoxelShape> WALL_BOUNDING_SHAPES;
    public static VoxelShape getBoundingShape(BlockState state) {
        return WALL_BOUNDING_SHAPES.get(state.getValue(FACING));
    }
    static {
        WALL_BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0), Direction.SOUTH, Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0), Direction.WEST, Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5), Direction.EAST, Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)));
    }
}
