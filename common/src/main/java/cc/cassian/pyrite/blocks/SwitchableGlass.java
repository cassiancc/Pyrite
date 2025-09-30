package cc.cassian.pyrite.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class SwitchableGlass extends TransparentBlock {
    public static final MapCodec<SwitchableGlass> CODEC = createCodec(SwitchableGlass::new);
    public static final BooleanProperty POWERED;

    public MapCodec<SwitchableGlass> getCodec() {
        return CODEC;
    }

    public SwitchableGlass(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));

    }

    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(POWERED)) {
            return 0F;
        }
        else {
            return 1F;
        }
    }

    protected int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        if (state.get(POWERED)) {
            return 15;
        }
        return 0;
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWERED, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!world.isClient()) {
            boolean currentlyPowered = state.get(POWERED);
            if (currentlyPowered != world.isReceivingRedstonePower(pos)) {
                if (currentlyPowered) {
                    world.scheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, state.cycle(POWERED), 2);
                }
            }
        }
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(POWERED), 2);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    static {
        POWERED = Properties.POWERED;
    }
}
