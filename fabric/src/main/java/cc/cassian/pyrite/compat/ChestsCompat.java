package cc.cassian.pyrite.compat;

import dev.lieonlion.mcv.block.FabricMoreChestBlock;
import dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.ArrayList;

public class ChestsCompat {
    public static ArrayList<Block> CHESTS = new ArrayList<>();

    public static Block registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        return new FabricMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
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
