package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
//? if >1.21.4 {
/*import net.minecraft.world.level.redstone.Orientation;
*///?}
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

// code copy - FenceGateBlock, which cannot be extended without using wood sounds
public class WallGateBlock extends HorizontalDirectionalBlock {

    protected static final VoxelShape Z_AXIS_SHAPE;
    protected static final VoxelShape X_AXIS_SHAPE;
    protected static final VoxelShape IN_WALL_Z_AXIS_SHAPE;
    protected static final VoxelShape IN_WALL_X_AXIS_SHAPE;
    public static final BooleanProperty OPEN;
    public static final BooleanProperty POWERED;
    public static final BooleanProperty IN_WALL;
    protected static final VoxelShape Z_AXIS_COLLISION_SHAPE;
    protected static final VoxelShape X_AXIS_COLLISION_SHAPE;
    protected static final VoxelShape Z_AXIS_SIDES_SHAPE;
    protected static final VoxelShape X_AXIS_SIDES_SHAPE;
    protected static final VoxelShape Z_AXIS_CULL_SHAPE;
    protected static final VoxelShape X_AXIS_CULL_SHAPE;
    protected static final VoxelShape IN_WALL_Z_AXIS_CULL_SHAPE;
    protected static final VoxelShape IN_WALL_X_AXIS_CULL_SHAPE;
    protected final BlockSetType type;
    
