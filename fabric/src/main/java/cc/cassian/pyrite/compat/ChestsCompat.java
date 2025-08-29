package cc.cassian.pyrite.compat;


import dev.lieonlion.mcv.block.FabricMoreChestBlock;
import dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;

import java.util.ArrayList;

public class ChestsCompat {
    public static ArrayList<Block> CHESTS = new ArrayList<>();

    public static Block registerChest(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock, MapColor color) {
        return new FabricMoreChestBlock(color,  "pyrite_"+ blockID.replace("_chest", ""));
    }

    public static void add(Block newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity() {
        for (Block chest : CHESTS) {
            FabricMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY.addSupportedBlock(chest);
            BlockEntityType.CHEST.addSupportedBlock(chest);
        }
    }
}
