package cc.cassian.pyrite.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Map;

public class ModWallMounted extends WallMountedBlock {
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case FLOOR -> Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
            case WALL -> getBoundingShape(state);
            //Ceiling
            default -> Block.createCuboidShape(6.0, 8.0, 6.0, 10.0, 16.0, 10.0);
        };
    }

    public ModWallMounted(Settings settings) {
        super(settings);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING);
    }


    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAt(world, pos, getDirection(state).getOpposite());
    }

    public static boolean canPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction);
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction.getOpposite()) | sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }


    private static final Map<Direction, VoxelShape> WALL_BOUNDING_SHAPES;
    public static VoxelShape getBoundingShape(BlockState state) {
        return WALL_BOUNDING_SHAPES.get(state.get(FACING));
    }
    static {
        WALL_BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5, 3.0, 11.0, 10.5, 13.0, 16.0), Direction.SOUTH, Block.createCuboidShape(5.5, 3.0, 0.0, 10.5, 13.0, 5.0), Direction.WEST, Block.createCuboidShape(11.0, 3.0, 5.5, 16.0, 13.0, 10.5), Direction.EAST, Block.createCuboidShape(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)));
    }
}
