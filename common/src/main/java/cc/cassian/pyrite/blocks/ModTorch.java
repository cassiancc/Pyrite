package cc.cassian.pyrite.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Map;

public class ModTorch extends WallMountedBlock {
    private final ParticleEffect particle;
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case FLOOR -> Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
            case WALL -> getBoundingShape(state);
            //Ceiling
            default -> Block.createCuboidShape(6.0, 8.0, 6.0, 10.0, 16.0, 10.0);
        };
    }

    public ModTorch(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double xPlus;
        double yPlus;
        double zPlus;
        switch (state.get(FACE)) {
            case FLOOR:
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.65;
                zPlus = (double)pos.getZ() + 0.5;
                break;
            case WALL:
                switch (state.get(FACING)) {
                    case EAST:
                        xPlus = (double)pos.getX() + 0.3;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.5;
                        break;
                    case WEST:
                        xPlus = (double)pos.getX() + 0.7;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.5;
                        break;
                    case SOUTH:
                        xPlus = (double)pos.getX() + 0.5;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.25;
                        break;
                    default:
                        xPlus = (double)pos.getX() + 0.5;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() +.8;
                        break;
                }
                break;
            //Ceiling
            default:
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.4;
                zPlus = (double)pos.getZ() + 0.5;
                break;
        }



        world.addParticle(ParticleTypes.SMOKE, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
        world.addParticle(particle, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
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
