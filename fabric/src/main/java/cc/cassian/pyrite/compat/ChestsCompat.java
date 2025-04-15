package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;

public class ChestsCompat {
    public static Block registerChest(String blockID, MapColor blockSettings, String group, Block copyBlock) {
        Block column = new MoreChestBlock(blockSettings, blockID.replace("_chest", ""));
        return column;
    }

    public static void registerToBlockEntity(Block newBlock) {
        McvBlockInit.MORE_CHEST_BLOCK_ENTITY.addSupportedBlock(newBlock);
    }
}
