package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModCraftingTable extends CraftingTableBlock {
    private static final Component TITLE = Component.translatable("container.crafting");

    public ModCraftingTable(BlockBehaviour.Properties settings) {
        super(settings);
    }
    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else {
            player.openMenu(blockState.getMenuProvider(world, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {

        return new SimpleMenuProvider((syncId, inventory, player) -> new ModCraftingScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos), state.getBlock()), TITLE);
    }
}