    public WallGateBlock(BlockSetType type, Properties settings) {
        super(settings);
        this.type = type;
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false).setValue(POWERED, false).setValue(IN_WALL, false));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(IN_WALL)) {
            return state.getValue(FACING).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_SHAPE : IN_WALL_Z_AXIS_SHAPE;
        } else {
            return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
        }
    }


    //? if >1.21.4 {
    /*protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource randomSource) {
    *///?} else {
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        //?}
        Direction.Axis axis = direction.getAxis();
        if (state.getValue(FACING).getClockWise().getAxis() != axis) {
            //? if >1.21.4 {
            /*return super.updateShape(state, world, scheduledTickAccess, pos, direction, neighborPos, neighborState, randomSource);
             *///?} else {
            return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
            //?}
        } else {
            boolean bl = this.isWall(neighborState) || this.isWall(world.getBlockState(pos.relative(direction.getOpposite())));
            return state.setValue(IN_WALL, bl);
        }
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.getValue(OPEN)) {
            return Shapes.empty();
        } else {
            return state.getValue(FACING).getAxis() == Direction.Axis.Z ? Z_AXIS_SIDES_SHAPE : X_AXIS_SIDES_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (state.getValue(OPEN)) {
            return Shapes.empty();
        } else {
            return state.getValue(FACING).getAxis() == Direction.Axis.Z ? Z_AXIS_COLLISION_SHAPE : X_AXIS_COLLISION_SHAPE;
        }
    }

    @Override
    //? if >1.21.4 {
    /*protected VoxelShape getOcclusionShape(BlockState state) {
    *///?} else {
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        //?}
        if (state.getValue(IN_WALL)) {
            return state.getValue(FACING).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_CULL_SHAPE : IN_WALL_Z_AXIS_CULL_SHAPE;
        } else {
            return state.getValue(FACING).getAxis() == Direction.Axis.X ? X_AXIS_CULL_SHAPE : Z_AXIS_CULL_SHAPE;
        }
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        switch (type) {
            case LAND, AIR -> {
                return state.getValue(OPEN);
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Level world = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        boolean bl = world.hasNeighborSignal(blockPos);
        Direction direction = ctx.getHorizontalDirection();
        Direction.Axis axis = direction.getAxis();
        boolean bl2 = axis == Direction.Axis.Z && (this.isWall(world.getBlockState(blockPos.west())) || this.isWall(world.getBlockState(blockPos.east()))) || axis == Direction.Axis.X && (this.isWall(world.getBlockState(blockPos.north())) || this.isWall(world.getBlockState(blockPos.south())));
        return this.defaultBlockState().setValue(FACING, direction).setValue(OPEN, bl).setValue(POWERED, bl).setValue(IN_WALL, bl2);
    }

    private boolean isWall(BlockState state) {
        return state.is(BlockTags.WALLS);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (state.getValue(OPEN)) {
            state = state.setValue(OPEN, false);
            world.setBlock(pos, state, 10);
        } else {
            Direction direction = player.getDirection();
            if (state.getValue(FACING) == direction.getOpposite()) {
                state = state.setValue(FACING, direction);
            }

            state = state.setValue(OPEN, true);
            world.setBlock(pos, state, 10);
        }

        boolean bl = state.getValue(OPEN);
        world.playSound(player, pos, bl ? this.type.doorOpen() : this.type.doorClose(), SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
        world.gameEvent(player, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        //? if >1.21.4 {
        /*return InteractionResult.SUCCESS_SERVER;
        *///?} else {
        return InteractionResult.SUCCESS;
        //?}
    }

    @Override
    //? if >1.21.4 {
    /*protected void onExplosionHit(BlockState state, ServerLevel world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> biConsumer) {
    *///?} else {
    protected void onExplosionHit(BlockState state, Level world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> biConsumer) {
    //?}
        if (explosion.canTriggerBlocks() && !(Boolean)state.getValue(POWERED)) {
            boolean bl = state.getValue(OPEN);
            world.setBlockAndUpdate(pos, state.setValue(OPEN, !bl));
            world.playSound(null, pos, bl ? this.type.doorClose() : this.type.doorOpen(), SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
            world.gameEvent(bl ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos, GameEvent.Context.of(state));
        }

        super.onExplosionHit(state, world, pos, explosion, biConsumer);
    }

    @Override
    //? if >1.21.4 {
    /*protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
    *///?} else {
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
    //?}
        if (!world.isClientSide()) {
            boolean bl = world.hasNeighborSignal(pos);
            if (state.getValue(POWERED) != bl) {
                world.setBlock(pos, state.setValue(POWERED, bl).setValue(OPEN, bl), 2);
                if (state.getValue(OPEN) != bl) {
                    world.playSound(null, pos, bl ? this.type.doorOpen() : this.type.doorClose(), SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
                    world.gameEvent(null, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                }
            }

        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, POWERED, IN_WALL);
    }

    static {
        Z_AXIS_SHAPE = Block.box(0.0F, 2.0F, 6.0F, 16.0F, 13.0F, 10.0F);
        X_AXIS_SHAPE = Block.box(6.0F, 2.0F, 0.0F, 10.0F, 13.0F, 16.0F);
        IN_WALL_Z_AXIS_SHAPE = Block.box(0.0F, 0.0F, 6.0F, 16.0F, 13.0F, 10.0F);
        IN_WALL_X_AXIS_SHAPE = Block.box(6.0F, 0.0F, 0.0F, 10.0F, 13.0F, 16.0F);
        OPEN = BlockStateProperties.OPEN;
        POWERED = BlockStateProperties.POWERED;
        IN_WALL = BlockStateProperties.IN_WALL;
        Z_AXIS_COLLISION_SHAPE = Block.box(0.0F, 0.0F, 6.0F, 16.0F, 24.0F, 10.0F);
        X_AXIS_COLLISION_SHAPE = Block.box(6.0F, 0.0F, 0.0F, 10.0F, 24.0F, 16.0F);
        Z_AXIS_SIDES_SHAPE = Block.box(0.0F, 5.0F, 6.0F, 16.0F, 24.0F, 10.0F);
        X_AXIS_SIDES_SHAPE = Block.box(6.0F, 5.0F, 0.0F, 10.0F, 24.0F, 16.0F);
        Z_AXIS_CULL_SHAPE = Shapes.or(Block.box(0.0F, 5.0F, 7.0F, 2.0F, 16.0F, 9.0F), Block.box(14.0F, 5.0F, 7.0F, 16.0F, 16.0F, 9.0F));
        X_AXIS_CULL_SHAPE = Shapes.or(Block.box(7.0F, 5.0F, 0.0F, 9.0F, 16.0F, 2.0F), Block.box(7.0F, 5.0F, 14.0F, 9.0F, 16.0F, 16.0F));
        IN_WALL_Z_AXIS_CULL_SHAPE = Shapes.or(Block.box(0.0F, 2.0F, 7.0F, 2.0F, 13.0F, 9.0F), Block.box(14.0F, 2.0F, 7.0F, 16.0F, 13.0F, 9.0F));
        IN_WALL_X_AXIS_CULL_SHAPE = Shapes.or(Block.box(7.0F, 2.0F, 0.0F, 9.0F, 13.0F, 2.0F), Block.box(7.0F, 2.0F, 14.0F, 9.0F, 13.0F, 16.0F));
    }
}
