package cc.cassian.pyrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;

public class ModCraftingScreenHandler extends CraftingScreenHandler {
    private final ScreenHandlerContext context;
    private final Block block;

    public ModCraftingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, Block block) {
        super(syncId, playerInventory, context);
        this.context = context;
        this.block = block;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return ModCraftingScreenHandler.canUse(this.context, player, block);
    }
}