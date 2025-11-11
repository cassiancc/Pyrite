package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.functions.ModHelpers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if <1.21.4 {
/*import net.minecraft.world.ItemInteractionResult;
*///?}
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModWood extends Block {
    public ModWood(Properties settings) {
        super(settings);
    }

    @Override @SuppressWarnings("all")
    protected
    //? if >1.21.4 {
    InteractionResult
     //?} else {
    /*ItemInteractionResult
    *///?}
    useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (stack.is(ItemTags.AXES) && !player.getOffhandItem().is(PyriteTags.SHIELDS)) {
                ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
                Block strippedBlock = ModHelpers.getBlock("stripped_"+ id.getPath());
                if (!strippedBlock.equals(Blocks.AIR)) {
                    world.setBlockAndUpdate(pos, strippedBlock.defaultBlockState());
                    //? if >1.21.4 {
                    return InteractionResult.SUCCESS;
                     //?} else {
                    /*return ItemInteractionResult.SUCCESS;
                    *///?}
                }
            }
        }
        //? if >1.21.4 {
        return InteractionResult.PASS;
         //?} else {
        /*return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        *///?}
    }
}
