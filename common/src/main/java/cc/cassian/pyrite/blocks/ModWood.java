package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.functions.ModHelpers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class ModWood extends Block {
    public ModWood(Settings settings) {
        super(settings);
    }

    @Override @SuppressWarnings("all")
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (stack.isIn(ItemTags.AXES) && !ModHelpers.isShield(player.getOffHandStack())) {
                Identifier id = Registries.BLOCK.getId(state.getBlock());
                Block strippedBlock = ModHelpers.getBlock("stripped_"+ id.getPath());
                if (!strippedBlock.equals(Blocks.AIR)) {
                    world.setBlockState(pos, strippedBlock.getDefaultState());
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.PASS;
    }
}
