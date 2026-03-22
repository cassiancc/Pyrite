package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.functions.ModHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if <1.21.4 {
import net.minecraft.world.ItemInteractionResult;
//?}
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModPillar extends RotatedPillarBlock {
    private final int power;

    public ModPillar(Properties settings) {
        super(settings);
        this.power = 0;
    }
    public ModPillar(Properties settings, int power) {
        super(settings);
        this.power = power;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return power == 15;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return power;
    }

    @Override @SuppressWarnings("all")
    protected
    //? if >1.21.4 {
    /*InteractionResult
    *///?} else {
    ItemInteractionResult
    //?}
    useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (stack.is(ItemTags.AXES) && !player.getOffhandItem().is(PyriteItemTags.SHIELDS)) {
                Identifier id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
                Block strippedBlock = ModHelpers.getBlock("stripped_"+ id.getPath());
                if (!strippedBlock.equals(Blocks.AIR)) {
                    world.setBlockAndUpdate(pos, strippedBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS)));
                    //? if >1.21.4 {
                    /*return InteractionResult.SUCCESS;
                    *///?} else {
                    return ItemInteractionResult.SUCCESS;
                    //?}

                }
            }
        }
        //? if >1.21.4 {
        /*return InteractionResult.PASS;
        *///?} else {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        //?}
    }
}