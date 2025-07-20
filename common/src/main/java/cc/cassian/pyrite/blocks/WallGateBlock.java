package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;

import java.util.function.BiConsumer;

// code copy - FenceGateBlock, which cannot be extended without using wood sounds
public class WallGateBlock extends HorizontalFacingBlock {

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
    
    public WallGateBlock(BlockSetType type, Settings settings) {
        super(settings);
        this.type = type;
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false).with(POWERED, false).with(IN_WALL, false));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(IN_WALL)) {
            return state.get(FACING).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_SHAPE : IN_WALL_Z_AXIS_SHAPE;
        } else {
            return state.get(FACING).getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
        }
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Direction.Axis axis = direction.getAxis();
        if (state.get(FACING).rotateYClockwise().getAxis() != axis) {
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        } else {
            boolean bl = this.isWall(neighborState) || this.isWall(world.getBlockState(pos.offset(direction.getOpposite())));
            return state.with(IN_WALL, bl);
        }
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(OPEN)) {
            return VoxelShapes.empty();
        } else {
            return state.get(FACING).getAxis() == Direction.Axis.Z ? Z_AXIS_SIDES_SHAPE : X_AXIS_SIDES_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(OPEN)) {
            return VoxelShapes.empty();
        } else {
            return state.get(FACING).getAxis() == Direction.Axis.Z ? Z_AXIS_COLLISION_SHAPE : X_AXIS_COLLISION_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(IN_WALL)) {
            return state.get(FACING).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_CULL_SHAPE : IN_WALL_Z_AXIS_CULL_SHAPE;
        } else {
            return state.get(FACING).getAxis() == Direction.Axis.X ? X_AXIS_CULL_SHAPE : Z_AXIS_CULL_SHAPE;
        }
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        switch (type) {
            case LAND, AIR -> {
                return state.get(OPEN);
            }
            default -> {
                return false;
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        boolean bl = world.isReceivingRedstonePower(blockPos);
        Direction direction = ctx.getHorizontalPlayerFacing();
        Direction.Axis axis = direction.getAxis();
        boolean bl2 = axis == Direction.Axis.Z && (this.isWall(world.getBlockState(blockPos.west())) || this.isWall(world.getBlockState(blockPos.east()))) || axis == Direction.Axis.X && (this.isWall(world.getBlockState(blockPos.north())) || this.isWall(world.getBlockState(blockPos.south())));
        return this.getDefaultState().with(FACING, direction).with(OPEN, bl).with(POWERED, bl).with(IN_WALL, bl2);
    }

    private boolean isWall(BlockState state) {
        return state.isIn(BlockTags.WALLS);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(OPEN)) {
            state = state.with(OPEN, false);
            world.setBlockState(pos, state, 10);
        } else {
            Direction direction = player.getHorizontalFacing();
            if (state.get(FACING) == direction.getOpposite()) {
                state = state.with(FACING, direction);
            }

            state = state.with(OPEN, true);
            world.setBlockState(pos, state, 10);
        }

        boolean bl = state.get(OPEN);
        world.playSound(player, pos, bl ? this.type.doorOpen() : this.type.doorClose(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
        world.emitGameEvent(player, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    protected void onExploded(BlockState state, World world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger) {
        if (explosion.canTriggerBlocks() && !(Boolean)state.get(POWERED)) {
            boolean bl = state.get(OPEN);
            world.setBlockState(pos, state.with(OPEN, !bl));
            world.playSound(null, pos, bl ? this.type.doorClose() : this.type.doorOpen(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
            world.emitGameEvent(bl ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos, GameEvent.Emitter.of(state));
        }

        super.onExploded(state, world, pos, explosion, stackMerger);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            boolean bl = world.isReceivingRedstonePower(pos);
            if (state.get(POWERED) != bl) {
                world.setBlockState(pos, state.with(POWERED, bl).with(OPEN, bl), 2);
                if (state.get(OPEN) != bl) {
                    world.playSound(null, pos, bl ? this.type.doorOpen() : this.type.doorClose(), SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F);
                    world.emitGameEvent(null, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                }
            }

        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, POWERED, IN_WALL);
    }

    static {
        Z_AXIS_SHAPE = Block.createCuboidShape(0.0F, 2.0F, 6.0F, 16.0F, 13.0F, 10.0F);
        X_AXIS_SHAPE = Block.createCuboidShape(6.0F, 2.0F, 0.0F, 10.0F, 13.0F, 16.0F);
        IN_WALL_Z_AXIS_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 6.0F, 16.0F, 13.0F, 10.0F);
        IN_WALL_X_AXIS_SHAPE = Block.createCuboidShape(6.0F, 0.0F, 0.0F, 10.0F, 13.0F, 16.0F);
        OPEN = Properties.OPEN;
        POWERED = Properties.POWERED;
        IN_WALL = Properties.IN_WALL;
        Z_AXIS_COLLISION_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 6.0F, 16.0F, 24.0F, 10.0F);
        X_AXIS_COLLISION_SHAPE = Block.createCuboidShape(6.0F, 0.0F, 0.0F, 10.0F, 24.0F, 16.0F);
        Z_AXIS_SIDES_SHAPE = Block.createCuboidShape(0.0F, 5.0F, 6.0F, 16.0F, 24.0F, 10.0F);
        X_AXIS_SIDES_SHAPE = Block.createCuboidShape(6.0F, 5.0F, 0.0F, 10.0F, 24.0F, 16.0F);
        Z_AXIS_CULL_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 5.0F, 7.0F, 2.0F, 16.0F, 9.0F), Block.createCuboidShape(14.0F, 5.0F, 7.0F, 16.0F, 16.0F, 9.0F));
        X_AXIS_CULL_SHAPE = VoxelShapes.union(Block.createCuboidShape(7.0F, 5.0F, 0.0F, 9.0F, 16.0F, 2.0F), Block.createCuboidShape(7.0F, 5.0F, 14.0F, 9.0F, 16.0F, 16.0F));
        IN_WALL_Z_AXIS_CULL_SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0F, 2.0F, 7.0F, 2.0F, 13.0F, 9.0F), Block.createCuboidShape(14.0F, 2.0F, 7.0F, 16.0F, 13.0F, 9.0F));
        IN_WALL_X_AXIS_CULL_SHAPE = VoxelShapes.union(Block.createCuboidShape(7.0F, 2.0F, 0.0F, 9.0F, 13.0F, 2.0F), Block.createCuboidShape(7.0F, 2.0F, 14.0F, 9.0F, 13.0F, 16.0F));
    }
}
