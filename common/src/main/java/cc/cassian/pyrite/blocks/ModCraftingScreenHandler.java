package cc.cassian.pyrite.blocks;


import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Block;

public class ModCraftingScreenHandler extends CraftingMenu {
    private final ContainerLevelAccess context;
    private final Block block;

    public ModCraftingScreenHandler(int syncId, Inventory playerInventory, ContainerLevelAccess context, Block block) {
        super(syncId, playerInventory, context);
        this.context = context;
        this.block = block;
    }

    @Override
    public boolean stillValid(Player player) {
        return ModCraftingScreenHandler.stillValid(this.context, player, block);
    }
}
