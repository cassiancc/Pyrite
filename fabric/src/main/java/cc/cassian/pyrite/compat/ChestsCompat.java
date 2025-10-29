package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;

public class ChestsCompat {
    public static ArrayList<Block> CHESTS = new ArrayList<>();

    public static Block registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        return new MoreChestBlock(blockSettings, () -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY, "pyrite_"+ blockID.replace("_chest", ""));
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
