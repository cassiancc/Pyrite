package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;

import java.util.ArrayList;

public class ChestsCompat {
    public static ArrayList<Block> CHESTS = new ArrayList<>();

    public static Block registerChest(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock) {
        Block column = new MoreChestBlock(blockSettings, () -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY, blockID.replace("_chest", ""));
        return column;
    }

    public static void add(Block newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity() {
        for (Block chest : CHESTS) {
            McvBlockInit.MORE_CHEST_BLOCK_ENTITY.addSupportedBlock(chest);
        }
    }
}
